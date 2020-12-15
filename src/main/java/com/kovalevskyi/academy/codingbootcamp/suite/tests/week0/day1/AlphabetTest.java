package com.kovalevskyi.academy.codingbootcamp.suite.tests.week0.day1;

import static com.google.common.truth.Truth.assertThat;

import com.kovalevskyi.academy.codingbootcamp.suite.AbstractTestExecutor;
import com.kovalevskyi.academy.codingbootcamp.week0.day1.Alphabet;
import org.junit.Test;


public class AlphabetTest extends AbstractTestExecutor {

  @Test
  public void testGenerateAlphabet() {
    var expectedResult =
        new char[] {
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
            'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
          };

    var actualResult = Alphabet.generateAlphabet();
    assertThat(actualResult).isEqualTo(expectedResult);
  }

  @Test
  public void testGenerateReversedAlphabet() {
    var expectedResult =
        new char[] {
            'z', 'y', 'x', 'w', 'v', 'u', 't', 's', 'r', 'q', 'p', 'o', 'n', 'm', 'l', 'k', 'j',
            'i', 'h', 'g', 'f', 'e', 'd', 'c', 'b', 'a'
          };

    var actualResult = Alphabet.generateReversedAlphabet();
    assertThat(actualResult).isEqualTo(expectedResult);
  }
}
