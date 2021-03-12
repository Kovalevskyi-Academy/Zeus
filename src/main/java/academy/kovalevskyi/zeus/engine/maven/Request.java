package academy.kovalevskyi.zeus.engine.maven;

import java.util.ArrayList;
import java.util.List;

public record Request(List<String> commands) {

  public static Builder builder() {
    return new Builder();
  }

  public List<String> getCommands() {
    return commands;
  }

  public static class Builder {

    private final List<String> commands;

    private Builder() {
      commands = new ArrayList<>();
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
      return new Request(commands);
    }

  }
}
