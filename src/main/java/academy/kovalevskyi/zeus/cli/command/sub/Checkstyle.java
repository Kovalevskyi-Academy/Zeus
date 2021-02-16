package academy.kovalevskyi.zeus.cli.command.sub;

import academy.kovalevskyi.testing.service.State;
import academy.kovalevskyi.zeus.engine.checkstyle.CheckstyleEngine;
import academy.kovalevskyi.zeus.engine.checkstyle.Style;
import academy.kovalevskyi.zeus.util.FileExplorer;
import academy.kovalevskyi.zeus.util.FileType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(
    name = "style",
    description = "Run checkstyle for all project or individual sources",
    mixinStandardHelpOptions = true)
public class Checkstyle implements Callable<Void> {

  private static final Style DEFAULT_CHECKSTYLE = Style.GOOGLE;

  @Parameters(description = "Classes names to check with checkstyle")
  private List<String> classNames;

  public Void call() throws Exception {
    if (classNames == null) {
      checkAllSourceFiles();
    } else {
      checkAllSourceFiles(classNames);
    }
    return null;
  }

  static int checkAllSourceFiles() throws IOException {
    final var javaFiles = FileExplorer.getFiles(getSourceFilesDirectory(), FileType.JAVA);
    return CheckstyleEngine.checkAll(DEFAULT_CHECKSTYLE, javaFiles);
  }

  private void checkAllSourceFiles(final List<String> classes) throws IOException {
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

    CheckstyleEngine.checkAll(DEFAULT_CHECKSTYLE, result);
  }

  private String prepareFileNotFoundMessage(final String name) {
    return Ansi
        .ansi()
        .fg(State.FAILED.color)
        .format("%s is not found on your project!", name)
        .reset()
        .toString();
  }

  private static File getSourceFilesDirectory() throws FileNotFoundException {
    final var directory = new File(FileExplorer.JAVA_SOURCES);
    if (!directory.exists()) {
      throw new FileNotFoundException("Directory of java source files is not exist!");
    } else {
      return directory;
    }
  }
}
