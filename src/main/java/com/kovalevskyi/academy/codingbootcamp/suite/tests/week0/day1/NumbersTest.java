package com.kovalevskyi.academy.codingbootcamp.suite.tests.week0.day1;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.kovalevskyi.academy.codingbootcamp.week0.day1.Numbers;
import com.kovalevskyi.academy.testing.AbstractTestExecutor;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.Test;


public class NumbersTest extends AbstractTestExecutor {

  @Test
  public void generateNumbers() {
    var expectedResult = IntStream.range(0, 100).toArray();
    var actualResult = Numbers.generateNumbers();
    String message = "Testing 'generateNumbers()' fail!\nExpected result: %s"
        + "\nActual result: %s\n";
    assertArrayEquals(expectedResult, actualResult,
        () -> String.format(message,
            Arrays.toString(expectedResult),
            Arrays.toString(actualResult)));
  }

  @Test
  public void findBiggestInTuple() {
    String message = "\nInput 'findBiggest(%d, %d)` have result";
    assertEquals(5, Numbers.findBiggest(5, 3), String.format(message, 5, 3));
    assertEquals(3, Numbers.findBiggest(-2, 3), String.format(message, -2, 3));
    assertEquals(-2, Numbers.findBiggest(-2, -3), String.format(message, -2, -3));
    assertEquals(0, Numbers.findBiggest(0, 0), String.format(message, 0, 0));
  }

  @Test
  public void findBiggestInTriplet() {
    String message = "\nInput 'findBiggest(%d, %d, %d)` have result";
    assertEquals(5, Numbers.findBiggest(5, 3, 1), String.format(message, 5, 3, 1));
    assertEquals(3, Numbers.findBiggest(-2, 3, -5), String.format(message, -2, 3, -5));
    assertEquals(-2, Numbers.findBiggest(-2, -3, -6), String.format(message, -2, -3, -6));
    assertEquals(0, Numbers.findBiggest(0, 0, 0), String.format(message, 0, 0, 0));
  }

  @Test
  public void findBiggestInArray() {
    String message = "\nInput array in 'findBiggest(int[] numbers)' is: \n%s"
        + "\nThe biggest element is invalid!";
    // RANDOM ARRAY
    for (int i = 0; i < 11; i++) {
      var input2 = new Random()
          .ints(25, -49, 50)
          .toArray();
      var max = IntStream.of(input2).max().getAsInt();
      assertEquals(max, Numbers.findBiggest(input2),
          String.format(message, Arrays.toString(input2)));
    }
  }

  @Test
  public void findBiggestWithEmptyArray() {
    var input = new int[] {};
    try {
      Numbers.findBiggest(input);
      fail("\nThe 'findBiggest(int[] numbers)' should throw IllegalArgumentException when input "
          + "is empty array\n");
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new RuntimeException("\nThe 'findBiggest(int[] numbers)' should throw "
          + "IllegalArgumentException when input is an empty array\n" + e.toString());
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void findBiggestWithNull() {
    try {
      Numbers.findBiggest(null);
      fail("\nThe 'findBiggest(int[] numbers)' should throw IllegalArgumentException when input "
          + "is null\n");
    } catch (NullPointerException e) {
      throw new RuntimeException("\nThe 'findBiggest(int[] numbers)' should throw "
          + "IllegalArgumentException when input is null\n" + e.toString());
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void findIndexOfBiggestNumber() {
    String message = "\nInput array in 'findIndexOfBiggestNumber(int[] numbers)' is: \n%s"
        + "\nAn index of biggest element is invalid!";
    // RANDOM ARRAY
    for (int i = 0; i < 11; i++) {
      var input2 = new Random()
          .ints(25, -49, 50)
          .toArray();
      List<Integer> arrayAsList = IntStream.of(input2).boxed().collect(Collectors.toList());
      var max = Collections.max(arrayAsList);
      var indexOfMax = arrayAsList.indexOf(max);
      assertEquals(indexOfMax,
          Numbers.findIndexOfBiggestNumber(input2),
          String.format(message, arrayAsList.toString()));
    }
  }

  @Test
  public void findIndexOfBiggestNumberWithEmptyArray() {
    var input = new int[] {};
    try {
      Numbers.findIndexOfBiggestNumber(input);
      fail("\nThe 'findIndexOfBiggestNumber(int[] numbers)' should throw IllegalArgumentException"
          +
          " when input is empty array\n");
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new RuntimeException("\nThe 'findIndexOfBiggestNumber(int[] numbers)' should throw "
          + "IllegalArgumentException when input is an empty array\n" + e.toString());
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void findIndexOfBiggestNumberWithNull() {
    try {
      Numbers.findIndexOfBiggestNumber(null);
      fail("\nThe 'findIndexOfBiggestNumber(int[] numbers)' should throw IllegalArgumentException"
          + " when input is empty array\n");
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new RuntimeException("\nThe 'findIndexOfBiggestNumber(int[] numbers)' should throw "
          + "IllegalArgumentException when input is an empty array\n" + e.toString());
    } catch (IllegalArgumentException ignored) {
    }
  }

  @Test
  public void testIsNegative() {
    String message = "\nTesting 'isNegative(int number)'!\n Income number is: %s and result is:";
    assertTrue(Numbers.isNegative(-100), String.format(message, -100));
    assertTrue(Numbers.isNegative(-1), String.format(message, -1));
    assertTrue(Numbers.isNegative(-11), String.format(message, -11));

    assertFalse(Numbers.isNegative(11), String.format(message, 11));
    assertFalse(Numbers.isNegative(111), String.format(message, 111));
    assertFalse(Numbers.isNegative(0), String.format(message, 0));
  }

  @Test
  public void testConvertToString() {
    Random rnd = new Random();
    String message =
        "Testing 'convertToString(int number)' fail!\nIncome number was: %d\nExpected result: %s"
            + "\nActual result: %s\n";
    for (int i = 0; i < 100; i++) {
      var number = rnd.nextInt();
      var expect = String.valueOf(number).toCharArray();
      try {
        var actual = Numbers.convertToString(number);
        assertArrayEquals(expect, actual,
            () -> String.format(message,
                number,
                Arrays.toString(expect),
                Arrays.toString(actual)));
      } catch (ArrayIndexOutOfBoundsException e) {
        throw new RuntimeException(
            "Your algorithm has gone beyond the limits of some kind of array."
                + " Be careful!\n" + e.toString());
      }
    }
  }
}
