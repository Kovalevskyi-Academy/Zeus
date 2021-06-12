package academy.kovalevskyi.zeus.cli;

import academy.kovalevskyi.testing.util.AnsiConsoleInstaller;
import academy.kovalevskyi.zeus.cli.command.Zeus;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Objects;
import org.fusesource.jansi.Ansi;
import picocli.CommandLine;
import picocli.CommandLine.IExecutionExceptionHandler;
import picocli.CommandLine.ParseResult;

public class ExceptionHandler implements IExecutionExceptionHandler {

  public int handleExecutionException(Exception e, CommandLine command, ParseResult result) {
    var report = Ansi.ansi().fgRed();
    if (Zeus.isDev()) {
      var byteArray = new ByteArrayOutputStream();
      try (var stream = new PrintStream(byteArray)) {
        e.printStackTrace(stream);
      }
      report.a(byteArray.toString());
    } else {
      var message = Objects.requireNonNullElse(e.getMessage(), e.toString());
      report.format("Oops..something went wrong :(%n%s%n", message);
    }
    AnsiConsoleInstaller.INSTANCE.systemInstall();
    System.out.println(report.reset());
    AnsiConsoleInstaller.INSTANCE.systemUninstall();
    return 1;
  }
}