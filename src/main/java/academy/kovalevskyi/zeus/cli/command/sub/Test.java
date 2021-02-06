package academy.kovalevskyi.zeus.cli.command.sub;

import academy.kovalevskyi.testing.AbstractTestExecutor;
import academy.kovalevskyi.testing.util.CourseManager;
import academy.kovalevskyi.testing.view.TestHandler;
import academy.kovalevskyi.zeus.cli.group.CourseRequest;
import academy.kovalevskyi.zeus.util.FileExplorer;
import java.util.concurrent.Callable;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(name = "test", description = "Run containers by course/week/day/id")
public class Test implements Callable<Integer> {

  @Option(names = {"-e", "--error"}, description = "Show only errors")
  private boolean error;

  @ArgGroup(exclusive = false, multiplicity = "1")
  private final CourseRequest request = new CourseRequest();

  public Integer call() throws Exception {
    if (FileExplorer.isJarAbsentInClasspath()) {
      System.out.println("Add your jar file first into classpath, because classpath is empty!");
    } else {
      TestHandler.setErrorMode(error);
      if (request.getId() >= 0) {
        var container = CourseManager.getContainerBy(
            request.getCourse(),
            request.getWeek(),
            request.getDay(),
            request.getId());
        execute(container);
      } else {
        var containers = CourseManager.getContainersBy(
            request.getCourse(),
            request.getWeek(),
            request.getDay());
        for (var container : containers) {
          execute(container);
        }
      }
    }
    return 0;
  }

  private void execute(Class<? extends AbstractTestExecutor> container) throws Exception {
    container.getConstructor().newInstance().execute();
  }
}
