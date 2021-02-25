package academy.kovalevskyi.zeus.cli.command.sub;

import academy.kovalevskyi.testing.service.State;
import academy.kovalevskyi.testing.util.ContainerLauncher;
import academy.kovalevskyi.zeus.cli.group.CourseRequest;
import academy.kovalevskyi.zeus.util.FileExplorer;
import java.util.concurrent.Callable;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import picocli.CommandLine.ArgGroup;
import picocli.CommandLine.Command;

@Command(
    name = "pass",
    description = "Run exam",
    mixinStandardHelpOptions = true)
public class Pass implements Callable<Void> {

  @ArgGroup(exclusive = false, multiplicity = "1")
  private final CourseRequest request = new CourseRequest();

  @Override
  public Void call() throws Exception {
    if (FileExplorer.isJarAbsentInClasspath()) {
      System.out.println(Test.EMPTY_CLASSPATH);
    } else {
      var errors = Checkstyle.checkAllSourceFiles();
      if (errors > 0) {
        AnsiConsole.systemInstall();
        System.out.println(errorStyleMessage());
        AnsiConsole.systemUninstall();
      } else {
        ContainerLauncher.execute(request.prepareRequest());
      }
    }
    return null;
  }

  private String errorStyleMessage() {
    return Ansi
        .ansi()
        .fg(State.FAILED.color)
        .a("Resolve all problems and than you can continue the exam!")
        .reset()
        .toString();
  }
}
