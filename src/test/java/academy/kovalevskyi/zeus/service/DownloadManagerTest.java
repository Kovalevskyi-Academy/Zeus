package academy.kovalevskyi.zeus.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class DownloadManagerTest {

  private static final Path TMP_DIR = Path.of(System.getProperty("java.io.tmpdir"));
  private static File testFile;

  @SuppressWarnings("ResultOfMethodCallIgnored")
  @BeforeAll
  public static void beforeAll() throws IOException {
    testFile = Path.of(TMP_DIR.toString(), "someTmpFile.txt").toFile();
    testFile.createNewFile();
    testFile.deleteOnExit();
  }

  @Test
  public void testThrowsConstructor() throws MalformedURLException {
    var link = new URL("http://example.org");
    assertThrows(IllegalArgumentException.class, () -> new DownloadManager(null, TMP_DIR));
    assertThrows(IllegalArgumentException.class, () -> new DownloadManager(link, null));
    assertThrows(IllegalArgumentException.class, () -> new DownloadManager(null));
  }

  @Test
  public void testThrowsFileNotFount() {
    assertThrows(
        FileNotFoundException.class,
        () -> new DownloadManager(new URL("http://example.org")).download(false));
    assertThrows(
        FileNotFoundException.class,
        () -> new DownloadManager(new URL("http://example.org/")).download(false));
    assertThrows(
        FileNotFoundException.class,
        () -> new DownloadManager(new URL("http://example.org/info")).download(false));
  }

  @Test
  public void testThrowsIllegal() throws MalformedURLException {
    var manager = new DownloadManager(new URL("http://example.org"), testFile.toPath());
    assertThrows(IllegalArgumentException.class, () -> manager.download(false));
  }

  @Test
  public void testFileSuccessfullyDownloadedWithoutPrints() throws IOException {
    var buffer = new ByteArrayOutputStream();
    var stdOut = System.out;
    System.setOut(new PrintStream(buffer));
    checkDownloadedFile(false);
    assertEquals(0, buffer.size());
    System.out.close();
    System.setOut(stdOut);
  }

  @Test
  public void testFileSuccessfullyDownloadedWithPrints() throws IOException {
    var buffer = new ByteArrayOutputStream();
    var stdOut = System.out;
    System.setOut(new PrintStream(buffer));
    checkDownloadedFile(true);
    assertEquals(
        String.format("\r%3.2f%% [%s] %,d/%3$,d bytes%n", 100F, "=".repeat(100), testFile.length()),
        buffer.toString());
    System.out.close();
    System.setOut(stdOut);
  }

  @Test
  public void testFileAlreadyExist() throws IOException {
    var url = mock(URL.class);
    var connection = mock(HttpURLConnection.class);
    when(url.getPath()).thenReturn(testFile.getName());
    when(url.openConnection()).thenReturn(connection);
    when(connection.getContentLengthLong()).thenReturn(testFile.length());
    when(connection.getInputStream()).thenReturn(new FileInputStream(testFile));

    var manager = new DownloadManager(url, TMP_DIR);
    assertThrows(FileAlreadyExistsException.class, () -> manager.download(false));
    manager.close();
  }

  @Test
  public void testDoesNotThrowIfIsNotInitialized() throws IOException {
    assertDoesNotThrow(new DownloadManager(new URL("http://example.org"), TMP_DIR)::close);
  }

  private void checkDownloadedFile(boolean show) throws IOException {
    var expectedName = "file.txt";
    var expectedData = "Some text";
    try (var writer = new FileWriter(testFile)) {
      writer.write(expectedData);
    }

    var url = mock(URL.class);
    var connection = mock(HttpURLConnection.class);
    when(url.getPath()).thenReturn(expectedName);
    when(url.openConnection()).thenReturn(connection);
    when(connection.getContentLengthLong()).thenReturn(testFile.length());
    when(connection.getInputStream()).thenReturn(new FileInputStream(testFile));

    var manager = new DownloadManager(url, TMP_DIR);
    var result = manager.download(show);
    manager.close();

    assertNotNull(result);
    assertEquals(testFile.length(), result.length());
    assertEquals(expectedName, result.getName());
    assertFalse(new File(String.format("%s/%s.tmp", TMP_DIR, expectedName)).exists());
    assertTrue(new File(String.format("%s/%s", TMP_DIR, expectedName)).exists());
    try (var reader = new BufferedReader(new FileReader(result))) {
      assertEquals(expectedData, reader.readLine());
    }
    assertTrue(result.delete());
  }
}