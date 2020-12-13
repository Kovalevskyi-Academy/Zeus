package com.kovalevskyi.academy.codingbootcamp.suite.tests.week1.day1;

import com.kovalevskyi.academy.codingbootcamp.suite.AbstractTestExecutor;
import com.kovalevskyi.academy.codingbootcamp.week1.day1.StdString;
import org.junit.Test;

import java.util.HashSet;

import static com.google.common.truth.Truth.assertThat;

public class StdStringTest extends AbstractTestExecutor {

  @Test
  public void copyConstructor() {
    var testStr = new StdString(new char[] {'h', 'e', 'l', 'l', 'o'});
    var testStr2 = new StdString(testStr);

    assertThat(testStr2).isEqualTo(testStr);
  }

  @Test
  public void length() {
    var testStr = new StdString(new char[] {'h', 'e', 'l', 'l', 'o'});

    assertThat(testStr.length()).isEqualTo(5);
  }

  @Test
  public void append() {
    var testStr = new StdString(new char[] {'h', 'e', 'l', 'l', 'o'});
    var testStr2 = new StdString(new char[] {'1', '2', '3'});

    assertThat(testStr.append(testStr2).toString()).isEqualTo("hello123");
    assertThat(testStr.toString()).isEqualTo("hello");
    assertThat(testStr2.toString()).isEqualTo("123");
  }

  @Test
  public void toCharArray() {
    var inputChars = new char[] {'h', 'e', 'l', 'l', 'o'};
    var testStr = new StdString(inputChars);

    assertThat(testStr.toCharArray()).isEqualTo(inputChars);
  }

  @Test
  public void charAt() {
    var inputChars = new char[] {'h', 'e', 'l', 'l', 'o'};
    var testStr = new StdString(inputChars);

    assertThat(testStr.charAt(1)).isEqualTo('e');
    assertThat(testStr.charAt(4)).isEqualTo('o');
  }

  @Test
  public void indexOf() {
    var inputChars = new char[] {'h', 'e', 'l', 'l', 'o'};
    var testStr = new StdString(inputChars);

    assertThat(testStr.indexOf('l')).isEqualTo(2);
    assertThat(testStr.indexOf('z')).isEqualTo(-1);
  }

  @Test
  public void testEquals() {
    var inputChars = new char[] {'h', 'e', 'l', 'l', 'o'};
    var inputChars2 = new char[] {'h', 'e', 'l', 'l', 'o', '2'};
    var testStr = new StdString(inputChars);
    var testStr2 = new StdString(inputChars);
    var testStr3 = new StdString(inputChars2);

    assertThat(testStr.equals(testStr2)).isTrue();
    assertThat(testStr.equals(testStr3)).isFalse();
  }

  @Test
  public void testHashCode() {
    var hashCode = (int) 'h' + (int) 'e' + (int) 'l' * 2 + (int) 'o';
    var inputChars = new char[] {'h', 'e', 'l', 'l', 'o'};
    var testStr = new StdString(inputChars);

    assertThat(testStr.hashCode()).isEqualTo(hashCode);
  }

  @Test
  public void testToString() {
    var inputChars = new char[] {'h', 'e', 'l', 'l', 'o'};
    var testStr = new StdString(inputChars);

    assertThat(testStr.toString()).isEqualTo("hello");
  }

  @Test
  public void iterator() {
    var inputChars = new char[] {'h', 'e', 'l', 'l', 'o'};
    var testStr = new StdString(inputChars);
    var index = 0;

    for (var ch : testStr) {
      assertThat(ch).isEqualTo(inputChars[index]);
      index++;
    }
  }

  @Test
  public void forEach() {
    var inputChars = new char[] {'h', 'e', 'l', 'o'};
    var testStr = new StdString(inputChars);
    var result = new HashSet<Character>();

    testStr.forEach(result::add);

    for (var ch : inputChars) {
      assertThat(result.contains(ch)).isTrue();
    }

    assertThat(result.size()).isEqualTo(4);
  }
}
