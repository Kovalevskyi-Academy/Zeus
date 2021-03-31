package academy.kovalevskyi.zeus.engine.maven;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

import java.io.File;
import java.util.ArrayList;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.junit.jupiter.api.Test;

public class MavenEngineTest {

  private static final String M2_HOME = System.getenv("M2_HOME");

  @Test
  public void testHasDifferentConfigs() {
    var zeus = MavenEngine.getConfig(Configuration.ZEUS);
    var user = MavenEngine.getConfig(Configuration.USER);

    assertNotEquals(zeus, user);
  }

  @Test
  public void testSingleton() {
    assertEquals(
        MavenEngine.getConfig(Configuration.ZEUS),
        MavenEngine.getConfig(Configuration.ZEUS));

    assertEquals(
        MavenEngine.getConfig(Configuration.USER),
        MavenEngine.getConfig(Configuration.USER));
  }

  @Test
  public void testCommandExecutionWithDefaultHome() throws MavenInvocationException {
    checkMavenHome();
    var result = MavenEngine.execute(null, new ArrayList<>() {
      {
        add("-version");
      }
    });
    assertEquals(0, result);
  }

  @Test
  public void testCommandExecution() throws MavenInvocationException {
    checkMavenHome();
    var result = MavenEngine.execute(new File(M2_HOME), new ArrayList<>() {
      {
        add("-version");
      }
    });
    assertEquals(0, result);
  }

  // IDE may show build is failed, but it is not true
  @Test
  public void testBadCommandExecution() throws MavenInvocationException {
    checkMavenHome();
    var result = MavenEngine.execute(new File(M2_HOME), new ArrayList<>() {
      {
        add("some_wrong_command");
      }
    });
    assertEquals(1, result);
  }

  @Test
  public void testNullAndEmptyCommands() {
    checkMavenHome();
    assertThrows(
        IllegalArgumentException.class,
        () -> MavenEngine.execute(null, new ArrayList<>()));

    assertThrows(
        IllegalArgumentException.class,
        () -> MavenEngine.execute(null, (Request) null));

    assertThrows(
        IllegalArgumentException.class,
        () -> MavenEngine.execute(null, (ArrayList<String>) null));
  }

  private void checkMavenHome() {
    assumeTrue(M2_HOME != null, "Aborted because M2_HOME not configured on this system");
  }
}