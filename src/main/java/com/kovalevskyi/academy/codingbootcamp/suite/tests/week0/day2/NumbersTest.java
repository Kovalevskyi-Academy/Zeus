package com.kovalevskyi.academy.codingbootcamp.suite.tests.week0.day2;

import com.kovalevskyi.academy.codingbootcamp.suite.AbstractTestExecutor;
import com.kovalevskyi.academy.codingbootcamp.week0.day2.Numbers;
import org.junit.Test;

import java.util.HashSet;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.*;

public class NumbersTest extends AbstractTestExecutor {

  @Test
  public void testGenerateTriplets() {
    var expected = new HashSet<String>();
    IntStream.range(0, 10)
        .forEach(
            n1 -> {
              IntStream.range(n1 + 1, 10)
                  .forEach(
                      n2 -> {
                        IntStream.range(n2 + 1, 10)
                            .forEach(
                                n3 -> {
                                  if (n1 != n2 && n1 != n3 && n2 != n3) {
                                    expected.add(String.format("%d%d%d", n1, n2, n3));
                                  }
                                });
                      });
            });

    var actualResult = Numbers.generateTriplets();

    Stream.of(actualResult).forEach(value -> {
        if (!expected.contains(new String(value))) {
            fail(String.format("Value from your result: %s is not expected\n", new String(value)));
        }
    });
    System.out.print("Checking if element 0 is 012:");
    assertThat(new String(actualResult[0])).isEqualTo("012");
    System.out.println(" so far so good");
    System.out.print("Checking if element 1 is 013");
    assertThat(new String(actualResult[1])).isEqualTo("013");
    System.out.println(" so far so good");
    assertThat(actualResult).hasLength(expected.size());
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

    Stream.of(actualResult).forEach(value -> {
        if (!expected.contains(new String(value))) {
            fail(String.format("Value from your result: %s is not expected\n", new String(value)));
        }
    });
    assertThat(actualResult).hasLength(expected.size());
  }

  @Test
  public void testGenerateNTuples() {
    var n = 5;
    var expected = new HashSet<String>();
    IntStream.range(0, (int) Math.pow(10, n))
        .forEach(
            value -> {
              expected.add(String.format("%05d", value));
            });

    var actualResult = Numbers.generateTuples(n);

    Stream.of(actualResult).forEach(value -> {
        if (!expected.contains(new String(value))) {
            fail(String.format("Value from your result: %s is not expected\n", new String(value)));
        }
    });
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
    assertThat(actualResult).isEmpty();
  }

  @Test
  public void testToString() {
    assertThat(Numbers.convertToString(12)).isEqualTo(new char[] {'1', '2'});
    assertThat(Numbers.convertToString(0)).isEqualTo(new char[] {'0'});
    assertThat(Numbers.convertToString(-32)).isEqualTo(new char[] {'-', '3', '2'});
    assertThat(Numbers.convertToString(Integer.MAX_VALUE))
            .isEqualTo(String.valueOf(Integer.MAX_VALUE).toCharArray());
      assertThat(Numbers.convertToString(Integer.MIN_VALUE))
              .isEqualTo(String.valueOf(Integer.MIN_VALUE).toCharArray());
  }

  @Test
  public void testSort() {
    var input = new int[] {3, 8, -1, 5, 0};
    var expected = new int[] {-1, 0, 3, 5, 8};

    Numbers.sort(input);

    assertThat(input).isEqualTo(expected);
  }

  @Test
  public void testSortWithNull() {
    Numbers.sort(null);
  }
}
