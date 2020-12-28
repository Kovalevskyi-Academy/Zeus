package com.kovalevskyi.academy.zeus.util;

import java.util.HashMap;
import java.util.Map;

public class PathParser {

  private static final String PATH_PATTERN =
      "src/main/java/com/kovalevskyi/academy/codingbootcamp/week%d/day%d/%s.java";

  private static final Map<String, String> paths = new HashMap<>();

  public static String putAndGet(String testClass) {
    var result = parse(testClass);
    paths.put(testClass, String.format(PATH_PATTERN, result.week, result.day, result.name));
    return testClass;
  }

  public static String getRelativePath(String testClass) {
    if (!paths.containsKey(testClass)) {
      throw new NullPointerException();
    }
    return paths.get(testClass);
  }

  private static ParseResult parse(String testClass) {
    var result = new ParseResult();
    var split = testClass.split("\\.");
    var isWeekParseError = true;
    var isDayParseError = true;
    for (String entry : split) {
      if (entry.matches("^week\\d*$")) {
        result.week = Integer.parseInt(entry.replaceAll("week", ""));
        isWeekParseError = false;
      }
      if (entry.matches("^day\\d*$")) {
        result.day = Integer.parseInt(entry.replaceAll("day", ""));
        isDayParseError = false;
      }
      if (entry.matches("^\\w*Test$")) {
        result.name = entry.replaceAll("Test", "");
      }
    }
    var errorPattern = "Can't parse a %s from \"%s\"";
    if (isWeekParseError) {
      throw new IllegalArgumentException(String.format(errorPattern, "week", testClass));
    }
    if (isDayParseError) {
      throw new IllegalArgumentException(String.format(errorPattern, "day", testClass));
    }
    if (result.name == null) {
      throw new IllegalArgumentException(String.format(errorPattern, "name", testClass));
    }
    return result;
  }

  private static class ParseResult {

    private String name;
    private int week;
    private int day;
  }
}
