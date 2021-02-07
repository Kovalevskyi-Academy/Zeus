package academy.kovalevskyi.zeus.cli.command.sub;

import academy.kovalevskyi.zeus.cli.group.CourseRequest;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;

@Command(name = "show", description = "Show container by course/week/day")
public class Show implements Runnable {

  @ArgGroup(exclusive = false, multiplicity = "1")
  private final CourseRequest manager = new CourseRequest();

  @Override
  public void run() {

  }
}
