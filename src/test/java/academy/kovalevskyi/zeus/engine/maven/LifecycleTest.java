package academy.kovalevskyi.zeus.engine.maven;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class LifecycleTest {

  @Test
  public void testValues() {
    for (var lifecycle : Lifecycle.values()) {
      assertNotNull(lifecycle.command);
      assertFalse(lifecycle.command.isBlank());
    }
  }
}