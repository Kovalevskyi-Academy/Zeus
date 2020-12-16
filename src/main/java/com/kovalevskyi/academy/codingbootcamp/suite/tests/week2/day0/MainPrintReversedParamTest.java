package com.kovalevskyi.academy.codingbootcamp.suite.tests.week2.day0;

import com.kovalevskyi.academy.codingbootcamp.suite.tests.common.BasicStdTest;
import com.kovalevskyi.academy.codingbootcamp.week2.day0.MainPrintReversedParam;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public class MainPrintReversedParamTest extends BasicStdTest {

  @Test
  public void main() {
    var inputArgs = new String[] {"arg1", "arg2"};
    var expectedString = "arg2\narg1\n";
    MainPrintReversedParam.main(inputArgs);
    assertThat(outputStreamCaptor.toString()).isEqualTo(expectedString);
  }

  @Test
  public void mainWithZeroArguments() {
    var inputArgs = new String[] {};
    var expectedString = "Please specify at least one argument!\n";
    MainPrintReversedParam.main(inputArgs);
    assertThat(outputStreamCaptor.toString()).isEqualTo(expectedString);
  }
}
