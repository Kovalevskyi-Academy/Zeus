package com.kovalevskyi.academy.codingbootcamp.suite.tests.week1.day3;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import com.kovalevskyi.academy.codingbootcamp.week1.day3.StdString;
import com.kovalevskyi.academy.testing.AbstractTestExecutor;
import org.junit.jupiter.api.Test;


public class StdStringTest extends AbstractTestExecutor {

  @Test
  public void toAsciiLowerCase() {
    var testStr = new StdString(new char[]{'H', 'e', 'L', 'L', 'o'});

    assertThat(testStr.toAsciiLowerCase().toString()).isEqualTo("hello");
    assertThat(testStr.toString().toString()).isEqualTo("HeLLo");
  }

  @Test
  public void toAsciiLowerCaseWithLegalNonAlphabetCharacters() {
    var testStr = new StdString(new char[]{'H', 'e', 'L', 'L', 'o', '1', ' '});

    assertThat(testStr.toAsciiLowerCase().toString()).isEqualTo("hello1 ");
    assertThat(testStr.toString()).isEqualTo("HeLLo1 ");
  }

  @Test
  public void toAsciiLowerCaseWithIllegalCharacters() {
    var testStr = new StdString(new char[]{(char) 299, 'e', 'L', 'L', 'o', '1', ' '});

    try {
      testStr.toAsciiLowerCase();
      fail(
          "toAsciiLowerCase does not throw IllegalArgumentException with new char[]{(char)299, 'e', 'L', 'L', 'o', '1', ' '}");
    } catch (IllegalArgumentException e) { }
  }

  @Test
  public void toAsciiUpperCase() {
    var testStr = new StdString(new char[]{'H', 'e', 'L', 'L', 'o'});

    assertThat(testStr.toAsciiUpperCase().toString()).isEqualTo("HELLO");
    assertThat(testStr.toString()).isEqualTo("HeLLo");
  }

  @Test
  public void toAsciiUpperCaseWithLegalNonAlphabetCharacters() {
    var testStr = new StdString(new char[]{' ', '!', 'H', 'e', 'L', 'L', 'o'});

    assertThat(testStr.toAsciiUpperCase().toString()).isEqualTo(" !HELLO");
  }

  @Test
  public void toAsciiUpperCaseWithIllegalCharacters() {
    var testStr = new StdString(new char[]{(char) 299, '!', 'H', 'e', 'L', 'L', 'o'});

    try {
      testStr.toAsciiUpperCase();
      fail(
          "toAsciiUpperCase() is not throwing IllegalArgumentException with new char[]{(char)299, '!', 'H', 'e', 'L', 'L', 'o'}");
    } catch (IllegalArgumentException e) { }
  }

  @Test
  public void subString() {
    var testStr = new StdString(new char[]{'H', 'e', 'L', 'L', 'o', '1', '2', '3'});

    assertThat(testStr.subString(1, 6).toString()).isEqualTo("eLLo1");
    assertThat(testStr.subString(2, 4).toString()).isEqualTo("LL");
  }

  @Test
  public void subStringWithIncorrectIndex() {
    var testStr = new StdString(new char[]{'H', 'e', 'L', 'L', 'o', '1', '2', '3'});

    try {
      testStr.subString(-1, 6);
      fail(
          "testStr.subString(-1, 6) does not throw IndexOutOfBoundsException with new char[]{'H', 'e', 'L', 'L', 'o', '1', '2', '3'}");
    } catch (IndexOutOfBoundsException e) { }
  }

  @Test
  public void subStringWithFromBiggerThanTo() {
    var testStr = new StdString(new char[]{'H', 'e', 'L', 'L', 'o', '1', '2', '3'});

    try {
      testStr.subString(7, 6);
      fail(
          "testStr.subString(7, 6) does not throw IllegalArgumentException with new char[]{'H', 'e', 'L', 'L', 'o', '1', '2', '3'}");
    } catch (IllegalArgumentException e) { }
  }

