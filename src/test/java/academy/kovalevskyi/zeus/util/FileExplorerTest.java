package academy.kovalevskyi.zeus.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FileExplorerTest {

  private static final Path TMP_DIR =
      Path.of(System.getProperty("java.io.tmpdir"), "ZeusTestFolder");
  private static final Path WRONG_DIR = Path.of("someWrongPath");
  private static File tmpDir;
  private static File fileOne;
  private static File fileTwo;
  private static File fileThree;

  @BeforeAll
  @SuppressWarnings("ResultOfMethodCallIgnored")
  public static void beforeAll() throws IOException {
    tmpDir = TMP_DIR.toFile();
    tmpDir.mkdir();
    tmpDir.deleteOnExit();

    fileOne = Path.of(TMP_DIR.toString(), "one.jar").toFile();
    fileOne.createNewFile();
    fileOne.deleteOnExit();

    fileTwo = Path.of(TMP_DIR.toString(), "two.java").toFile();
    fileTwo.createNewFile();
    fileTwo.deleteOnExit();

    fileThree = Path.of(TMP_DIR.toString(), "folder", "three.jar").toFile();
    var folder = new File(fileThree.getParent());
    folder.mkdir();
    folder.deleteOnExit();
    fileThree.createNewFile();
    fileThree.deleteOnExit();
  }

  @Test
  public void testMatch() {
    assertTrue(FileExplorer.match("some.JaR", FileType.JAR));
    assertTrue(FileExplorer.match("some.JaVA", FileType.JAVA));
    assertTrue(FileExplorer.match("some.java", ".java"));
    assertFalse(FileExplorer.match("some.java", ".exe"));
    assertFalse(FileExplorer.match("some.j", ".exe"));
    assertFalse(FileExplorer.match("some.", "."));
    assertFalse(FileExplorer.match("some.", ""));
    assertFalse(FileExplorer.match("", ".exe"));
    assertFalse(FileExplorer.match(null, ".exe"));
    assertFalse(FileExplorer.match("", (String) null));
  }

  @SuppressWarnings("ConstantConditions")
  @Test
  public void testGetFiles() {
    var result = FileExplorer.getFiles(TMP_DIR, false);

    assertEquals(2, result.size());
    assertTrue(result.contains(fileOne));
    assertTrue(result.contains(fileTwo));
    assertFalse(result.contains(fileThree));

    assertThrows(UnsupportedOperationException.class, () -> result.add(tmpDir));

    assertThrows(
        IllegalArgumentException.class,
        () -> FileExplorer.getFiles(WRONG_DIR, false));
  }

  @SuppressWarnings("ConstantConditions")
  @Test
  public void testGetFilesAndDeepSearch() {
    var result = FileExplorer.getFiles(TMP_DIR, true);

    assertEquals(3, result.size());
    assertTrue(result.contains(fileOne));
    assertTrue(result.contains(fileTwo));
    assertTrue(result.contains(fileThree));

    assertThrows(UnsupportedOperationException.class, () -> result.add(tmpDir));

    assertThrows(
        IllegalArgumentException.class,
        () -> FileExplorer.getFiles(WRONG_DIR, true));
  }

  @Test
  public void testGetFilesWithType() {
    var result = FileExplorer.getFiles(TMP_DIR, false, FileType.JAR);

    assertEquals(1, result.size());
    assertTrue(result.contains(fileOne));

    assertThrows(
        IllegalArgumentException.class,
        () -> FileExplorer.getFiles(WRONG_DIR, false, FileType.JAR));
  }

  @Test
  public void testGetFilesWithDeepSearchAndType() {
    var result = FileExplorer.getFiles(TMP_DIR, true, FileType.JAR);

    assertEquals(2, result.size());
    assertTrue(result.contains(fileOne));
    assertTrue(result.contains(fileThree));

    assertThrows(UnsupportedOperationException.class, () -> result.add(tmpDir));
    assertThrows(
        IllegalArgumentException.class,
        () -> FileExplorer.getFiles(WRONG_DIR, true, FileType.JAR));
  }
}