package academy.kovalevskyi.zeus.cli.group;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import picocli.CommandLine.Parameters;

public class MavenCustomRequestTest {

  @Test
  public void testCommandsField() throws NoSuchFieldException {
    var commandsField = MavenCustomRequest.class.getDeclaredField("commands");
    assertTrue(Modifier.isPrivate(commandsField.getModifiers()));
    var commandsFieldParameters = commandsField.getAnnotation(Parameters.class);
    assertNotNull(commandsFieldParameters);
    assertFalse(commandsFieldParameters.hidden());
    assertEquals("1..", commandsFieldParameters.arity());
    assertEquals(1, commandsFieldParameters.description().length);
    assertEquals("Any maven commands", commandsFieldParameters.description()[0]);
  }

  @Test
  public void testGetCommands() throws NoSuchFieldException, IllegalAccessException {
    var customRequest = new MavenCustomRequest();
    var expected = new ArrayList<String>();
    expected.add("cmd1");
    expected.add("cmd2");
    var commandsField = MavenCustomRequest.class.getDeclaredField("commands");
    commandsField.setAccessible(true);
    commandsField.set(customRequest, expected);
    var actual = customRequest.getCommands();

    assertIterableEquals(expected, actual);
  }

}