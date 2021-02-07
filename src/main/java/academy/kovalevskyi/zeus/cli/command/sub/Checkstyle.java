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
import java.util.function.Function;
import java.util.stream.Collectors;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

@Command(name = "checkstyle", description = "Run checkstyle", mixinStandardHelpOptions = true)
public class Checkstyle implements Callable<Integer> {

  @Parameters(description = "Classes names to check with checkstyle")
  private final List<String> classNames = new ArrayList<>();

  public Integer call() throws Exception {
    final var style = Style.GOOGLE;
    if (classNames.isEmpty()) {
      checkAllSourceFiles(style);
    } else {
      checkAllSourceFiles(classNames, style);
    }
    return 0;
  }

  public int checkAllSourceFiles(final List<String> classes, final Style style) throws IOException {
    Function<File, String> dropType = file -> file.getName()
        .replaceFirst(FileType.JAVA.extension, "");
    final var result = FileExplorer
        .getFiles(getSourceFilesDirectory(), FileType.JAVA)
        .stream()
        .filter(file -> classes.contains(dropType.apply(file)))
        .collect(Collectors.toUnmodifiableList());
    if (result.size() != classes.size()) {
      var foundClasses = result.stream().map(dropType).collect(Collectors.toList());
      AnsiConsole.systemInstall();
      classes.forEach(clazz -> {
        if (!foundClasses.contains(clazz)) {
          var message = Ansi
              .ansi()
              .fg(State.FAILED.color)
              .format("%s%s is not found on your project!", clazz, FileType.JAVA.extension)
              .reset()
              .toString();
          System.out.println(message);
        }
      });
      AnsiConsole.systemUninstall();
    }
    return CheckstyleEngine.checkAll(style, result);
  }

  public int checkAllSourceFiles(final Style style) throws IOException {
    final var javaFiles = FileExplorer.getFiles(getSourceFilesDirectory(), FileType.JAVA);
    return CheckstyleEngine.checkAll(style, javaFiles);
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
