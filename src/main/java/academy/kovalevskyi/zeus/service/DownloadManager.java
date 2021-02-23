package academy.kovalevskyi.zeus.service;

import academy.kovalevskyi.zeus.util.FileExplorer;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;

public class DownloadManager implements AutoCloseable {

  private final String directory;
  private final String link;
  private final byte[] buffer;
  private final String template;
  private long parts;
  private long part;
  private InputStream input;
  private RandomAccessFile output;
  private HttpURLConnection connection;

  public DownloadManager(final String link) {
    this(link, FileExplorer.WORKING_DIRECTORY);
  }

  public DownloadManager(final String link, final String directory) {
    this.link = link;
    this.directory = directory;
    this.buffer = new byte[1024];
    this.template = "\r%3.0f%% [%-100s] %,d/%,d bytes";
    this.part = 0;
  }

  public File download() throws IOException {
    connection = (HttpURLConnection) new URL(link).openConnection();
    final var result = prepareResultFile(connection.getURL());
    if (result.exists()) {
      var message = String.format("%s already exists", result.getAbsolutePath());
      throw new FileAlreadyExistsException(message);
    }
    parts = connection.getContentLengthLong();
    input = connection.getInputStream();
    output = new RandomAccessFile(result, "rw");
    var bytes = 0;
    while ((bytes = input.read(buffer)) != -1) {
      part += bytes;
      output.write(buffer, 0, bytes);
      System.out.print(getProgressBar());
    }
    System.out.println();
    return result;
  }

  @Override
  public void close() throws IOException {
    input.close();
    output.close();
    connection.disconnect();
  }

  private File prepareResultFile(final URL url) {
    if (!new File(directory).isDirectory()) {
      throw new IllegalArgumentException(String.format("%s is not a directory", directory));
    }
    var name = url.getFile().substring(url.getFile().lastIndexOf("/") + 1);
    return new File(String.format("%s%s%s", directory, File.separator, name));
  }

  private String getProgressBar() {
    return String.format(template, calculateProgress(), prepareProgressLine(), part, parts);
  }

  private double calculateProgress() {
    return (100.0 / parts) * part;
  }

  private String prepareProgressLine() {
    return "=".repeat(((int) calculateProgress()));
  }
}
