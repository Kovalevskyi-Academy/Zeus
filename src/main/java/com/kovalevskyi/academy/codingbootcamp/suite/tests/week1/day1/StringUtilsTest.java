package com.kovalevskyi.academy.codingbootcamp.suite.tests.week1.day1;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import com.kovalevskyi.academy.codingbootcamp.suite.AbstractTestExecutor;
import com.kovalevskyi.academy.codingbootcamp.week1.day1.StringUtils;
import org.junit.jupiter.api.Test;

public class StringUtilsTest extends AbstractTestExecutor {

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
      StringUtils.isAsciiUppercase((char) 257);
      fail("isAsciiUppercase((char)257) does not throw IllegalArgumentException");
    } catch (IllegalArgumentException ignore) {
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
      StringUtils.isAsciiLowercase((char) 257);
      fail("isAsciiLowercase((char)257) does not throw IllegalArgumentException");
    } catch (IllegalArgumentException ignore) {
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
      StringUtils.isAsciiNumeric((char) 257);
      fail("isAsciiNumeric((char)257) does not throw IllegalArgumentException");
    } catch (IllegalArgumentException ignore) {
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
      StringUtils.isAsciiAlphabetic((char) 257);
      fail("isAsciiAlphabetic((char)257) does not throw IllegalArgumentException");
    } catch (IllegalArgumentException ignore) {
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
      StringUtils.toAsciiUppercase((char) 257);
      fail("toAsciiUppercase((char)257) does not throw IllegalArgumentException");
    } catch (IllegalArgumentException ignore) {
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
      StringUtils.toAsciiLowercase((char) 257);
      fail("toAsciiLowercase((char)257) does not throw IllegalArgumentException");
    } catch (IllegalArgumentException ignore) {
    }
  }

  @Test
  public void makeUppercase() {
    var inputValue = new char[]{'h', 'e', 'l', 'L', 'o'};
    var expectedValue = "HELLO";

    assertThat(StringUtils.makeUppercase(inputValue).toString()).isEqualTo(expectedValue);
  }

  @Test
  public void makeUppercaseWithLegalNonCharacters() {
    var inputValue = new char[]{'1', '2', '3', ' ', 'h', 'e', 'l', 'L', 'o'};
    var expectedValue = "123 HELLO";

    assertThat(StringUtils.makeUppercase(inputValue).toString()).isEqualTo(expectedValue);
  }

  @Test
  public void makeUppercaseWithIllegalInput() {
    var inputValue = new char[]{(char) 299, 'e', 'l', 'L', 'o'};
    try {
      StringUtils.makeUppercase(inputValue);
      fail(
          "makeUppercase does not throw IllegalArgumentException for input: new char[] {(char)299, 'e', 'l', 'L', 'o'}");
    } catch (IllegalArgumentException ignore) {
    }
  }

  @Test
  public void makeLowercase() {
    var inputValue = new char[]{'H', 'E', 'l', 'L', 'o'};
    var expectedValue = "hello";

    assertThat(StringUtils.makeLowercase(inputValue).toString()).isEqualTo(expectedValue);
  }

  @Test
  public void makeLowercaseWithLegalNonCharacters() {
    var inputValue = new char[]{'1', '2', '3', ' ', 'H', 'E', 'l', 'L', 'o'};
    var expectedValue = "123 hello";

    assertThat(StringUtils.makeLowercase(inputValue).toString()).isEqualTo(expectedValue);
  }

  @Test
  public void makeLowercaseWithIllegalCharacter() {
    var inputValue = new char[]{'1', '2', '3', (char) 299, ' ', 'H', 'E', 'l', 'L', 'o'};
    try {
      StringUtils.makeLowercase(inputValue);
      fail(
          "makeLowercase with new char[] {'1', '2', '3', (char)299, ' ', 'H', 'E', 'l', 'L', 'o'} does not throw IllegalArgumentException exception");
    } catch (IllegalArgumentException ignore) {
    }
  }

  @Test
  public void makeCamel() {
    var inputValue = new char[]{'H', 'E', 'l', 'L', 'o'};
    var expectedValue = "hElLo";

    assertThat(StringUtils.makeCamel(inputValue).toString()).isEqualTo(expectedValue);
  }

  @Test
  public void makeCamelWithLegalNonAlphabeticCharacters() {
    var inputValue = new char[]{'1', ' ', 'H', 'E', 'l', 'L', 'o', '?', 'O'};
    var expectedValue = "1 hElLo?o";

    assertThat(StringUtils.makeCamel(inputValue).toString()).isEqualTo(expectedValue);
  }

  @Test
  public void makeCamelWithIllegalCharacters() {
    var inputValue = new char[]{'1', (char) 399, 'H', 'E', 'l', 'L', 'o', '?', 'O'};
    try {
      StringUtils.makeCamel(inputValue);
      fail(
          "makeCamel for new char[] {'1', (char)399, 'H', 'E', 'l', 'L', 'o', '?', 'O'} does not throw IllegalArgumentException");
    } catch (IllegalArgumentException ignore) {
    }
  }

  @Test
  public void isStringAlphaNumerical() {
    assertThat(StringUtils.isStringAlphaNumerical(new char[]{'a', 'b', '1', ' '})).isTrue();
    assertThat(StringUtils.isStringAlphaNumerical(new char[]{'a', ',', '1', ' '})).isFalse();
  }

  @Test
  public void isStringAlphaNumericalWithIllegalInput() {
    try {
      StringUtils.isStringAlphaNumerical(new char[]{(char) 399, 'b', '1', ' '});
      fail(
          "isStringAlphaNumerical does not throw IllegalArgumentException for new char[]{(char)399, 'b', '1', ' '}");
    } catch (IllegalArgumentException ignore) {
    }
  }

  @Test
  public void concatStrings() {
    var input = new char[][]{{'a', 'b'}, {'c', 'd'}};
    var expectedOut = new char[]{'a', 'b', 'c', 'd'};
    assertThat(StringUtils.concatStrings(input)).isEqualTo(expectedOut);
  }

  @Test
  public void concatStringsWithZeroInput() {
    var input = new char[][]{};
    var expectedOut = new char[]{};
    assertThat(StringUtils.concatStrings(input)).isEqualTo(expectedOut);
  }

  @Test
  public void concatStringsWithNullInput() {
    try {
      StringUtils.concatStrings(null);
      fail("StringUtils.concatStrings(null) does not throw NullPointerException");
    } catch (NullPointerException ignore) {
    }
  }

  @Test
  public void concatStringsWithNullInputWithinNonNullArray() {
    var input = new char[][]{null};
    try {
      StringUtils.concatStrings(input);
      fail("StringUtils.concatStrings does not throw NullPointerException for new char[][]{null}");
    } catch (NullPointerException ignore) {
    }
  }

  @Test
  public void toInt() {
    assertThat(StringUtils.toInt(new char[]{'1', '2'})).isEqualTo(12);
    assertThat(StringUtils.toInt(new char[]{'0'})).isEqualTo(0);
    assertThat(StringUtils.toInt(new char[]{'-', '1', '2'})).isEqualTo(-12);
    assertThat(StringUtils.toInt(String.valueOf(Integer.MAX_VALUE).toCharArray()))
        .isEqualTo(Integer.MAX_VALUE);
    assertThat(StringUtils.toInt(String.valueOf(Integer.MIN_VALUE).toCharArray()))
        .isEqualTo(Integer.MIN_VALUE);
  }

  @Test
  public void toIntWithNullInput() {
    try {
      StringUtils.toInt(null);
      fail("StringUtils.toInt(null); does not throw NullPointerException");
    } catch (NullPointerException ignore) {
    }
  }

  @Test
  public void toIntWithIllegalInput() {
    var input = new char[]{'1', '2', 'a'};
    try {
      StringUtils.toInt(input);
      fail("StringUtils.toInt does not throw NumberFormatException for: new char[]{'1', '2', 'a'}");
    } catch (NumberFormatException ignore) {
    }
  }
}