  @Test
  public void concat() {
    var testStrLeft = new StdString(new char[]{'H', 'e', 'L', 'L', 'o'});
    var testStrRight = new StdString(new char[]{'1', '2', '3'});
    assertThat(testStrLeft.concat(testStrRight).toString()).isEqualTo("HeLLo123");
    assertThat(testStrLeft.toString()).isEqualTo("HeLLo");
    assertThat(testStrRight.toString()).isEqualTo("123");
  }

  @Test
  public void concatWithEmpty() {
    var testStrLeft = new StdString(new char[]{'H', 'e', 'L', 'L', 'o'});
    var testStrRight = new StdString(new char[]{});
    assertThat(testStrLeft.concat(testStrRight).toString()).isEqualTo("HeLLo");
  }

  @Test
  public void concatWithNullStr() {
    var testStrLeft = new StdString(new char[]{'H', 'e', 'L', 'L', 'o'});
    var testStrRight = (StdString) null;

    try {
      testStrLeft.concat(testStrRight);
      fail("testStrLeft.concat(null) is not throwing NullPointerException");
    } catch (NullPointerException e) { }
  }

  @Test
  public void split() {
    var testStrLeft =
        new StdString(
            new char[]{' ', ' ', 'H', 'e', 'L', 'L', 'o', ' ', '1', '2', '3', ' ', 'a', 'b', ' '});

    var actualResult = testStrLeft.split(' ');
    assertThat(actualResult.length).isEqualTo(5);
    assertThat(actualResult[0].toString()).isEqualTo("");
    assertThat(actualResult[1].toString()).isEqualTo("");
    assertThat(actualResult[2].toString()).isEqualTo("HeLLo");
    assertThat(actualResult[3].toString()).isEqualTo("123");
    assertThat(actualResult[4].toString()).isEqualTo("ab");
    assertThat(testStrLeft.toString()).isEqualTo("  HeLLo 123 ab ");
  }

  @Test
  public void splitWithNonExistingSeparator() {
    var testStrLeft =
        new StdString(
            new char[]{' ', ' ', 'H', 'e', 'L', 'L', 'o', ' ', '1', '2', '3', ' ', 'a', 'b', ' '});

    var actualResult = testStrLeft.split('!');
    assertThat(actualResult.length).isEqualTo(1);
    assertThat(actualResult[0].toString()).isEqualTo("  HeLLo 123 ab ");
  }

  @Test
  public void trim() {
    var testStrLeft =
        new StdString(
            new char[]{' ', ' ', 'H', 'e', 'L', 'L', 'o', ' ', '1', '2', '3', ' ', 'a', 'b', ' '});

    var actualResult = testStrLeft.trim();
    assertThat(actualResult.toString()).isEqualTo("HeLLo 123 ab");
    assertThat(testStrLeft.toString()).isEqualTo("  HeLLo 123 ab ");
  }

  @Test
  public void trimWithEmptyString() {
    var testStrLeft = new StdString();

    var actualResult = testStrLeft.trim();
    assertThat(actualResult.toString()).isEmpty();
  }

  @Test
  public void trimWithoutSpaces() {
    var testStrLeft = new StdString(new char[]{'1', '2', '3'});

    var actualResult = testStrLeft.trim();
    assertThat(actualResult.toString()).isEqualTo("123");
  }

  @Test
  public void removeCharacter() {
    var testStrLeft =
        new StdString(
            new char[]{' ', ' ', 'H', 'e', 'L', 'L', 'o', ' ', '1', '2', '3', ' ', 'a', 'b', ' '});

    var actualResult = testStrLeft.removeCharacter(' ');
    assertThat(actualResult.toString()).isEqualTo("HeLLo123ab");
    assertThat(testStrLeft.toString()).isEqualTo("  HeLLo 123 ab ");
  }

  @Test
  public void removeCharacterWithEmptyString() {
    var testStrLeft = new StdString();

    var actualResult = testStrLeft.removeCharacter(' ');
    assertThat(actualResult.toString()).isEmpty();
  }
}
