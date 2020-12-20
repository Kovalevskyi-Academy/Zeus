package com.kovalevskyi.academy.codingbootcamp.suite.util;

import com.puppycrawl.tools.checkstyle.Main;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Checkstyle {

  public enum Checks {

    GOOGLE_CHECKS("google_checks.xml"),
    SUN_CHECKS("sun_checks.xml");

    public final String checksFile;

    Checks(String checksFile) {
      this.checksFile = checksFile;
    }

  }

  public static void check(Checks checkstyle, String testClassName) throws IOException {
    var relativeClassPath = PathParser.getRelativePath(testClassName);
    var testedClass = new File(System.getProperty("user.dir")
        + File.separator
        + relativeClassPath);
    if (testedClass.exists()) {
      Main.main("-c", checkstyle.checksFile, relativeClassPath);
    } else {
      throw new FileNotFoundException(String.format("%s is not exist!", testedClass.getPath()));
    }
  }
}