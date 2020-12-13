package com.kovalevskyi.academy.codingbootcamp.suite.tests.week0.day1;

import com.kovalevskyi.academy.codingbootcamp.suite.AbstractTestExecutor;
import com.kovalevskyi.academy.codingbootcamp.week0.day1.Numbers;
import org.junit.Test;

import java.util.Arrays;
import java.util.stream.IntStream;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;

public class NumbersTest extends AbstractTestExecutor {

    @Test
    public void testGenerateNumbers() {
        var expectedResult = IntStream.range(0, 100).toArray();
        var actualResult = com.kovalevskyi.academy.codingbootcamp.week0.day2.Numbers.generateNumbers();
        assertThat(actualResult).isEqualTo(expectedResult);
    }

    @Test
    public void testFindBiggestInTuple() {
        assertThat(Numbers.findBiggest(-2, 3)).isEqualTo(3);
        assertThat(Numbers.findBiggest(-2, -3)).isEqualTo(-2);
        assertThat(Numbers.findBiggest(0, 0)).isEqualTo(0);
    }

    @Test
    public void testFindBiggestInTriplet() {
        assertThat(Numbers.findBiggest(-2, 3, 0)).isEqualTo(3);
        assertThat(Numbers.findBiggest(-2, 0,-3)).isEqualTo(0);
        assertThat(Numbers.findBiggest(10, -1, 0)).isEqualTo(10);
    }

  @Test
  public void testFindBiggestInArray() {
    var input = new int[] {0, 1, -200, 3, 400, 5, 6, 7, 8, 9};
    var actualResult = Numbers.findBiggest(input);
    assertThat(actualResult).isEqualTo(400);
  }

  @Test
  public void testFindBiggestWithEmptyArray() {
    var input = new int[] {};
    try {
      Numbers.findBiggest(input);
      fail("findBiggest should throw IllegalArgumentException when input is empty array");
    } catch (IllegalArgumentException e) {

    }
  }

  @Test
  public void testFindBiggestWithNull() {
    try {
      Numbers.findBiggest(null);
      fail("findBiggest should throw IllegalArgumentException when input is null");
    } catch (IllegalArgumentException e) {

    }
  }

    @Test
    public void testFindIndexOfBiggestNumber() {
        testFindIndexOfBiggestNumber(new int[] {0, 1, -200, 3, 400, 5, 6, 7, 8, 9}, 4);
        testFindIndexOfBiggestNumber(new int[] {0, 1, -200, 3, 4, 5, 6, 7, 8, 9}, 9);
        testFindIndexOfBiggestNumber(new int[] {0}, 0);
        testFindIndexOfBiggestNumber(new int[] {0, 1, 200, 3, 4, 5, 6, 7, 8, 9}, 2);
    }

    private void testFindIndexOfBiggestNumber(int[] input, int index) {
        System.out.printf("Testing for input: %s, index should be: %d\n", Arrays.toString(input), index);
        var actualResult = Numbers.findIndexOfBiggestNumber(input);
        assertThat(actualResult).isEqualTo(index);
    }

    @Test
    public void testFindIndexOfBiggestNumberWithEmptyArray() {
        var input = new int[] {};
        try {
            Numbers.findIndexOfBiggestNumber(input);
            fail("findIndexOfBiggestNumber should throw IllegalArgumentException when input is empty array");
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void testFindIndexOfBiggestNumberWithNull() {
        try {
            Numbers.findIndexOfBiggestNumber(null);
            fail("findIndexOfBiggestNumber should throw IllegalArgumentException when input is null");
        } catch (IllegalArgumentException e) {

        }
    }

  @Test
  public void testIsNegative() {
    testIsNegative(-1, true);
    testIsNegative(-200, true);
    testIsNegative(0, false);
    testIsNegative(200, false);
  }

  private void testIsNegative(int input, boolean expected) {
    var actual = Numbers.isNegative(input);
    System.out.printf("For input: %d, result is: %s, expected: %s\n", input, actual, expected);
    var assertResult = assertThat(actual);
    if (expected) {
      assertResult.isTrue();
    } else {
      assertResult.isFalse();
    }
  }

  @Test
  public void testConvertToString() {
    assertThat(Numbers.convertToString(12)).isEqualTo(new char[] {'1', '2'});
    assertThat(Numbers.convertToString(0)).isEqualTo(new char[] {'0'});
    assertThat(Numbers.convertToString(-32)).isEqualTo(new char[] {'-', '3', '2'});
    assertThat(Numbers.convertToString(Integer.MAX_VALUE))
        .isEqualTo(String.valueOf(Integer.MAX_VALUE).toCharArray());
    assertThat(Numbers.convertToString(Integer.MIN_VALUE))
        .isEqualTo(String.valueOf(Integer.MIN_VALUE).toCharArray());
  }
}
