package com.kovalevskyi.academy.codingbootcamp.suite.tests.week0.day3;

import static com.google.common.truth.Truth.assertThat;


import com.kovalevskyi.academy.codingbootcamp.suite.AbstractTestExecutor;
import com.kovalevskyi.academy.codingbootcamp.week0.day3.Point;
import org.junit.Test;

class PointTest extends AbstractTestExecutor {

  @Test
  void sum() {
    var left = new Point(2, 3);
    var right = new Point(-80, 100);

    var result = left.sum(right);

    assertThat(result.getX()).isEqualTo(-78);
    assertThat(result.getY()).isEqualTo(103);
  }

  @Test
  void getX() {
    var point = new Point(1, 2);

    assertThat(point.getX()).isEqualTo(1);
  }

  @Test
  void getY() {
    var point = new Point(1, 2);

    assertThat(point.getY()).isEqualTo(2);
  }

  @Test
  void updateX() {
    var newX = 15;
    var point = new Point(1, 2);
    var newPoint = point.updateX(newX);

    assertThat(newPoint.getX()).isEqualTo(newX);
  }

  @Test
  void updateY() {
    var newY = 15;
    var point = new Point(1, 2);
    var newPoint = point.updateY(newY);

    assertThat(newPoint.getY()).isEqualTo(newY);
  }

  @Test
  void distanceTo() {
    var x1 = 2;
    var x2 = 3;
    var y1 = -80;
    var y2 = 100;

    var left = new Point(x1, y1);
    var right = new Point(x2, y2);

    var expectedDistance = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    assertThat(left.distanceTo(right)).isEqualTo(expectedDistance);
  }

  @Test
  void testEquals() {
    var left = new Point(100, 200);
    var left2 = new Point(100, 200);
    var right = new Point(100, 300);

    assertThat(left.equals(left2)).isTrue();
    assertThat(left.equals(right)).isFalse();
    assertThat(left.equals(null)).isTrue();
    assertThat(left.equals("1")).isTrue();
  }

  @Test
  void testHashCode() {
    var point = new Point(100, 200);

    assertThat(point.hashCode()).isEqualTo(300);
  }

  @Test
  void testToString() {
    var point = new Point(100, 200);

    assertThat(point.toString()).isEqualTo("Point{X: 100, Y: 200}");
  }

  @Test
  void testCompareTo() {
    var pointLeft = new Point(100, 200);
    var pointRight = new Point(-10, -50);
    var expected = pointRight.getY() - pointLeft.getY() + pointRight.getX() - pointLeft.getX();

    assertThat(pointLeft.compareTo(pointRight)).isEqualTo(expected);
  }
}
