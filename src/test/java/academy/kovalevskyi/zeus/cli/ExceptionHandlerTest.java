package academy.kovalevskyi.zeus.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mockStatic;

import academy.kovalevskyi.zeus.cli.command.Zeus;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ConnectException;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import picocli.CommandLine.IExecutionExceptionHandler;

public class ExceptionHandlerTest {

  @BeforeAll
  public static void beforeAll() {
    AnsiConsole.systemUninstall();
  }

  @SuppressWarnings("ConstantConditions")
  @Test
  public void testParent() {
    assertTrue(new ExceptionHandler() instanceof IExecutionExceptionHandler);
  }

  @Test
  public void testExitCode() {
    var handler = new ExceptionHandler();
    assertEquals(1, handler.handleExecutionException(new IllegalArgumentException(), null, null));
    assertEquals(1, handler.handleExecutionException(new NullPointerException(), null, null));
    assertEquals(1, handler.handleExecutionException(new ConnectException(), null, null));
    assertEquals(1, handler.handleExecutionException(new IOException(), null, null));
    assertEquals(1, handler.handleExecutionException(new FileNotFoundException(), null, null));
  }

  @Test
  public void testPrintErrorWithoutMessage() {
    var buffer = new ByteArrayOutputStream();
    var stream = new PrintStream(buffer);
    var stdOut = System.out;
    System.setOut(stream);
    var exception = new NullPointerException();
    new ExceptionHandler().handleExecutionException(exception, null, null);
    var message = Ansi.ansi()
        .fgRed()
        .format("Oops..something went wrong :(%n%s%n", exception.toString())
        .reset()
        .format("%n")
        .toString();
    assertEquals(message, buffer.toString());
    stream.close();
    System.setOut(stdOut);
  }

  @Test
  public void testPrintErrorWithMessage() {
    var buffer = new ByteArrayOutputStream();
    var stream = new PrintStream(buffer);
    var stdOut = System.out;
    System.setOut(stream);
    var exception = new NullPointerException("Some message");
    new ExceptionHandler().handleExecutionException(exception, null, null);
    var message = Ansi.ansi()
        .fgRed()
        .format("Oops..something went wrong :(%n%s%n", exception.getMessage())
        .reset()
        .format("%n")
        .toString();
    assertEquals(message, buffer.toString());
    stream.close();
    System.setOut(stdOut);
  }

  @SuppressWarnings("ResultOfMethodCallIgnored")
  @Test
  public void testPrintErrorWithDevMode() {
    var buffer = new ByteArrayOutputStream();
    var stream = new PrintStream(buffer);
    var stdOut = System.out;
    System.setOut(stream);

    var exception = new NullPointerException();
    try (var mock = mockStatic(Zeus.class)) {
      mock.when(Zeus::isDev).thenReturn(true);
      new ExceptionHandler().handleExecutionException(exception, null, null);
    }

    var buffer2 = new ByteArrayOutputStream();
    try (var stream2 = new PrintStream(buffer2)) {
      exception.printStackTrace(stream2);
    }

    var message = Ansi.ansi().fgRed().a(buffer2.toString()).reset().format("%n").toString();
    assertEquals(message, buffer.toString());
    stream.close();
    System.setOut(stdOut);
  }
}