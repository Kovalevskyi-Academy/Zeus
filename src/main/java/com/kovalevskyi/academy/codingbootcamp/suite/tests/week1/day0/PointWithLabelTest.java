package com.kovalevskyi.academy.codingbootcamp.suite.tests.week1.day0;

import com.kovalevskyi.academy.codingbootcamp.week0.day3.Point;
import com.kovalevskyi.academy.codingbootcamp.week1.day0.PointWithLabel;
import com.kovalevskyi.academy.testing.AbstractTestExecutor;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertWithMessage;

public class PointWithLabelTest extends AbstractTestExecutor {

  @Test
  public void testGetLabel() {
    var inputValue = "myLabel";

    var point = new PointWithLabel(1, 2, inputValue);

    assertWithMessage(
            String.format(
                "checking if new PointWithLabel(1, 2, myLabel)" + " will return myLabel correctly"))
        .that(point.getLabel())
        .isEqualTo(inputValue);
    assertWithMessage("Checking is test point also is " + "Point.class")
        .that(point)
        .isInstanceOf(Point.class);
  }

  @Test
  public void testCompareTo() {
    var x1 = 1;
    var y1 = 0;

    var x2 = 2;
    var y2 = 2;

    var x3 = 3;
    var y3 = 4;

    var point1 = new PointWithLabel(x1, y1, "hi");
    var point2 = new PointWithLabel(x2, y2, "ololo");
    var point3 = new Point(x3, y3);

    assertWithMessage("asserting that (1, 0, \"hi\") compareTo with Point(3, 4) will return 6")
        .that(point1.compareTo(point3))
        .isEqualTo(6);
    assertWithMessage(
            "asserting that (1, 0, \"hi\") compareTo with PointWithLabel(x2, y2, \"ololo\") will return -7")
        .that(point1.compareTo(point2))
        .isEqualTo(-7);
  }
}
