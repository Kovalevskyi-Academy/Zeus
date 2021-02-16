package academy.kovalevskyi.zeus.cli.command.sub;

import academy.kovalevskyi.testing.util.ContainerLauncher;
import academy.kovalevskyi.zeus.cli.group.CourseRequest;
import academy.kovalevskyi.zeus.util.FileExplorer;
import java.util.concurrent.Callable;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
    name = "test",
    description = "Run containers",
    mixinStandardHelpOptions = true)
public class Test implements Callable<Void> {

  static final String EMPTY_CLASSPATH = "Add your jar file first into classpath!";

  @Option(names = {"-e", "--error"}, description = "Show only errors")
  private boolean error;

  @ArgGroup(exclusive = false, multiplicity = "1")
  private final CourseRequest request = new CourseRequest();

  public Void call() throws Exception {
    if (FileExplorer.isJarAbsentInClasspath()) {
      System.out.println(EMPTY_CLASSPATH);
    } else {
      ContainerLauncher.execute(error, request.prepareRequest());
    }
    return null;
  }
}
