package com.kovalevskyi.academy.zeus.util;

import com.kovalevskyi.academy.zeus.view.CheckstyleConsolePrinter;
import com.puppycrawl.tools.checkstyle.Main;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

public class Checkstyle {

  private static int totalErrors;

  public enum Checks {

    GOOGLE_CHECKS("google_checks.xml"),
    SUN_CHECKS("sun_checks.xml");

    public final String checksFile;

    Checks(String checksFile) {
      this.checksFile = checksFile;
    }
  }

  public static void checkAll(final Checks checkstyle, final List<File> javaFiles)
      throws IOException, CheckstyleException {
    for (var javaFile : javaFiles) {
      check(checkstyle, javaFile);
    }
    if (totalErrors > 0) {
      throw new CheckstyleException(String.format("You have %d style error(s)", totalErrors));
    } else {
      System.out.println("Your project is passed checkstyle successfully!");
    }
  }

  private static void check(final Checks checkstyle, final File javaFile) throws IOException {
    if (javaFile.isDirectory()) {
      throw new IllegalArgumentException("Directory is not supported!");
    }
    if (!javaFile.getName().toLowerCase().endsWith(".java")) {
      throw new IllegalArgumentException(String.format("%s is not supported!", javaFile.getName()));
    }
    var outputStreamCaptor = new ByteArrayOutputStream();
    var defaultPrintStream = System.out;
    System.setOut(new PrintStream(outputStreamCaptor));
    Main.main("-c", checkstyle.checksFile, javaFile.getAbsolutePath());
    System.setOut(defaultPrintStream);
    var checkstyleCaptor = new CheckstyleConsolePrinter(outputStreamCaptor, javaFile.getName());
    totalErrors += checkstyleCaptor.processPrints();
  }
}