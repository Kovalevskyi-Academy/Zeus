package academy.kovalevskyi.zeus.cli;

import academy.kovalevskyi.zeus.cli.command.Zeus;
import java.util.Objects;
import picocli.CommandLine;
import picocli.CommandLine.IExecutionExceptionHandler;
import picocli.CommandLine.ParseResult;

public class ExceptionMessageHandler implements IExecutionExceptionHandler {

  public int handleExecutionException(Exception e, CommandLine command, ParseResult result) {
    final var zeus = (Zeus) command.getParent().getCommand();
    if (zeus.isDebug()) {
      e.printStackTrace();
    } else {
      var message = Objects.requireNonNullElse(e.getMessage(), e.toString());
      System.out.printf("Oops..something went wrong :(%n%s%n", message);
    }

    return 1;
  }
}