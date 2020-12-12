package com.kovalevskyi.academy.codingbootcamp.suite.week0.day1;

import com.kovalevskyi.academy.codingbootcamp.week0.day2.Numbers;
import org.junit.Test;

import java.util.HashSet;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class NumbersTest {

  @Test
  public void testGenerateNumbers() {
    var expectedResult = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    assertArrayEquals(expectedResult, Numbers.generateNumbers());
  }

  @Test
  public void testIsNegative() {
    assertTrue(Numbers.isNegative(-1));
    assertTrue(Numbers.isNegative(-200));
    assertFalse(Numbers.isNegative(0));
    assertFalse(Numbers.isNegative(200));
  }

  @Test
  public void testGenerateTriplets() {
    var expected = new HashSet<String>();
    IntStream.range(0, 10)
        .forEach(
            n1 -> {
              IntStream.range(0, 10)
                  .forEach(
                      n2 -> {
                        IntStream.range(0, 10)
                            .forEach(
                                n3 -> {
                                  if (n1 != n2 && n1 != n3 && n2 != n3) {
                                    expected.add(String.format("%d%d%d", n1, n2, n3));
                                  }
                                });
                      });
            });

    var actualResult = Numbers.generateTriplets();

    Stream.of(actualResult).forEach(value -> assertTrue(expected.contains(new String(value))));
    assertEquals("012", new String(actualResult[0]));
    assertEquals("013", new String(actualResult[1]));
  }

  @Test
  public void testGenerateTuples() {
    var expected = new HashSet<String>();
    IntStream.range(0, 10)
        .forEach(
            n1 -> {
              IntStream.range(0, 10)
                  .forEach(
                      n2 -> {
                        IntStream.range(0, 10)
                            .forEach(
                                n3 -> {
                                  IntStream.range(0, 10)
                                      .forEach(
                                          n4 ->
                                              expected.add(
                                                  String.format("%d%d %d%d", n1, n2, n3, n4)));
                                });
                      });
            });

    var actualResult = Numbers.generateTuples();

    Stream.of(actualResult).forEach(value -> assertTrue(expected.contains(new String(value))));
  }

  @Test
  public void testGenerateNTuples() {
    var n = 5;
    var expected = new HashSet<String>();
    IntStream.range(0, (int) Math.pow(10, n))
        .forEach(
            value -> {
              var str = String.valueOf(value);
              while (str.length() < n) {
                str = String.format("0%s", str);
              }
              expected.add(str);
            });

    var actualResult = Numbers.generateTuples(n);

    Stream.of(actualResult).forEach(value -> assertTrue(expected.contains(value)));
  }

  @Test
  public void testGenerateNTuplesWithNegativeNumber() {
    var n = -1;

    try {
      Numbers.generateTuples(n);
      fail();
    } catch (IllegalArgumentException e) {

    }
  }

  @Test
  public void testGenerateNTuplesWithZero() {
    var n = 0;

    var actualResult = Numbers.generateTuples(n);
    assertTrue(actualResult.length == 0);
  }

  @Test
  public void testToString() {
    assertArrayEquals(new char[] {'1', '2'}, Numbers.convertToString(12));
    assertArrayEquals(new char[] {'0'}, Numbers.convertToString(0));
    assertArrayEquals(new char[] {'-', '3', '2'}, Numbers.convertToString(-32));
    assertArrayEquals(
        String.valueOf(Integer.MAX_VALUE).toCharArray(),
        Numbers.convertToString(Integer.MAX_VALUE));
    assertArrayEquals(
        String.valueOf(Integer.MIN_VALUE).toCharArray(),
        Numbers.convertToString(Integer.MIN_VALUE));
  }

  @Test
  public void testSort() {
    var input = new int[] {3, 8, -1, 5, 0};
    var expected = new int[] {-1, 0, 3, 5, 8};

    Numbers.sort(input);

    assertArrayEquals(expected, input);
  }

  @Test
  public void testSortWithNull() {
    Numbers.sort(null);
  }
}
