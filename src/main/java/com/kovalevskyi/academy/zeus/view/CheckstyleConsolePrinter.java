package com.kovalevskyi.academy.zeus.view;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

public class CheckstyleConsolePrinter {

  private final ByteArrayOutputStream captor;
  private final String fileName;

  public CheckstyleConsolePrinter(final ByteArrayOutputStream captor, final String fileName) {
    this.captor = captor;
    this.fileName = fileName;
  }

  public int processPrints() {
    if (captor.toString().isEmpty()) {
      throw new IllegalArgumentException("Checkstyle console captor is empty!");
    }
    var warnings = Arrays.stream(captor.toString().split("\n"))
        .filter(message -> !message.contains("Starting audit..."))
        .filter(message -> !message.contains("Audit done."))
        .collect(Collectors.toList());
    if (!warnings.isEmpty()) {
      AnsiConsole.systemInstall();
      System.out.printf("%s has %d style error(s)\n", fileName, warnings.size());
      warnings.forEach(line -> System.out.println(Ansi.ansi().fgRed().a(line).reset()));
      AnsiConsole.systemUninstall();
    }
    return warnings.size();
  }
}
