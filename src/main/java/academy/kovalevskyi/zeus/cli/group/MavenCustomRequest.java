package academy.kovalevskyi.zeus.cli.group;

import java.util.ArrayList;
import java.util.List;
import picocli.CommandLine.Parameters;

public class MavenCustomRequest {

  @Parameters(arity = "1", description = "Any maven commands")
  private final List<String> commands = new ArrayList<>();

  public List<String> getCommands() {
    return commands;
  }
}
