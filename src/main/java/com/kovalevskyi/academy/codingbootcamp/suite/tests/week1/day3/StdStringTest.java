package com.kovalevskyi.academy.codingbootcamp.suite.tests.week1.day3;

import com.kovalevskyi.academy.codingbootcamp.suite.AbstractTestExecutor;
import com.kovalevskyi.academy.codingbootcamp.week1.day3.StdString;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.fail;

public class StdStringTest extends AbstractTestExecutor {

    @Test
    public void toAsciiLowerCase() {
        var testStr = new StdString(new char[]{'H', 'e', 'L', 'L', 'o'});

        assertThat(testStr.toAsciiLowerCase()).isEqualTo("hello");
        assertThat(testStr.toString()).isEqualTo("HeLLo");
    }

    @Test
    public void toAsciiLowerCaseWithLegalNonAlphabetCharacters() {
        var testStr = new StdString(new char[]{'H', 'e', 'L', 'L', 'o', '1', ' '});

        assertThat(testStr.toAsciiLowerCase()).isEqualTo("hello1 ");
        assertThat(testStr.toString()).isEqualTo("HeLLo1 ");
    }

    @Test
    public void toAsciiLowerCaseWithIllegalCharacters() {
        var testStr = new StdString(new char[]{(char)299, 'e', 'L', 'L', 'o', '1', ' '});

        try {
            testStr.toAsciiLowerCase();
            fail("toAsciiLowerCase does not throw IllegalArgumentException with new char[]{(char)299, 'e', 'L', 'L', 'o', '1', ' '}");
        } catch (IllegalArgumentException e) {

        }
    }

    @Test
    public void toAsciiUpperCase() {
        var testStr = new StdString(new char[]{'H', 'e', 'L', 'L', 'o'});

        assertThat(testStr.toAsciiUpperCase()).isEqualTo("HELLO");
        assertThat(testStr.toString()).isEqualTo("HeLLo");
    }

    @Test
    public void toAsciiUpperCaseWithLegalNonAlphabetCharacters() {
        var testStr = new StdString(new char[]{' ', '!', 'H', 'e', 'L', 'L', 'o'});

        assertThat(testStr.toAsciiUpperCase().toString()).isEqualTo(" !HELLO");
    }

    @Test
    public void toAsciiUpperCaseWithIllegalCharacters() {
        var testStr = new StdString(new char[]{(char)299, '!', 'H', 'e', 'L', 'L', 'o'});

        try {
            testStr.toAsciiUpperCase();
            fail("toAsciiUpperCase() is not throwing IllegalArgumentException with new char[]{(char)299, '!', 'H', 'e', 'L', 'L', 'o'}");
        } catch (IllegalArgumentException e) {

        }
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
            fail("testStr.subString(-1, 6) does not throw IndexOutOfBoundsException with new char[]{'H', 'e', 'L', 'L', 'o', '1', '2', '3'}");
        } catch (IndexOutOfBoundsException e) {

        }
    }

    @Test
    public void subStringWithFromBiggerThanTo() {
        var testStr = new StdString(new char[]{'H', 'e', 'L', 'L', 'o', '1', '2', '3'});

        try {
            testStr.subString(7, 6);
            fail("testStr.subString(7, 6) does not throw IllegalArgumentException with new char[]{'H', 'e', 'L', 'L', 'o', '1', '2', '3'}");
        } catch (IllegalArgumentException e) {

        }
    }

//    @Test
//    public void concat() {
//        var testStrLeft = new StdString(new char[]{'H', 'e', 'L', 'L', 'o'});
//        var testStrRight = new StdString(new char[]{'1', '2', '3'});
//        Assertions.assertEquals("HeLLo123", testStrLeft.concat(testStrRight).toString());
//        Assertions.assertEquals("HeLLo", testStrLeft.toString());
//        Assertions.assertEquals("123", testStrRight.toString());
//    }
//
//    @Test
//    public void concatWithEmpty() {
//        var testStrLeft = new StdString(new char[]{'H', 'e', 'L', 'L', 'o'});
//        var testStrRight = new StdString(new char[]{});
//        Assertions.assertEquals("HeLLo", testStrLeft.concat(testStrRight).toString());
//    }
//
//    @Test
//    public void concatWithNullStr() {
//        var testStrLeft = new StdString(new char[]{'H', 'e', 'L', 'L', 'o'});
//        var testStrRight = (StdString)null;
//
//        try {
//            testStrLeft.concat(testStrRight);
//            Assertions.fail();
//        } catch (NullPointerException e) {
//
//        }
//    }
//
//    @Test
//    public void split() {
//        var testStrLeft = new StdString(new char[]{
//                ' ', ' ', 'H', 'e', 'L', 'L', 'o', ' ', '1', '2', '3', ' ', 'a', 'b', ' '});
//
//        var actualResult = testStrLeft.split(' ');
//        Assertions.assertEquals(5, actualResult.length);
//        Assertions.assertEquals("", actualResult[0].toString());
//        Assertions.assertEquals("", actualResult[1].toString());
//        Assertions.assertEquals("HeLLo", actualResult[2].toString());
//        Assertions.assertEquals("123", actualResult[3].toString());
//        Assertions.assertEquals("ab", actualResult[4].toString());
//        Assertions.assertEquals("  HeLLo 123 ab ", testStrLeft.toString());
//    }
//
//    @Test
//    public void splitWithNonExistingSeparator() {
//        var testStrLeft = new StdString(new char[]{
//                ' ', ' ', 'H', 'e', 'L', 'L', 'o', ' ', '1', '2', '3', ' ', 'a', 'b', ' '});
//
//        var actualResult = testStrLeft.split('!');
//        Assertions.assertEquals(1, actualResult.length);
//        Assertions.assertEquals("  HeLLo 123 ab ", actualResult[0].toString());
//    }
//
//    @Test
//    public void trim() {
//        var testStrLeft = new StdString(new char[]{
//                ' ', ' ', 'H', 'e', 'L', 'L', 'o', ' ', '1', '2', '3', ' ', 'a', 'b', ' '});
//
//        var actualResult = testStrLeft.trim();
//        Assertions.assertEquals("HeLLo 123 ab", actualResult.toString());
//        Assertions.assertEquals("  HeLLo 123 ab ", testStrLeft.toString());
//    }
//
//    @Test
//    public void trimWithEmptyString() {
//        var testStrLeft = new StdString();
//
//        var actualResult = testStrLeft.trim();
//        Assertions.assertEquals("", actualResult.toString());
//    }
//
//    @Test
//    public void trimWithoutSpaces() {
//        var testStrLeft = new StdString(new char[] {'1', '2', '3'});
//
//        var actualResult = testStrLeft.trim();
//        Assertions.assertEquals("123", actualResult.toString());
//    }
//
//    @Test
//    public void removeCharacter() {
//        var testStrLeft = new StdString(new char[]{
//                ' ', ' ', 'H', 'e', 'L', 'L', 'o', ' ', '1', '2', '3', ' ', 'a', 'b', ' '});
//
//        var actualResult = testStrLeft.removeCharacter(' ');
//        Assertions.assertEquals("HeLLo123ab", actualResult.toString());
//        Assertions.assertEquals("  HeLLo 123 ab ", testStrLeft.toString());
//    }
//
//    @Test
//    public void removeCharacterWithEmptyString() {
//        var testStrLeft = new StdString();
//
//        var actualResult = testStrLeft.removeCharacter(' ');
//        Assertions.assertEquals("", actualResult.toString());
//    }
}