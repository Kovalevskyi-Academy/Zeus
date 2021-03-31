package academy.kovalevskyi.zeus.engine.maven;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class RequestTest {

  @Test
  public void testSequenceOfCommands() {
    var cmd1 = Lifecycle.CLEAN;
    var cmd2 = Lifecycle.COMPILE;
    var cmd3 = "test";
    var cmd4 = "deploy";

    var cmdList = Request.builder().add(cmd1).add(cmd2).add(cmd3).add(cmd4).build().getCommands();

    assertEquals(4, cmdList.size());
    assertEquals(cmd1.command, cmdList.get(0));
    assertEquals(cmd2.command, cmdList.get(1));
    assertEquals(cmd3, cmdList.get(2));
    assertEquals(cmd4, cmdList.get(3));
  }

  @Test
  public void testEmptyCommandList() {
    assertThrows(NullPointerException.class, () -> Request.builder().build());
  }
}