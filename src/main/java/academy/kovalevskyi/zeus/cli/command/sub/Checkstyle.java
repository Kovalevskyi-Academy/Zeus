package academy.kovalevskyi.zeus.cli.command.sub;

import academy.kovalevskyi.testing.view.State;
import academy.kovalevskyi.zeus.engine.checkstyle.CheckstyleEngine;
import academy.kovalevskyi.zeus.engine.checkstyle.Style;
import academy.kovalevskyi.zeus.util.FileExplorer;
import academy.kovalevskyi.zeus.util.FileType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "checkstyle", description = "Run checkstyle", mixinStandardHelpOptions = true)
public class Checkstyle implements Callable<Void> {

  @Parameters(description = "Classes names to check with checkstyle")
  private final List<String> classNames = new ArrayList<>();

  public Void call() throws Exception {
    final var style = Style.GOOGLE;
    if (classNames.isEmpty()) {
      checkAllSourceFiles(style);
    } else {
      checkAllSourceFiles(classNames, style);
    }
    return null;
  }

  public void checkAllSourceFiles(final List<String> classes, final Style style)
      throws IOException {
    final var preparedNames = classes
        .stream()
        .map(name -> {
          if (!name.endsWith(FileType.JAVA.extension)) {
            return String.format("%s%s", name, FileType.JAVA.extension);
          }
          return name;
        })
        .collect(Collectors.toUnmodifiableList());

    final var result = FileExplorer
        .getFiles(getSourceFilesDirectory(), FileType.JAVA)
        .stream()
        .filter(file -> preparedNames.contains(file.getName()))
        .collect(Collectors.toUnmodifiableList());

    if (result.size() != classes.size()) {
      var existedFileNames = result.stream().map(File::getName).collect(Collectors.toList());

      AnsiConsole.systemInstall();
      preparedNames.forEach(name -> {
        if (!existedFileNames.contains(name)) {
          System.out.println(prepareFileNotFoundMessage(name));
        }
      });
      AnsiConsole.systemUninstall();
    }

    CheckstyleEngine.checkAll(style, result);
  }

  public void checkAllSourceFiles(final Style style) throws IOException {
    final var javaFiles = FileExplorer.getFiles(getSourceFilesDirectory(), FileType.JAVA);
    CheckstyleEngine.checkAll(style, javaFiles);
  }

  private String prepareFileNotFoundMessage(final String name) {
    return Ansi
        .ansi()
        .fg(State.FAILED.color)
        .format("%s is not found on your project!", name)
        .reset()
        .toString();
  }

  private File getSourceFilesDirectory() throws FileNotFoundException {
    final var directory = new File(FileExplorer.JAVA_SOURCES);
    if (!directory.exists()) {
      throw new FileNotFoundException("Directory of java source files is not exist!");
    } else {
      return directory;
    }
  }
}
