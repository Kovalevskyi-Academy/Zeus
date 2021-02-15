package academy.kovalevskyi.zeus.cli.group;

import academy.kovalevskyi.zeus.engine.maven.Lifecycle;
import academy.kovalevskyi.zeus.engine.maven.Request;
import picocli.CommandLine.Option;

public class MavenPresetRequest {

  @Option(names = {"-c", "--clean"}, description = "Clean a project")
  private boolean isClean;

  @Option(names = {"-C", "--compile"}, description = "Compile a project")
  private boolean isCompile;

  @Option(names = {"-t", "--test"}, description = "Test a project")
  private boolean isTest;

  @Option(names = {"-b", "--build"}, description = "Package a project")
  private boolean isBuild;

  public Request getRequest() {
    final var request = Request.builder();
    if (isClean) {
      request.add(Lifecycle.CLEAN);
    } else if (isCompile) {
      request.add(Lifecycle.CLEAN).add(Lifecycle.COMPILE);
    } else if (isTest) {
      request.add(Lifecycle.CLEAN).add(Lifecycle.TEST);
    } else if (isBuild) {
      request.add(Lifecycle.CLEAN).add(Lifecycle.PACKAGE);
    }
    return request.build();
  }
}
