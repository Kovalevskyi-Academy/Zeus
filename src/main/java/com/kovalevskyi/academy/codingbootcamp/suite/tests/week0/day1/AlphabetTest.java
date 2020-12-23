package com.kovalevskyi.academy.codingbootcamp.suite.tests.week0.day1;


import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import com.kovalevskyi.academy.codingbootcamp.week0.day1.Alphabet;
import com.kovalevskyi.academy.testing.AbstractTestExecutor;
import java.util.Arrays;
import org.junit.jupiter.api.Test;


public class AlphabetTest extends AbstractTestExecutor {

  @Test
  public void testGenerateAlphabet() {
    var expectedResult =
        new char[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
        };
    var actualResult = Alphabet.generateAlphabet();
    String message = "Testing 'generateAlphabet()' fail!\nExpected result: %s"
        + "\nActual result: %s\n";
    assertArrayEquals(expectedResult, actualResult,
        () -> String.format(message,
            Arrays.toString(expectedResult),
            Arrays.toString(actualResult)));
  }

  @Test
  public void testGenerateReversedAlphabet() {
    var expectedResult =
        new char[]{
            'z', 'y', 'x', 'w', 'v', 'u', 't', 's', 'r', 'q', 'p', 'o', 'n', 'm', 'l', 'k', 'j',
            'i', 'h', 'g', 'f', 'e', 'd', 'c', 'b', 'a'
        };

    var actualResult = Alphabet.generateReversedAlphabet();
    String message = "Testing 'generateReversedAlphabet()' fail!\nExpected result: %s"
        + "\nActual result: %s\n";
    assertArrayEquals(expectedResult, actualResult,
        () -> String.format(message,
            Arrays.toString(expectedResult),
            Arrays.toString(actualResult)));
  }
}
