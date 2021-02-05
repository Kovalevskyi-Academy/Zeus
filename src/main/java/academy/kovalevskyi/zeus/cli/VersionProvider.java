package academy.kovalevskyi.zeus.cli;

import academy.kovalevskyi.zeus.engine.maven.MavenEngine;
import org.apache.maven.model.Model;
import picocli.CommandLine.IVersionProvider;

public class VersionProvider implements IVersionProvider {

  private final Model mavenConfig = MavenEngine.getConfig();

  public String[] getVersion() {
    return new String[]{
        String.format("Name: %s", mavenConfig.getArtifactId()),
        String.format("Version: %s", mavenConfig.getVersion())};
  }
}