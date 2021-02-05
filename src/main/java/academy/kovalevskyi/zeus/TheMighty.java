package academy.kovalevskyi.zeus;

import academy.kovalevskyi.zeus.cli.ExceptionMessageHandler;
import academy.kovalevskyi.zeus.cli.command.Zeus;
import picocli.CommandLine;

public class TheMighty {

  public static void main(String... args) {
    final var cli = new CommandLine(new Zeus());
    cli.setExecutionExceptionHandler(new ExceptionMessageHandler());
    final var code = cli.execute(args);
    if (cli.getParseResult().expandedArgs().isEmpty()) {
      cli.usage(System.out);
    }
    System.exit(code);
  }
}
