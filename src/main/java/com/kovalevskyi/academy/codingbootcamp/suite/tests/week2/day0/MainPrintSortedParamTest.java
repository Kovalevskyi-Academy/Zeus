package com.kovalevskyi.academy.codingbootcamp.suite.tests.week2.day0;

import com.kovalevskyi.academy.codingbootcamp.suite.tests.common.BasicStdTest;
import com.kovalevskyi.academy.codingbootcamp.week2.day0.MainPrintSortedParam;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class MainPrintSortedParamTest extends BasicStdTest {

  @Test
  public void main() {
    var inputArgs = new String[] {"c", "a", "b"};
    var expectedString = "a\nb\nc\n";
    MainPrintSortedParam.main(inputArgs);
    assertThat(outputStreamCaptor.toString()).isEqualTo(expectedString);
  }

  @Test
  public void mainWithZeroArguments() {
    var inputArgs = new String[] {};
    var expectedString = "Please specify at least one argument!\n";
    MainPrintSortedParam.main(inputArgs);
    assertThat(outputStreamCaptor.toString()).isEqualTo(expectedString);
  }
}
