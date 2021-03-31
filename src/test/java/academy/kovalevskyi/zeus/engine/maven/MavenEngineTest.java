package academy.kovalevskyi.zeus.engine.maven;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
    var result = MavenEngine.execute(null, new ArrayList<>() {
      {
        add("-version");
      }
    });
    assertEquals(0, result);
  }

  @Test
  public void testCommandExecution() throws MavenInvocationException {
    var result = MavenEngine.execute(new File(M2_HOME), new ArrayList<>() {
      {
        add("-version");
      }
    });
    assertEquals(0, result);
  }

  @Test
  public void testBadCommandExecution() throws MavenInvocationException {
    var result = MavenEngine.execute(new File(M2_HOME), new ArrayList<>() {
      {
        add("some_wrong_command");
      }
    });
    assertEquals(1, result);
  }

  @Test
  public void testNullAndEmptyCommands() {
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
}