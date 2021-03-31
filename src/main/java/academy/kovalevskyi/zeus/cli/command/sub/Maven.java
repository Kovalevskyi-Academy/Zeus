package academy.kovalevskyi.zeus.cli.command.sub;

import academy.kovalevskyi.zeus.cli.group.MavenCustomRequest;
import academy.kovalevskyi.zeus.cli.group.MavenPresetRequest;
import academy.kovalevskyi.zeus.engine.maven.MavenEngine;
import academy.kovalevskyi.zeus.util.FileExplorer;
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
public class Maven implements Callable<Integer> {

  @ArgGroup(multiplicity = "1")
  private final Request request = new Request();

  @Option(names = {"-m", "--maven"}, description = "Maven home directory")
  private final File mavenHome;

  public Maven() {
    mavenHome = FileExplorer.M2_HOME.toFile();
  }

  public Integer call() throws MavenInvocationException {
    if (request.mavenCustomRequest != null) {
      return MavenEngine.execute(mavenHome, request.mavenCustomRequest.getCommands());
    }
    return MavenEngine.execute(mavenHome, request.mavenPresetRequest.getRequest());
  }

  private static class Request {

    @ArgGroup
    private MavenPresetRequest mavenPresetRequest;

    @ArgGroup
    private MavenCustomRequest mavenCustomRequest;
  }
}
