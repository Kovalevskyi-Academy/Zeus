package academy.kovalevskyi.zeus.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class FileTypeTest {

  @Test
  public void testExtensions() {
    for (var type : FileType.values()) {
      assertNotNull(type.extension);
      assertFalse(type.extension.isBlank());
    }
  }
}