package com.kovalevskyi.academy.codingbootcamp.suite.tests.week2.day1;

import com.kovalevskyi.academy.codingbootcamp.suite.tests.common.BasicStdTest;
import com.kovalevskyi.academy.codingbootcamp.week2.day1.TextPrinter;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class TextPrinterTest extends BasicStdTest {

  @Test
  public void main() {
    var inputArgs = new String[] {"5sdfgadsf3"};
    var expectedString = "/############\\\n# 5sdfgadsf3 #\n\\############/\n";
    TextPrinter.main(inputArgs);
    assertThat(outputStreamCaptor.toString()).isEqualTo(expectedString);
  }

  @Test
  public void mainWithEmptyArgument() {
    var inputArgs = new String[] {""};
    var expectedString = "/##\\\n#  #\n\\##/\n";
    TextPrinter.main(inputArgs);
    assertThat(outputStreamCaptor.toString()).isEqualTo(expectedString);
  }

  @Test
  public void mainWithIncorrectArgumentAmount() {
    var inputArgs = new String[] {"", "asdfasdf"};
    var expectedString = "Please provide only one input argument, current amount: 2\n";
    TextPrinter.main(inputArgs);
    assertThat(outputStreamCaptor.toString()).isEqualTo(expectedString);
  }

  @Test
  public void mainWithZeroArguments() {
    var inputArgs = new String[] {};
    var expectedString = "Please provide only one input argument, current amount: 0\n";
    TextPrinter.main(inputArgs);
    assertThat(outputStreamCaptor.toString()).isEqualTo(expectedString);
  }
}
