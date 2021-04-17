package academy.kovalevskyi.zeus.cli.command.sub;

import academy.kovalevskyi.testing.service.State;
import academy.kovalevskyi.zeus.engine.checkstyle.CheckstyleEngine;
import academy.kovalevskyi.zeus.engine.checkstyle.Style;
import academy.kovalevskyi.zeus.util.FileExplorer;
import academy.kovalevskyi.zeus.util.FileType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(
    name = "style",
    description = "Run checkstyle for all or selected sources",
    mixinStandardHelpOptions = true)
public class Checkstyle implements Callable<Void> {

  private static final Style DEFAULT_CHECKSTYLE = Style.GOOGLE;

  @Parameters(description = "Classes names to check with checkstyle")
  private List<String> classNames;

  static int checkAllSourceFiles() throws IOException {
    final var javaFiles = FileExplorer.getFiles(getSourceFilesDirectory(), true, FileType.JAVA);
    return CheckstyleEngine.check(DEFAULT_CHECKSTYLE, javaFiles);
  }

  private void checkAllSourceFiles(final List<String> classes) throws IOException {
    final var preparedNames = classes
        .stream()
        .map(name -> {
          if (!FileExplorer.match(name, FileType.JAVA)) {
            return String.format("%s%s", name, FileType.JAVA.extension);
          }
          return name;
        })
        .toList();

    final var result = FileExplorer
        .getFiles(getSourceFilesDirectory(), true, FileType.JAVA)
        .stream()
        .filter(file -> preparedNames.contains(file.getName()))
        .toList();

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

    CheckstyleEngine.check(DEFAULT_CHECKSTYLE, result);
  }

  @Override
  public Void call() throws Exception {
    if (classNames == null) {
      checkAllSourceFiles();
    } else {
      checkAllSourceFiles(classNames);
    }
    return null;
  }

  private static Path getSourceFilesDirectory() throws FileNotFoundException {
    final var directory = FileExplorer.JAVA_SOURCES.toFile();
    if (!directory.exists()) {
      throw new FileNotFoundException("Directory of java source files is not exist!");
    } else {
      return FileExplorer.JAVA_SOURCES;
    }
  }

  private String prepareFileNotFoundMessage(final String name) {
    return Ansi
        .ansi()
        .fg(State.FAILED.color)
        .format("%s is not found on your project!", name)
        .reset()
        .toString();
  }
}
