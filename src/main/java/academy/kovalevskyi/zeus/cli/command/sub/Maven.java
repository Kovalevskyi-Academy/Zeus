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

@Command(name = "maven",
    description = "Run presets or invoke any maven commands",
    mixinStandardHelpOptions = true)
public class Maven implements Callable<Integer> {

  @ArgGroup(multiplicity = "1")
  private final Group group = new Group();

  @Option(names = {"-m", "--maven"}, description = "Maven home directory")
  private final File mavenHome;

  public Maven() {
    mavenHome = new File(FileExplorer.M2_HOME);
  }

  public Integer call() throws MavenInvocationException {
    final var customRequest = group.mavenCustomRequest.getCommands();
    if (!customRequest.isEmpty()) {
      return MavenEngine.execute(mavenHome, customRequest);
    }
    return MavenEngine.execute(mavenHome, group.mavenPresetRequest.getRequest());
  }

  private static class Group {

    @ArgGroup
    private final MavenPresetRequest mavenPresetRequest = new MavenPresetRequest();

    @ArgGroup
    private final MavenCustomRequest mavenCustomRequest = new MavenCustomRequest();
  }
}
