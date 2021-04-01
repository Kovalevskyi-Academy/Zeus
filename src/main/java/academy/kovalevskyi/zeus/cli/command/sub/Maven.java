package academy.kovalevskyi.zeus.cli.command.sub;

import academy.kovalevskyi.zeus.cli.group.MavenCustomRequest;
import academy.kovalevskyi.zeus.cli.group.MavenPresetRequest;
import academy.kovalevskyi.zeus.engine.maven.MavenEngine;
import java.io.File;
import java.util.concurrent.Callable;
import org.apache.maven.shared.invoker.MavenInvocationException;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
    name = "maven",
    description = "Run maven presets or any custom commands",
    mixinStandardHelpOptions = true)
public class Maven implements Callable<Void> {

  @ArgGroup(multiplicity = "1")
  private final Request request = new Request();

  @Option(names = {"-m", "--maven"}, description = "Maven home directory")
  private File mavenHome;

  public Void call() throws MavenInvocationException {
    if (request.mavenCustomRequest != null) {
      MavenEngine.execute(mavenHome, request.mavenCustomRequest.getCommands());
    }
    MavenEngine.execute(mavenHome, request.mavenPresetRequest.getRequest());
    return null;
  }

  private static class Request {

    @ArgGroup
    private MavenPresetRequest mavenPresetRequest;

    @ArgGroup
    private MavenCustomRequest mavenCustomRequest;
  }
}
