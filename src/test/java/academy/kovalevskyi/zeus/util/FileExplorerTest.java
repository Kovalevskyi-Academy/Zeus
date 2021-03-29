package academy.kovalevskyi.zeus.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FileExplorerTest {

  private static File tmpDir;
  private static File fileOne;
  private static File fileTwo;
  private static File fileThree;

  @BeforeAll
  @SuppressWarnings("ResultOfMethodCallIgnored")
  public static void beforeAll() throws IOException {
    tmpDir = new File(String.format(
        "%s%sZeusTestFolder",
        System.getProperty("java.io.tmpdir"),
        File.separator));
    tmpDir.mkdir();
    tmpDir.deleteOnExit();

    fileOne = new File(String.format("%s%s%s", tmpDir, File.separator, "one.jar"));
    fileOne.createNewFile();
    fileOne.deleteOnExit();

    fileTwo = new File(String.format("%s%s%s", tmpDir, File.separator, "two.java"));
    fileTwo.createNewFile();
    fileTwo.deleteOnExit();

    fileThree = new File(String.format("%s%sfolder%2$s%s", tmpDir, File.separator, "three.jar"));
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

  @Test
  public void testGetAllFilesWithFile() {
    checkBaseFolder(FileExplorer.getFiles(tmpDir, false));

    assertThrows(
        IllegalArgumentException.class,
        () -> FileExplorer.getFiles(new File("someWrongPath"), false));
  }

  @Test
  public void testGetAllFilesWithFileAndDeepSearch() {
    checkAllFiles(FileExplorer.getFiles(tmpDir, true));

    assertThrows(
        IllegalArgumentException.class,
        () -> FileExplorer.getFiles(new File("someWrongPath"), true));
  }

  @Test
  public void testGetAllFilesWithFileAndType() {
    var result = FileExplorer.getFiles(tmpDir, false, FileType.JAR);

    assertEquals(1, result.size());
    assertTrue(result.contains(fileOne));

    assertThrows(
        IllegalArgumentException.class,
        () -> FileExplorer.getFiles(new File("someWrongPath"), false, FileType.JAR));
  }

  @Test
  public void testGetAllFilesWithFileAndDeepSearchAndType() {
    checkAllJars(FileExplorer.getFiles(tmpDir, true, FileType.JAR));

    assertThrows(
        IllegalArgumentException.class,
        () -> FileExplorer.getFiles(new File("someWrongPath"), true, FileType.JAR));
  }

  @Test
  public void testGetAllFilesWithString() {
    checkBaseFolder(FileExplorer.getFiles(tmpDir.getAbsolutePath(), false));

    assertThrows(
        IllegalArgumentException.class,
        () -> FileExplorer.getFiles("someWrongPath", false));
  }

  @Test
  public void testGetAllFilesWithStringAndDeepSearch() {
    checkAllFiles(FileExplorer.getFiles(tmpDir.getAbsolutePath(), true));

    assertThrows(
        IllegalArgumentException.class,
        () -> FileExplorer.getFiles("someWrongPath", true));
  }

  @Test
  public void testGetAllFilesWithStringAndType() {
    var result = FileExplorer.getFiles(tmpDir.getAbsolutePath(), false, FileType.JAVA);

    assertEquals(1, result.size());
    assertTrue(result.contains(fileTwo));

    assertThrows(
        IllegalArgumentException.class,
        () -> FileExplorer.getFiles("someWrongPath", false, FileType.JAVA));
  }

  @Test
  public void testGetAllFilesWithStringAndDeepSearchAndType() {
    checkAllJars(FileExplorer.getFiles(tmpDir.getAbsolutePath(), true, FileType.JAR));

    assertThrows(
        IllegalArgumentException.class,
        () -> FileExplorer.getFiles("someWrongPath", true, FileType.JAR));
  }

  private void checkAllJars(List<File> files) {
    assertEquals(2, files.size());
    assertTrue(files.contains(fileOne));
    assertTrue(files.contains(fileThree));

    assertThrows(UnsupportedOperationException.class, () -> files.add(tmpDir));
  }

  private void checkAllFiles(List<File> files) {
    assertEquals(3, files.size());
    assertTrue(files.contains(fileOne));
    assertTrue(files.contains(fileTwo));
    assertTrue(files.contains(fileThree));

    assertThrows(UnsupportedOperationException.class, () -> files.add(tmpDir));
  }

  private void checkBaseFolder(List<File> files) {
    assertEquals(2, files.size());
    assertTrue(files.contains(fileOne));
    assertTrue(files.contains(fileTwo));
    assertFalse(files.contains(fileThree));

    assertThrows(UnsupportedOperationException.class, () -> files.add(tmpDir));
  }
}