package academy.kovalevskyi.zeus.cli.group;

import academy.kovalevskyi.zeus.engine.maven.Lifecycle;
import java.util.ArrayList;
import java.util.List;
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

  public List<String> prepareRequest() {
    ArrayList<String> request = new ArrayList<>();
    if (this.isClean) {
      request.add(Lifecycle.CLEAN.command);
    } else if (this.isCompile) {
      request.add(Lifecycle.CLEAN.command);
      request.add(Lifecycle.COMPILE.command);
    } else if (this.isTest) {
      request.add(Lifecycle.CLEAN.command);
      request.add(Lifecycle.TEST.command);
    } else if (this.isBuild) {
      request.add(Lifecycle.CLEAN.command);
      request.add(Lifecycle.PACKAGE.command);
    }
    return request;
  }
}
