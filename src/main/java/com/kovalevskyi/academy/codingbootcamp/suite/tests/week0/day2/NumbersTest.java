package com.kovalevskyi.academy.codingbootcamp.suite.tests.week0.day2;

import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.kovalevskyi.academy.codingbootcamp.week0.day2.Numbers;
import com.kovalevskyi.academy.testing.AbstractTestExecutor;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;


public class NumbersTest extends AbstractTestExecutor {

  @Test
  public void testSort() {
    String message = "\nTest method 'sort(int[] target)'!\nInput array does not sorted!";
    // RANDOM ARRAY
    for (int i = 0; i < 11; i++) {
      var input2 = new Random()
          .ints(25, -49, 50)
          .toArray();
      var expectedArray = IntStream.of(input2).sorted().toArray();
      Numbers.sort(input2);
      assertWithMessage(String.format(message, Arrays.toString(input2))).that(input2).isEqualTo(expectedArray);
    }
  }

  @Test
  public void testSortWithNull() {
    Numbers.sort(null);
  }

  @Test
  public void testGenerateTriplets() {
    var expected = new HashSet<String>();
    IntStream.range(0, 8)
        .forEach(
            n1 -> {
              IntStream.range(n1 + 1, 9)
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

    // Only assertEquals fo length! No tips!
    assertEquals(expected.size(), actualResult.length,
        "\nTripletTest:\nWrong result array length!\n");


    Stream.of(actualResult).forEach(chArray -> {
      assertWithMessage("\nTripletTest:\nThe subarrays are of the wrong length.")
          .that(chArray).hasLength(3);
      assertTrue(expected.contains(new String(chArray)),
          "\nTripletTest:\nThe resulting array contains an invalid element: "
              + Arrays.toString(chArray));
      //TODO Come up with a check for consistency and ordering of the result.
    });

    assertWithMessage("TripletTest:")
        .that(new String(actualResult[0])).isEqualTo("012");
    assertWithMessage("TripletTest:")
        .that(new String(actualResult[1])).isEqualTo("013");
  }

  @Test
  public void testGenerateTuples() {
    String message = "\ntestGenerateTuples\n";
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

    var actualResult = Numbers.generateNtuples();

    Stream.of(actualResult).forEach(chArray -> {
      assertWithMessage(message + "The subarrays are of the wrong length.")
          .that(chArray).hasLength(5);
      assertTrue(expected.contains(new String(chArray)),
          message + "The resulting array contains an invalid element: "
              + Arrays.toString(chArray));
      //TODO Come up with a check for consistency and ordering of the result.
    });
    // Only assertEquals fo length! No tips!
    assertEquals(expected.size(), actualResult.length,
        message + "Wrong result array length!\n");
  }

  @Test
  public void testGenerateNtuples() {
    String message = "\ntestGenerateNtuples\n";
    var n = 5;
    var expected = new HashSet<String>();
    IntStream.range(0, (int) Math.pow(10, n))
        .forEach(
            value -> {
              expected.add(String.format("%05d", value));
            });

    var actualResult = Numbers.generateNtuples(n);

    Stream.of(actualResult).forEach(chArray -> {
      assertWithMessage(message + "The subarrays are of the wrong length.")
          .that(chArray).hasLength(5);
      assertTrue(expected.contains(new String(chArray)),
          message + "The resulting array contains an invalid element: "
              + Arrays.toString(chArray));
      //TODO Come up with a check for consistency and ordering of the result.
    });
    // Only assertEquals fo length! No tips!
    assertEquals(expected.size(), actualResult.length,
        message + "Wrong result array length!\n");
  }

  @Test
  public void testGenerateNTuplesWithNegativeNumber() {
    String message = "\ntestGenerateNTuplesWithNegativeNumber\n";
    var n = -1;

    try {
      Numbers.generateNtuples(n);
      fail(message + "amount < 0! Where is your IllegalArgumentException?");
    } catch (IllegalArgumentException ignored) { }
  }

  @Test
  public void testGenerateNTuplesWithZero() {
    String message = "\ntestGenerateNTuplesWithZero\n";
    var n = 0;

    var actualResult = Numbers.generateNtuples(n);
    assertWithMessage(message + "amount = 0").that(actualResult).isEmpty();
  }
}
