package academy.kovalevskyi.zeus.service;

import academy.kovalevskyi.zeus.util.FileExplorer;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;

public class DownloadManager implements Closeable {

  private final String directory;
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

  public DownloadManager(final URL link, final String directory) {
    if (link == null || directory == null || directory.isBlank()) {
      throw new IllegalArgumentException();
    }
    this.link = link;
    this.directory = directory;
    this.buffer = new byte[1024];
    this.template = "\r%3.2f%% [%-100s] %,d/%,d bytes";
    this.part = 0;
  }

  public File download(final boolean showProgress) throws IOException {
    final var result = prepareResultFile();
    final var tmp = prepareTmpFile(result);
    connection = (HttpURLConnection) link.openConnection();
    parts = connection.getContentLengthLong();
    if (result.exists() && result.length() == parts) {
      var message = String.format("%s already exists", result.getAbsolutePath());
      throw new FileAlreadyExistsException(message);
    }
    input = connection.getInputStream();
    try (var output = new RandomAccessFile(tmp, "rw")) {
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
    if (!tmp.renameTo(result)) {
      throw new NullPointerException("Something went wrong while file was moving");
    }
    return result;
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

  private File prepareResultFile() throws FileNotFoundException {
    if (!new File(directory).isDirectory()) {
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
    return new File(String.format("%s/%s", directory, name));
  }

  private File prepareTmpFile(final File result) {
    return new File(String.format("%s/%s.tmp", FileExplorer.TMP_DIRECTORY, result.getName()));
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
