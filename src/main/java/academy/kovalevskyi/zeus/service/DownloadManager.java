package academy.kovalevskyi.zeus.service;

import academy.kovalevskyi.zeus.util.FileExplorer;
import java.io.Closeable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class DownloadManager implements Closeable {

  private final Path directory;
  private final URL link;
  private final byte[] buffer;
  private final String template;
  private long parts;
  private long part;
  private InputStream input;
  private HttpURLConnection connection;

  public DownloadManager(final URL link) {
    this(link, FileExplorer.WORKING_DIRECTORY);
  }

  public DownloadManager(final URL link, final Path directory) {
    if (link == null || directory == null) {
      throw new IllegalArgumentException();
    }
    this.link = link;
    this.directory = directory;
    this.buffer = new byte[1024];
    this.template = "\r%3.2f%% [%-100s] %,d/%,d bytes";
    this.part = 0;
  }

  public Path download(final boolean showProgress) throws IOException {
    final var result = prepareResultFile();
    final var tmp = prepareTmpFile(result);
    connection = (HttpURLConnection) link.openConnection();
    parts = connection.getContentLengthLong();
    if (Files.exists(result)) {
      var message = String.format("%s already exists", result.toAbsolutePath());
      throw new FileAlreadyExistsException(message);
    }
    input = connection.getInputStream();
    try (var output = new RandomAccessFile(tmp.toFile(), "rw")) {
      var bytes = 0;
      while ((bytes = input.read(buffer)) != -1) {
        part += bytes;
        output.write(buffer, 0, bytes);
        if (showProgress) {
          System.out.print(prepareProgressBar());
        }
      }
      if (showProgress) {
        System.out.println();
      }
    }
    return Files.move(tmp, result, StandardCopyOption.REPLACE_EXISTING);
  }

  @Override
  public void close() throws IOException {
    if (input != null) {
      input.close();
    }
    if (connection != null) {
      connection.disconnect();
    }
  }

  private Path prepareResultFile() throws FileNotFoundException {
    if (!Files.isDirectory(directory)) {
      throw new IllegalArgumentException(String.format("%s is not a directory", directory));
    }
    var path = link.getPath();
    if (path == null || path.equals("/")) {
      throw new FileNotFoundException("Wrong link");
    }
    var name = Path.of(path).getFileName().toString();
    if (name == null || name.isBlank()) {
      throw new FileNotFoundException("Can't parse file name");
    }
    return directory.resolve(name);
  }

  private Path prepareTmpFile(final Path result) {
    return FileExplorer.TMP_DIRECTORY.resolve(String.format("%s.tmp", result.getFileName()));
  }

  private String prepareProgressBar() {
    return String.format(template, calculateProgress(), prepareProgressLine(), part, parts);
  }

  private double calculateProgress() {
    return (100.0 / parts) * part;
  }

  private String prepareProgressLine() {
    return "=".repeat(((int) calculateProgress()));
  }
}
