package com.kovalevskyi.academy.codingbootcamp.suite.tests.week0.day3;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.kovalevskyi.academy.codingbootcamp.suite.AbstractTestExecutor;
import com.kovalevskyi.academy.codingbootcamp.week0.day3.Point;
import java.util.Random;
import org.junit.jupiter.api.Test;


public class PointTest extends AbstractTestExecutor {

  @Test
  public void getX() {
    var point = new Point(1, 2);

    assertWithMessage("testing getX()")
        .that(point.getX()).isEqualTo(1);
  }

  @Test
  public void getY() {
    var point = new Point(1, 2);

    assertWithMessage("testing getY()")
        .that(point.getY()).isEqualTo(2);
  }

  @Test
  public void sum() {
    String message = "\nTesting sum()\nNew Point coordinate%s must be %d\n";
    var left = new Point(2, 3);
    var right = new Point(-80, 100);

    var result = left.sum(right);

    assertWithMessage(String.format(message, "X", -78))
        .that(result.getX()).isEqualTo(-78);

    assertWithMessage(String.format(message, "Y", 103))
        .that(result.getY()).isEqualTo(103);
  }

  @Test
  public void updateX() {
    var newX = 15;
    var point = new Point(1, 2);
    var newPoint = point.updateX(newX);


    assertWithMessage("testing updateX() on newPoint\n"
        + "newPoint with newX have incorrect value!")
        .that(newPoint.getX()).isEqualTo(newX);
    assertWithMessage("testing updateX() on old point\n"
        + "old Point should be final!")
        .that(point.getX()).isEqualTo(1);
  }

  @Test
  public void updateY() {
    var newY = 15;
    var point = new Point(1, 2);
    var newPoint = point.updateY(newY);

    assertWithMessage("testing updateY() on newPoint\n"
        + "newPoint with newX have incorrect value!")
        .that(newPoint.getY()).isEqualTo(newY);
    assertWithMessage("testing updateY() on old point\n"
        + "old Point should be final!")
        .that(point.getY()).isEqualTo(2);
  }

  @Test
  public void distanceTo() {
    var x1 = 2;
    var x2 = 3;
    var y1 = -80;
    var y2 = 100;

    var left = new Point(x1, y1);
    var right = new Point(x2, y2);

    var expectedDistance = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    assertThat(left.distanceTo(right)).isEqualTo(expectedDistance);
    assertWithMessage("testing distanceTo()\n"
        + "Distance fail!")
        .that(left.distanceTo(right)).isEqualTo(expectedDistance);
  }

  @Test
  public void testEquals() {
    var left = new Point(100, 200);
    var left2 = new Point(100, 200);
    var right = new Point(100, 300);

    assertEquals(left, left2, "\ntestEquals()\nExpected true");
    assertNotEquals(left, right, "\ntestEquals()\nExpected false");
  }

  @Test
  public void testHashCode() {
    Point point;
    Random rnd = new Random();

    int x;
    int y;
    for (int i = 0; i < 10; i++) {
      x = rnd.nextInt(500);
      y = rnd.nextInt(500);
      point = new Point(x, y);
      assertWithMessage("\ntesting hashCode()\n")
          .that(point.hashCode()).isEqualTo(x + y);
    }

  }

  @Test
  public void testToString() {
    Point point;
    Random rnd = new Random();
    String expected = "Point{X: %d, Y: %d}";

    int x;
    int y;
    for (int i = 0; i < 10; i++) {
      x = rnd.nextInt(500);
      y = rnd.nextInt(500);
      point = new Point(x, y);
      assertWithMessage("\ntesting toString()\n")
          .that(point.toString()).isEqualTo(String.format(expected, x, y));
    }
  }

  @Test
  public void testCompareTo() {
    String message = "\nTesting compareTo()\n";
    var pointLeft = new Point(100, 200);
    var pointRight = new Point(-10, -50);
    assertWithMessage(message)
        .that(pointRight)
        .isAtMost(pointLeft);

    pointLeft = new Point(-300, 100);
    pointRight = new Point(400, -100);
    assertWithMessage(message)
        .that(pointLeft)
        .isAtMost(pointRight);

    pointLeft = new Point(100, 100);
    pointRight = new Point(100, 100);
    assertWithMessage(message + "It is the same objects!")
        .that(pointLeft.compareTo(pointRight))
        .isEqualTo(0);

  }
}
