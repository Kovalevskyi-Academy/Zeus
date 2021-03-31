package academy.kovalevskyi.zeus.engine.maven;

import java.util.ArrayList;
import java.util.List;

public class Request {

  private final List<String> commands;

  private Request(List<String> commands) {
    this.commands = commands;
  }

  public static Builder builder() {
    return new Builder();
  }

  public List<String> getCommands() {
    return commands;
  }

  public static class Builder {

    private final List<String> commands = new ArrayList<>();

    private Builder() {
    }

    public Builder add(Lifecycle lifecycle) {
      commands.add(lifecycle.command);
      return this;
    }

    public Builder add(String cmd) {
      commands.add(cmd);
      return this;
    }

    public Request build() {
      if (commands.isEmpty()) {
        throw new NullPointerException("Command list can't be empty");
      }
      return new Request(commands);
    }

  }
}
