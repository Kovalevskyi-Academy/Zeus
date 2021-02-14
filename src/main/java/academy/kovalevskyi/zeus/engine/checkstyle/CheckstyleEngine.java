package academy.kovalevskyi.zeus.engine.checkstyle;

import academy.kovalevskyi.testing.view.State;
import academy.kovalevskyi.zeus.util.FileExplorer;
import academy.kovalevskyi.zeus.util.FileType;
import com.puppycrawl.tools.checkstyle.Main;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

public class CheckstyleEngine {

  public static int check(final Style style, final File file) throws IOException {
    final var warnings = process(style, file);
    AnsiConsole.systemInstall();
    final var template = "%s%s%n";
    if (warnings.isEmpty()) {
      System.out.printf(template, file.getName(), prepareStatus(State.SUCCESSFUL));
    } else {
      System.out.printf(template, file.getName(), prepareStatus(State.FAILED));
      warnings.forEach(line -> System.out.println(Ansi.ansi().fgRed().a(line).reset()));
    }
    AnsiConsole.systemUninstall();
    return warnings.size();
  }

  public static int checkAll(final Style style, final List<File> files) throws IOException {
    if (files.isEmpty()) {
      System.out.println("You have nothing to verify with checkstyle!");
      return 0;
    } else {
      var verified = 0;
      var warnings = 0;
      for (var file : files) {
        var result = check(style, file);
        if (result == 0) {
          verified++;
        } else {
          warnings += result;
        }
      }
      if (warnings > 0) {
        var footer = prepareFooter(files.size(), verified, files.size() - verified, warnings);
        System.out.println(footer);
      } else {
        System.out.println("Your source files are verified by checkstyle successfully!");
      }
      return warnings;
    }
  }

  private static List<String> process(final Style style, final File file) throws IOException {
    if (file.isDirectory()) {
      throw new IllegalArgumentException("Directory is not supported!");
    } else if (!FileExplorer.match(file, FileType.JAVA)) {
      throw new IllegalArgumentException(String.format("%s is not supported!", file.getName()));
    } else {
      final var outputStreamCaptor = new ByteArrayOutputStream();
      final var defaultPrintStream = System.out;
      System.setOut(new PrintStream(outputStreamCaptor));
      Main.main("-c", style.config, file.getAbsolutePath());
      System.setOut(defaultPrintStream);
      return collectResult(outputStreamCaptor);
    }
  }

  private static List<String> collectResult(final ByteArrayOutputStream captor) {
    final var result = captor.toString();
    if (result.trim().isEmpty()) {
      throw new IllegalArgumentException("Checkstyle console captor is empty!");
    } else {
      return Arrays
          .stream(result.split("\n"))
          .filter(message -> !message.contains("Starting audit..."))
          .filter(message -> !message.contains("Audit done."))
          .filter(message -> !message.contains("[MissingJavadocType]"))
          .filter(message -> !message.contains("[MissingJavadocMethod]"))
          .collect(Collectors.toUnmodifiableList());
    }
  }

  private static String prepareFooter(
      final int files,
      final int successful,
      final int failed,
      final int errors) {
    final var result = new StringJoiner(" | ");
    result.add(String.format("%nFILES %d", files));
    if (successful > 0) {
      result.add(String.format("SUCCESSFUL %d", successful));
    }
    if (failed > 0) {
      result.add(String.format("FAILED %d", failed));
    }
    if (errors > 0) {
      result.add(String.format("ERRORS %d", errors));
    }
    var bar = result.toString();
    return String.format("%s%n%s", bar, "-".repeat(bar.trim().length()));
  }

  private static String prepareStatus(final State state) {
    return Ansi.ansi().a(" - ").fg(state.color).a(state.status).reset().toString();
  }
}