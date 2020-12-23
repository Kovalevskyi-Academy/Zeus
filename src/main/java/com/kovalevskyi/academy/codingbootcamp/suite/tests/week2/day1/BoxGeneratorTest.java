package com.kovalevskyi.academy.codingbootcamp.suite.tests.week2.day1;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import com.kovalevskyi.academy.codingbootcamp.week2.day1.BoxGenerator;
import com.kovalevskyi.academy.testing.common.BasicStdTest;
import org.junit.jupiter.api.Test;


public class BoxGeneratorTest extends BasicStdTest {

  @Test
  public void main5x4() {
    var inputArgs = new String[] {"5", "4", "*", "?"};
    var expectedString = "?***?\n*   *\n*   *\n?***?\n";
    BoxGenerator.main(inputArgs);
    assertThat(outputStreamCaptor.toString()).isEqualTo(expectedString);
  }

  @Test
  public void main1x1() {
    var inputArgs = new String[] {"1", "1", "*", "?"};
    var expectedString = "?\n";
    BoxGenerator.main(inputArgs);
    assertThat(outputStreamCaptor.toString()).isEqualTo(expectedString);
  }

  @Test
  public void main1x4() {
    var inputArgs = new String[] {"1", "4", "*", "?"};
    var expectedString = "?\n*\n*\n?\n";
    BoxGenerator.main(inputArgs);
    assertThat(outputStreamCaptor.toString()).isEqualTo(expectedString);
  }

  @Test
  public void main4x1() {
    var inputArgs = new String[] {"4", "1", "*", "?"};
    var expectedString = "?**?\n";
    BoxGenerator.main(inputArgs);
    assertThat(outputStreamCaptor.toString()).isEqualTo(expectedString);
  }

  @Test
  public void main0x4() {
    var inputArgs = new String[] {"0", "4", "*", "?"};
    var expectedString = "";
    BoxGenerator.main(inputArgs);
    assertThat(outputStreamCaptor.toString()).isEqualTo(expectedString);
  }

  @Test
  public void main4x0() {
    var inputArgs = new String[] {"0", "4", "*", "?"};
    var expectedString = "";
    BoxGenerator.main(inputArgs);
    assertThat(outputStreamCaptor.toString()).isEqualTo(expectedString);
  }

  @Test
  public void mainWithOneArgument() {
    var inputArgs = new String[] {"0"};
    var expectedString = "Please provide 4 input arguments, current amount: 1\n";
    BoxGenerator.main(inputArgs);
    assertThat(outputStreamCaptor.toString()).isEqualTo(expectedString);
  }

  @Test
  public void mainWithWrongArgumentsNumber() {
    var inputArgs = new String[] {"0", "2", "*", "?", "1"};
    var expectedString = "Please provide 4 input arguments, current amount: 5\n";
    BoxGenerator.main(inputArgs);
    assertThat(outputStreamCaptor.toString()).isEqualTo(expectedString);
  }

  @Test
  public void mainWithIllegalWidth() {
    var inputArgs = new String[] {"-1", "4", "*", "?"};
    try {
      BoxGenerator.main(inputArgs);
      fail("\"-1\", \"4\", \"*\", \"?\" does not throw IllegalArgumentException");
    } catch (IllegalArgumentException ignored) {

    }
  }

  @Test
  public void mainWithIllegalHeight() {
    var inputArgs = new String[] {"1", "-4", "*", "?"};
    try {
      BoxGenerator.main(inputArgs);
      fail("\"1\", \"-4\", \"*\", \"?\" does not throw IllegalArgumentException");
    } catch (IllegalArgumentException ignored) {

    }
  }

  @Test
  public void mainWithIllegalCorner() {
    var inputArgs = new String[] {"1", "4", "*", "??"};
    try {
      BoxGenerator.main(inputArgs);
      fail("\"1\", \"4\", \"*\", \"??\" does not throw IllegalArgumentException");
    } catch (IllegalArgumentException ignored) {

    }
  }

  @Test
  public void mainWithIllegalMid() {
    var inputArgs = new String[] {"1", "4", "**", "?"};
    try {
      BoxGenerator.main(inputArgs);
      fail("\"1\", \"4\", \"**\", \"?\" does not throw IllegalArgumentException");
    } catch (IllegalArgumentException ignored) {

    }
  }
}
