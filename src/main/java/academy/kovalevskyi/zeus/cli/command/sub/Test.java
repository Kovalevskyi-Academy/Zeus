package academy.kovalevskyi.zeus.cli.command.sub;

import academy.kovalevskyi.testing.service.FrameworkProperty;
import academy.kovalevskyi.testing.util.ContainerLauncher;
import academy.kovalevskyi.zeus.cli.command.Zeus;
import academy.kovalevskyi.zeus.cli.group.CourseRequest;
import academy.kovalevskyi.zeus.util.FileExplorer;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.ParentCommand;

@Command(
    name = "test",
    description = "Run test containers",
    mixinStandardHelpOptions = true)
public class Test implements Runnable {

  static final String EMPTY_CLASSPATH = "Add your jar file first into classpath!";

  @Option(names = {"-e", "--error"}, description = "Show only errors")
  private boolean error;

  @ArgGroup(exclusive = false, multiplicity = "1")
  private final CourseRequest request = new CourseRequest();

  @ParentCommand
  private final Zeus zeus = new Zeus();

  @Override
  public void run() {
    System.out.println(zeus.isDebug());
    if (FileExplorer.isJarAbsentInClasspath()) {
      System.out.println(EMPTY_CLASSPATH);
    } else {
      System.setProperty(FrameworkProperty.ERROR_MODE, String.valueOf(error));
      System.setProperty(FrameworkProperty.DEBUG_MODE, String.valueOf(zeus.isDebug()));
      ContainerLauncher.execute(request.prepareRequest());
    }
  }
}
