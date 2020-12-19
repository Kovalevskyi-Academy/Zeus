package com.kovalevskyi.academy.codingbootcamp.suite;

public class TaskPathParserUtil {

  private static final String DELIMITER = "#";
  private static final String COMBINE_PATTERN = "%s%s%s";

  public static String combine(String testClass, String testedClassPath) {
    return String.format(COMBINE_PATTERN, testClass, DELIMITER, testedClassPath);
  }

  public static String parseTestClass(String combinedString) {
    return combinedString.substring(0, combinedString.indexOf(DELIMITER));
  }

  public static String parseTestedClassPath(String combinedString) {
    return combinedString.substring(combinedString.indexOf(DELIMITER) + 1);
  }
}
