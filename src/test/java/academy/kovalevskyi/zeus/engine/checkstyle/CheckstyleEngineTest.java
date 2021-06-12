package academy.kovalevskyi.zeus.engine.checkstyle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import academy.kovalevskyi.testing.service.State;
import academy.kovalevskyi.testing.util.AnsiConsoleInstaller;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CheckstyleEngineTest {

  private static final File SOURCE_OK = new File("src/test/resources/TestClassOne.java");
  private static final File SOURCE_BAD = new File("src/test/resources/TestClassTwo.java");
  private static final ByteArrayOutputStream BUFFER = new ByteArrayOutputStream();
  private static final PrintStream STREAM = new PrintStream(BUFFER);
  private static final PrintStream STD_OUT = System.out;

  @BeforeAll
  public static void beforeAll() {
    AnsiConsoleInstaller.INSTANCE.systemUninstall();
    System.setOut(STREAM);
  }

  @AfterAll
  public static void afterAll() {
    STREAM.close();
    System.setOut(STD_OUT);
  }

  @AfterEach
  public void tearDown() {
    BUFFER.reset();
  }

  @Test
  public void testFileOk() throws IOException {
    var result = CheckstyleEngine.check(Style.GOOGLE, SOURCE_OK);
    var expected =
        String.format("TestClassOne.java - %s%n", colorText("OK", State.SUCCESSFUL.color));

    assertEquals(0, result);
    assertEquals(expected, BUFFER.toString());
  }

  @Test
  public void testFileBad() throws IOException {
    var result = CheckstyleEngine.check(Style.GOOGLE, SOURCE_BAD);
    var expected = String.format("TestClassTwo.java - %s%n"
            + "%s%n"
            + "%s%n"
            + "%s%n",
        colorText("BAD", State.FAILED.color),
        colorText("[ERROR] 3:3: Comment has incorrect indentation level 2, expected is 5, "
                + "indentation should be the same level as line 4. [CommentsIndentation]",
            State.FAILED.color),
        colorText("[ERROR] 4:6: 'member def modifier' has incorrect indentation level 5, "
                + "expected level should be 2. [Indentation]",
            State.FAILED.color),
        colorText("[ERROR] 4:18: Member name 'a' must match pattern "
                + "'^[a-z][a-z0-9][a-zA-Z0-9]*$'. [MemberName]",
            State.FAILED.color));

    assertEquals(3, result);
    assertEquals(expected, BUFFER.toString());
  }

  @Test
  public void testFilesMixed() throws IOException {
    var sources = new ArrayList<File>();
    sources.add(SOURCE_OK);
    sources.add(SOURCE_BAD);
    var result = CheckstyleEngine.check(Style.GOOGLE, sources);
    var expected = String.format("TestClassOne.java - %s%n"
            + "TestClassTwo.java - %s%n"
            + "%s%n"
            + "%s%n"
            + "%s%n%n"
            + "FILES 2 | SUCCESSFUL 1 | FAILED 1 | ERRORS 3%n"
            + "--------------------------------------------%n",
        colorText("OK", State.SUCCESSFUL.color),
        colorText("BAD", State.FAILED.color),
        colorText("[ERROR] 3:3: Comment has incorrect indentation level 2, expected is 5, "
                + "indentation should be the same level as line 4. [CommentsIndentation]",
            State.FAILED.color),
        colorText("[ERROR] 4:6: 'member def modifier' has incorrect indentation level 5, "
                + "expected level should be 2. [Indentation]",
            State.FAILED.color),
        colorText("[ERROR] 4:18: Member name 'a' must match pattern "
                + "'^[a-z][a-z0-9][a-zA-Z0-9]*$'. [MemberName]",
            State.FAILED.color));

    assertEquals(3, result);
    assertEquals(expected, BUFFER.toString());
  }

  @Test
  public void testFilesOk() throws IOException {
    var sources = new ArrayList<File>();
    sources.add(SOURCE_OK);
    var result = CheckstyleEngine.check(Style.GOOGLE, sources);
    var expected = String.format("TestClassOne.java - %s%n"
            + "Your source files are verified by checkstyle successfully!%n"
            + "----------------------------------------------------------%n",
        colorText("OK", State.SUCCESSFUL.color));

    assertEquals(0, result);
    assertEquals(expected, BUFFER.toString());
  }

  @Test
  public void testEmpty() throws IOException {
    var result = CheckstyleEngine.check(Style.GOOGLE, new ArrayList<>());
    var expected = String.format("You have nothing to verify with checkstyle!%n"
        + "-------------------------------------------%n");

    assertEquals(0, result);
    assertEquals(expected, BUFFER.toString());
  }

  @Test
  public void testFileNotFound() {
    assertThrows(
        FileNotFoundException.class,
        () -> CheckstyleEngine.check(Style.GOOGLE, new File("kek")));
  }

  @Test
  public void testIllegalArgument() {
    assertThrows(
        IllegalArgumentException.class,
        () -> CheckstyleEngine.check(Style.GOOGLE, new File(".")));

    var wrongFile = new File("src/test/resources/ProgressBar-1.0.jar");

    assertThrows(
        IllegalArgumentException.class,
        () -> CheckstyleEngine.check(Style.GOOGLE, wrongFile));
  }

  private String colorText(String text, Color color) {
    return Ansi.ansi().fg(color).a(text).reset().toString();
  }
}