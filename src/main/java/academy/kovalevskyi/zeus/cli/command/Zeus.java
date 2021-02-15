package academy.kovalevskyi.zeus.cli.command;

import academy.kovalevskyi.zeus.cli.VersionProvider;
import academy.kovalevskyi.zeus.cli.command.sub.Checkstyle;
import academy.kovalevskyi.zeus.cli.command.sub.Maven;
import academy.kovalevskyi.zeus.cli.command.sub.Show;
import academy.kovalevskyi.zeus.cli.command.sub.Test;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
    name = "Zeus",
    mixinStandardHelpOptions = true,
    versionProvider = VersionProvider.class,
    footer = "%nCopyright (c) 2021",
    subcommands = {Test.class, Show.class, Maven.class, Checkstyle.class}
)
public class Zeus implements Runnable {

  @Option(names = {"-d", "--debug"}, description = "Debug mode")
  private boolean debug;

  @Override
  public void run() {
    System.out.println("Zeus the Mighty greets you, stranger ;)");
  }

  public boolean isDebug() {
    return debug;
  }
}
