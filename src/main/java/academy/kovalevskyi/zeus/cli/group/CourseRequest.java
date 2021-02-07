package academy.kovalevskyi.zeus.cli.group;

import picocli.CommandLine.Option;

public class CourseRequest {

  @Option(required = true, names = {"-c", "--course"}, description = "Course number")
  private int course;

  @Option(required = true, names = {"-w", "--week"}, description = "Week number")
  private int week;

  @Option(required = true, names = {"-d", "--day"}, description = "Day number")
  private int day;

  @Option(defaultValue = "-1", names = {"-i", "--id"}, description = "Container id")
  private int id;

  public int getCourse() {
    return course;
  }

  public int getWeek() {
    return week;
  }

  public int getDay() {
    return day;
  }

  public int getId() {
    return id;
  }
}
