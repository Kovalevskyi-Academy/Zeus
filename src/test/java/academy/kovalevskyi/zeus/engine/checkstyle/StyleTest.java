package academy.kovalevskyi.zeus.engine.checkstyle;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class StyleTest {

  @Test
  public void testExtensions() {
    for (var style : Style.values()) {
      assertNotNull(style.config);
      assertFalse(style.config.isBlank());
    }
  }

}