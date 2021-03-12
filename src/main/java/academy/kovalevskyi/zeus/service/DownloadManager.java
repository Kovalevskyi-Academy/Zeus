package academy.kovalevskyi.zeus.service;

import academy.kovalevskyi.zeus.util.FileExplorer;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;

public class DownloadManager implements Closeable {

  private final String directory;
  private final String link;
  private final byte[] buffer;
  private final String template;
  private long parts;
  private long part;
  private InputStream input;
  private HttpURLConnection connection;

  public DownloadManager(final String link) {
    this(link, FileExplorer.WORKING_DIRECTORY);
  }

  public DownloadManager(final String link, final String directory) {
    this.link = link;
    this.directory = directory;
    this.buffer = new byte[1024];
    this.template = "\r%3.2f%% [%-100s] %,d/%,d bytes";
    this.part = 0;
  }

  public File download(final boolean showProgress) throws IOException {
    connection = (HttpURLConnection) new URL(link).openConnection();
    final var name = parseJarName(connection.getURL());
    final var result = prepareResultFile(name);
    final var tmp = prepareTmpFile(name);
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
    input.close();
    connection.disconnect();
  }

  private File prepareResultFile(final String name) {
    if (!new File(directory).isDirectory()) {
      throw new IllegalArgumentException(String.format("%s is not a directory", directory));
    }
    return new File(String.format("%s%s%s", directory, File.separator, name));
  }

  private File prepareTmpFile(final String name) {
    return new File(String.format("%s%s%s.tmp", FileExplorer.TMP_DIRECTORY, File.separator, name));
  }

  private String parseJarName(final URL url) {
    var file = url.getFile();
    return file.substring(file.lastIndexOf("/") + 1);
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
