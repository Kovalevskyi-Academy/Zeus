package academy.kovalevskyi.zeus.cli.group;

import academy.kovalevskyi.testing.service.ContainerRequest;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

public class CourseRequest {

  @Parameters(description = "Course key", arity = "1")
  private String key;

  @Option(defaultValue = "-1", names = {"-w", "--week"}, description = "Week number")
  private int week;

  @Option(defaultValue = "-1", names = {"-d", "--day"}, description = "Day number")
  private int day;

  @Option(defaultValue = "-1", names = {"-i", "--id"}, description = "Container id")
  private int id;

  public ContainerRequest prepareRequest() {
    final var request = ContainerRequest.builder().course(key);
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
}
