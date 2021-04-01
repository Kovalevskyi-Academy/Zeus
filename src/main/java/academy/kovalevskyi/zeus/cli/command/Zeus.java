package academy.kovalevskyi.zeus.cli.command;

import academy.kovalevskyi.zeus.cli.VersionProvider;
import academy.kovalevskyi.zeus.cli.command.sub.Checkstyle;
import academy.kovalevskyi.zeus.cli.command.sub.Maven;
import academy.kovalevskyi.zeus.cli.command.sub.Pass;
import academy.kovalevskyi.zeus.cli.command.sub.Show;
import academy.kovalevskyi.zeus.cli.command.sub.Test;
import academy.kovalevskyi.zeus.cli.command.sub.Update;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
    name = "Zeus",
    mixinStandardHelpOptions = true,
    versionProvider = VersionProvider.class,
    footer = "Copyright (c) 2021",
    subcommands = {Show.class, Checkstyle.class, Test.class, Pass.class, Maven.class, Update.class}
)
public class Zeus implements Runnable {

  @Option(defaultValue = "false", names = {"-d", "--dev"}, description = "Developer mode")
  private static boolean dev;

  public static boolean isDev() {
    return dev;
  }

  @Override
  public void run() {
    System.out.println("Zeus the Mighty greets you, stranger ;)");
  }
}
