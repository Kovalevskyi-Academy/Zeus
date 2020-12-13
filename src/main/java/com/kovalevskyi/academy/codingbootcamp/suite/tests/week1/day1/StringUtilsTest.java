package com.kovalevskyi.academy.codingbootcamp.suite.tests.week1.day1;



import com.kovalevskyi.academy.codingbootcamp.week1.day1.StringUtils;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;

public class StringUtilsTest {

  @Test
  public void isAsciiUppercase() {
    assertThat(StringUtils.isAsciiUppercase('A')).isTrue();
    assertThat(StringUtils.isAsciiUppercase('Z')).isTrue();
    assertThat(StringUtils.isAsciiUppercase('X')).isTrue();
    assertThat(StringUtils.isAsciiUppercase('a')).isFalse();
    assertThat(StringUtils.isAsciiUppercase('d')).isFalse();
    assertThat(StringUtils.isAsciiUppercase('u')).isFalse();
  }

  @Test
  public void isAsciiUppercaseWithIllegalInput() {
    try {
      StringUtils.isAsciiUppercase((char)257);
      fail("isAsciiUppercase((char)257) does not throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {

    }
  }

  @Test
  public void isAsciiLowercase() {
    assertThat(StringUtils.isAsciiLowercase('A')).isFalse();
    assertThat(StringUtils.isAsciiLowercase('Z')).isFalse();
    assertThat(StringUtils.isAsciiLowercase('X')).isFalse();
    assertThat(StringUtils.isAsciiLowercase('a')).isTrue();
    assertThat(StringUtils.isAsciiLowercase('d')).isTrue();
    assertThat(StringUtils.isAsciiLowercase('u')).isTrue();
  }

  @Test
  public void isAsciiLowercaseWithIllegalInput() {
    try {
      StringUtils.isAsciiLowercase((char)257);
      fail("isAsciiLowercase((char)257) does not throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {

    }
  }

  @Test
  public void isAsciiNumeric() {
    assertThat(StringUtils.isAsciiNumeric('A')).isFalse();
    assertThat(StringUtils.isAsciiNumeric('Z')).isFalse();
    assertThat(StringUtils.isAsciiNumeric('X')).isFalse();
    assertThat(StringUtils.isAsciiNumeric('1')).isTrue();
    assertThat(StringUtils.isAsciiNumeric('2')).isTrue();
    assertThat(StringUtils.isAsciiNumeric('3')).isTrue();
  }

  @Test
  public void isAsciiNumericWithIllegalInput() {
    try {
      StringUtils.isAsciiNumeric((char)257);
      fail("isAsciiNumeric((char)257) does not throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {

    }
  }

  @Test
  public void isAsciiAlphabetic() {
    assertThat(StringUtils.isAsciiAlphabetic('A')).isTrue();
    assertThat(StringUtils.isAsciiAlphabetic('A')).isTrue();
    assertThat(StringUtils.isAsciiAlphabetic('A')).isTrue();
    assertThat(StringUtils.isAsciiAlphabetic('1')).isFalse();
    assertThat(StringUtils.isAsciiAlphabetic('2')).isFalse();
    assertThat(StringUtils.isAsciiAlphabetic('3')).isFalse();
  }

  @Test
  public void isAsciiAlphabeticWithIllegalInput() {
    try {
      StringUtils.isAsciiAlphabetic((char)257);
      fail("isAsciiAlphabetic((char)257) does not throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {

    }
  }

  @Test
  public void toAsciiUppercase() {
    assertThat(StringUtils.toAsciiUppercase('a')).isEqualTo('A');
    assertThat(StringUtils.toAsciiUppercase('b')).isEqualTo('B');
    assertThat(StringUtils.toAsciiUppercase('A')).isEqualTo('A');
    assertThat(StringUtils.toAsciiUppercase('B')).isEqualTo('B');
    assertThat(StringUtils.toAsciiUppercase('!')).isEqualTo('!');
    assertThat(StringUtils.toAsciiUppercase(' ')).isEqualTo(' ');
  }

  @Test
  public void toAsciiUppercaseWithIllegalInput() {
    try {
      StringUtils.toAsciiUppercase((char)257);
      fail("toAsciiUppercase((char)257) does not throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {

    }
  }

  @Test
  public void toAsciiLowercase() {
    assertThat(StringUtils.toAsciiLowercase('a')).isEqualTo('a');
    assertThat(StringUtils.toAsciiLowercase('b')).isEqualTo('b');
    assertThat(StringUtils.toAsciiLowercase('A')).isEqualTo('a');
    assertThat(StringUtils.toAsciiLowercase('B')).isEqualTo('b');
    assertThat(StringUtils.toAsciiLowercase('!')).isEqualTo('!');
    assertThat(StringUtils.toAsciiLowercase(' ')).isEqualTo(' ');
  }

  @Test
  public void toAsciiLowercaseWithIllegalInput() {
    try {
      StringUtils.toAsciiLowercase((char)257);
      fail("toAsciiLowercase((char)257) does not throw IllegalArgumentException");
    } catch (IllegalArgumentException e) {

    }
  }

  @Test
  public void makeUppercase() {
    var inputValue = new char[] {'h', 'e', 'l', 'L', 'o'};
    var expectedValue = "HELLO";

    assertThat(StringUtils.makeUppercase(inputValue).toString())
            .isEqualTo(expectedValue);
  }

//  @Test
//  public void makeUppercaseWithLegalNonCharacters() {
//    var inputValue = new char[] {'1', '2', '3', ' ', 'h', 'e', 'l', 'L', 'o'};
//    var expectedValue = "123 HELLO";
//
//    Assertions.assertEquals(expectedValue,
//            StringUtils.makeUppercase(inputValue).toString());
//  }
//
//  @Test
//  public void makeUppercaseWithIllegalInput() {
//    var inputValue = new char[] {(char)299, 'e', 'l', 'L', 'o'};
//    try {
//      StringUtils.makeUppercase(inputValue);
//      Assertions.fail();
//    } catch (IllegalArgumentException e) {
//
//    }
//  }
//
//  @Test
//  public void makeLowercase() {
//    var inputValue = new char[] {'H', 'E', 'l', 'L', 'o'};
//    var expectedValue = "hello";
//
//    Assertions.assertEquals(expectedValue,
//        StringUtils.makeLowercase(inputValue).toString());
//  }
//
//  @Test
//  public void makeLowercaseWithLegalNonCharacters() {
//    var inputValue = new char[] {'1', '2', '3', ' ', 'H', 'E', 'l', 'L', 'o'};
//    var expectedValue = "123 hello";
//
//    Assertions.assertEquals(expectedValue,
//            StringUtils.makeLowercase(inputValue).toString());
//  }
//
//  @Test
//  public void makeLowercaseWithIllegalCharacter() {
//    var inputValue = new char[] {'1', '2', '3', (char)299, ' ', 'H', 'E', 'l', 'L', 'o'};
//    try {
//      StringUtils.makeLowercase(inputValue);
//      Assertions.fail();
//    } catch (IllegalArgumentException e) {
//
//    }
//  }
//
//  @Test
//  public void makeCamel() {
//    var inputValue = new char[] {'H', 'E', 'l', 'L', 'o'};
//    var expectedValue = "hElLo";
//
//    Assertions.assertEquals(expectedValue,
//            StringUtils.makeCamel(inputValue).toString());
//  }
//
//  @Test
//  public void makeCamelWithLegalNonAlphabeticCharacters() {
//    var inputValue = new char[] {'1', ' ', 'H', 'E', 'l', 'L', 'o', '?', 'O'};
//    var expectedValue = "1 hElLo?o";
//
//    Assertions.assertEquals(expectedValue,
//            StringUtils.makeCamel(inputValue).toString());
//  }
//
//  @Test
//  public void makeCamelWithIllegalCharacters() {
//    var inputValue = new char[] {'1', (char)399, 'H', 'E', 'l', 'L', 'o', '?', 'O'};
//    try{
//      StringUtils.makeCamel(inputValue);
//      Assertions.fail();
//    } catch (IllegalArgumentException e) {
//
//    }
//  }
//
//  @Test
//  public void isStringAlphaNumerical() {
//    Assertions.assertTrue(StringUtils.isStringAlphaNumerical(
//        new char[]{'a', 'b', '1', ' '}
//    ));
//    Assertions.assertFalse(StringUtils.isStringAlphaNumerical(
//        new char[]{'a', ',', '1', ' '}
//    ));
//  }
//
//  @Test
//  public void isStringAlphaNumericalWithIllegalInput() {
//    try {
//      StringUtils.isStringAlphaNumerical(
//              new char[]{(char)399, 'b', '1', ' '}
//      );
//    } catch (IllegalArgumentException e) {
//
//    }
//  }
//
//  @Test
//  public void concatStrings() {
//    var input = new char[][]{
//            {'a', 'b'},
//            {'c', 'd'}
//    };
//    var expectedOut = new char[] {'a', 'b', 'c', 'd'};
//    Assertions.assertArrayEquals(expectedOut, StringUtils.concatStrings(input));
//  }
//
//  @Test
//  public void concatStringsWithZeroInput() {
//    var input = new char[][]{};
//    var expectedOut = new char[] {};
//    Assertions.assertArrayEquals(expectedOut, StringUtils.concatStrings(input));
//  }
//
//  @Test
//  public void concatStringsWithNullInput() {
//    try {
//      StringUtils.concatStrings(null);
//      Assertions.fail();
//    } catch (NullPointerException e) {
//
//    }
//  }
//
//  @Test
//  public void concatStringsWithNullInputWithinNonNullArray() {
//    var input = new char[][]{null};
//    try {
//      StringUtils.concatStrings(input);
//      Assertions.fail();
//    } catch (NullPointerException e) {
//
//    }
//  }
//
//  @Test
//  public void toInt() {
//    Assertions.assertEquals(12, StringUtils.toInt(new char[]{'1', '2'}));
//    Assertions.assertEquals(0, StringUtils.toInt(new char[]{'0'}));
//    Assertions.assertEquals(-12, StringUtils.toInt(new char[]{'-', '1', '2'}));
//    Assertions.assertEquals(Integer.MAX_VALUE,
//        StringUtils.toInt(
//            String.valueOf(Integer.MAX_VALUE).toCharArray()
//        ));
//  }
//
//  @Test
//  public void toIntWithNullInput() {
//    try {
//      StringUtils.toInt(null);
//      Assertions.fail();
//    } catch (NullPointerException e) {
//
//    }
//  }
//
//  @Test
//  public void toIntWithIllegalInput() {
//    var input = new char[]{'1', '2', 'a'};
//    try {
//      StringUtils.toInt(input);
//      Assertions.fail();
//    } catch (NumberFormatException e) {
//
//    }
//  }
}