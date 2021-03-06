package academy.kovalevskyi.zeus.cli.command.sub;

import academy.kovalevskyi.testing.service.State;
import academy.kovalevskyi.testing.util.AnsiConsoleInstaller;
import academy.kovalevskyi.testing.util.ContainerLauncher;
import academy.kovalevskyi.testing.util.ContainerManager;
import academy.kovalevskyi.zeus.cli.group.CourseRequest;
import academy.kovalevskyi.zeus.util.JarLoader;
import java.util.concurrent.Callable;
import org.fusesource.jansi.Ansi;
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
    if (JarLoader.isManuallyLoaded() || JarLoader.isDynamicallyLoaded()) {
      var errors = Checkstyle.checkAllSourceFiles();
      if (errors > 0) {
        AnsiConsoleInstaller.INSTANCE.systemInstall();
        System.out.println(errorStyleMessage());
        AnsiConsoleInstaller.INSTANCE.systemUninstall();
      } else {
        var containers =
            ContainerManager.getContainers(request.prepareRequest(), "academy.kovalevskyi");
        ContainerLauncher.execute(containers, false, false, false);
      }
    } else {
      System.out.println(Test.EMPTY_CLASSPATH);
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
