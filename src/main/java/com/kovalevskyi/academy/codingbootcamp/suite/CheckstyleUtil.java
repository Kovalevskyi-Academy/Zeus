package com.kovalevskyi.academy.codingbootcamp.suite;

import com.puppycrawl.tools.checkstyle.Main;
import java.io.IOException;

public class CheckstyleUtil {

  public enum Checkstyle {

    GOOGLE_CHECKS("google_checks.xml"),
    SUN_CHECKS("sun_checks.xml");

    public final String checksFile;

    Checkstyle(String checksFile) {
      this.checksFile = checksFile;
    }

  }

  public static void runCheckstyle(Checkstyle checkstyle, String testedClassPath)
      throws IOException {
    Main.main("-c", checkstyle.checksFile, testedClassPath);
  }
}