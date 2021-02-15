package academy.kovalevskyi.zeus.cli.group;

import academy.kovalevskyi.testing.service.ContainerRequest;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

public class CourseRequest {

  @ArgGroup(multiplicity = "1")
  private final CourseArg course = new CourseArg();

  @Option(defaultValue = "-1", names = {"-w", "--week"}, description = "Week number")
  private int week;

  @Option(defaultValue = "-1", names = {"-d", "--day"}, description = "Day number")
  private int day;

  @Option(defaultValue = "-1", names = {"-i", "--id"}, description = "Container id")
  private int id;

  public ContainerRequest prepareRequest() {
    final var request = ContainerRequest.builder();
    if (course.key != null) {
      request.course(course.key);
    } else {
      request.course(course.id);
    }
    if (week >= 0) {
      request.week(week);
    }
    if (day >= 0) {
      request.day(day);
    }
    if (id >= 0) {
      request.id(id);
    }
    return request.build();
  }

  public static class CourseArg {

    @Option(defaultValue = "-1", names = {"-c", "--course"}, description = "Course id")
    private int id;

    @Parameters(description = "Course key")
    private String key;
  }
}
