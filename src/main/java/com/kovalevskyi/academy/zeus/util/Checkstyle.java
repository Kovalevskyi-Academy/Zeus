package com.kovalevskyi.academy.zeus.util;

import com.kovalevskyi.academy.zeus.view.CheckstyleConsolePrinter;
import com.puppycrawl.tools.checkstyle.Main;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Checkstyle {

  public enum Checks {

    GOOGLE_CHECKS("google_checks.xml"),
    SUN_CHECKS("sun_checks.xml");

    public final String checksFile;

    Checks(String checksFile) {
      this.checksFile = checksFile;
    }

  }

  public static void check(final Checks checkstyle, final File javaFile)
      throws IOException, CheckstyleException {
    if (javaFile.isDirectory()) {
      throw new IllegalArgumentException("Directory is not supported!");
    }
    if (!javaFile.getName().endsWith(".java")) {
      var message = String.format("%s is not supported!", javaFile.getName());
      throw new IllegalArgumentException(message);
    }
    var outputStreamCaptor = new ByteArrayOutputStream();
    var defaultPrintStream = System.out;
    System.setOut(new PrintStream(outputStreamCaptor));
    Main.main("-c", checkstyle.checksFile, javaFile.getAbsolutePath());
    System.setOut(defaultPrintStream);
    new CheckstyleConsolePrinter(outputStreamCaptor, javaFile.getName()).processPrints();
  }
}