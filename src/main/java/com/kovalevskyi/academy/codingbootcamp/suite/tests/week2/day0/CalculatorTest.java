package com.kovalevskyi.academy.codingbootcamp.suite.tests.week2.day0;

import com.kovalevskyi.academy.codingbootcamp.suite.tests.common.BasicStdTest;
import com.kovalevskyi.academy.codingbootcamp.week2.day0.Calculator;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class CalculatorTest extends BasicStdTest {

  @Test
  public void main() {
    var inputArgs = new String[] {"2", "+", "355"};
    var expectedString = "result: 357\n";
    Calculator.main(inputArgs);
    assertThat(outputStreamCaptor.toString()).isEqualTo(expectedString);
  }

  @Test
  public void mainWithMinus() {
    var inputArgs = new String[] {"2", "-", "355"};
    var expectedString = "result: -353\n";
    Calculator.main(inputArgs);
    assertThat(outputStreamCaptor.toString()).isEqualTo(expectedString);
  }

  @Test
  public void mainWithMinusMinus() {
    var inputArgs = new String[] {"-12", "+", "-355"};
    var expectedString = "result: -367\n";
    Calculator.main(inputArgs);
    assertThat(outputStreamCaptor.toString()).isEqualTo(expectedString);
  }

  @Test
  public void mainWithMult() {
    var inputArgs = new String[] {"-12", "*", "5"};
    var expectedString = "result: -60\n";
    Calculator.main(inputArgs);
    assertThat(outputStreamCaptor.toString()).isEqualTo(expectedString);
  }

  @Test
  public void mainWithDevid() {
    var inputArgs = new String[] {"-12", "/", "-6"};
    var expectedString = "result: 2\n";
    Calculator.main(inputArgs);
    assertThat(outputStreamCaptor.toString()).isEqualTo(expectedString);
  }

  @Test
  public void mainWithPercent() {
    var inputArgs = new String[] {"6", "%", "4"};
    var expectedString = "result: 2\n";
    Calculator.main(inputArgs);
    assertThat(outputStreamCaptor.toString()).isEqualTo(expectedString);
  }

  @Test
  public void mainWithIncorrectInput() {
    var inputArgs = new String[] {"6"};
    var expectedString = "Please provide 3 input arguments, example: 2 + 3\n";
    Calculator.main(inputArgs);
    assertThat(outputStreamCaptor.toString()).isEqualTo(expectedString);
  }

  @Test
  public void mainWithIllegalInput() {
    var inputArgs = new String[] {"6", "%", "!"};
    try {
      Calculator.main(inputArgs);
      fail("input: new String[] {\"6\", \"%\", \"!\"} does not cause IllegalArgumentException");
    } catch (IllegalArgumentException ignored) {

    }
  }
}
