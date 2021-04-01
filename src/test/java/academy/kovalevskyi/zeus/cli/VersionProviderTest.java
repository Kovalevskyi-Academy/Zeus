package academy.kovalevskyi.zeus.cli;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import academy.kovalevskyi.zeus.engine.maven.Configuration;
import academy.kovalevskyi.zeus.engine.maven.MavenEngine;
import org.junit.jupiter.api.Test;
import picocli.CommandLine.IVersionProvider;

public class VersionProviderTest {

  @SuppressWarnings("ConstantConditions")
  @Test
  public void testParent() {
    assertTrue(new VersionProvider() instanceof IVersionProvider);
  }

  @Test
  public void testGetVersion() {
    var version = new VersionProvider().getVersion();
    var zeus = MavenEngine.getConfig(Configuration.ZEUS);

    assertEquals(2, version.length);
    assertEquals(String.format("Name: %s", zeus.getArtifactId()), version[0]);
    assertEquals(String.format("Version: %s", zeus.getVersion()), version[1]);
  }
}