package academy.kovalevskyi.zeus.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assumptions.assumeFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.IOException;
import java.lang.instrument.Instrumentation;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import net.bytebuddy.agent.ByteBuddyAgent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class JarLoaderTest {

  private static final File TEST_JAR = new File("target/ProgressBar-1.0.jar");
  private static final File TEST_BROKEN_JAR = new File("target/testJar.jar");

  @SuppressWarnings("ResultOfMethodCallIgnored")
  @BeforeEach
  public void setUp() {
    if (TEST_JAR.exists()) {
      TEST_JAR.delete();
    }
    if (TEST_BROKEN_JAR.exists()) {
      TEST_BROKEN_JAR.delete();
    }
  }

  @Order(0)
  @Test
  public void testIsNotDynamicallyLoaded() {
    assumeFalse(TEST_JAR.exists(), "Do maven clean before test");
    try (var mock = mockStatic(ByteBuddyAgent.class)) {
      assertFalse(JarLoader.isDynamicallyLoaded());
      mock.verifyNoInteractions();
    }
  }

  @Order(1)
  @Test
  public void testIsNotDynamicallyLoadedDueToError() throws IOException {
    assumeFalse(TEST_JAR.exists(), "Do maven clean before test");
    assertTrue(TEST_BROKEN_JAR.createNewFile());
    try (var mock = mockStatic(ByteBuddyAgent.class)) {
      assertFalse(JarLoader.isDynamicallyLoaded());
      mock.verify(ByteBuddyAgent::install);
    }
    assertTrue(TEST_BROKEN_JAR.delete());
  }

  @Order(2)
  @Test
  public void testIsDynamicallyLoaded() throws IOException {
    assumeFalse(TEST_BROKEN_JAR.exists(), "Do maven clean before test");
    var source = Paths.get("src/test/resources/ProgressBar-1.0.jar");
    Files.copy(source, TEST_JAR.toPath(), StandardCopyOption.REPLACE_EXISTING);
    assertTrue(TEST_JAR.exists());

    var instruments = mock(Instrumentation.class);
    try (var mock = mockStatic(ByteBuddyAgent.class)) {
      mock.when(ByteBuddyAgent::install).thenReturn(instruments);
      assertTrue(JarLoader.isDynamicallyLoaded());
      mock.verify(ByteBuddyAgent::install);
      verify(instruments, only()).appendToSystemClassLoaderSearch(any());
    }
  }
}