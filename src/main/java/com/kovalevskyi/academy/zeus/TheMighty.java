package com.kovalevskyi.academy.zeus;

import academy.kovalevskyi.testing.AbstractTestExecutor;
import academy.kovalevskyi.testing.view.TestsConsolePrinter;
import com.kovalevskyi.academy.zeus.util.Checkstyle;
import com.kovalevskyi.academy.zeus.util.Checkstyle.Checks;
import com.kovalevskyi.academy.zeus.util.FileExplorer;
import com.puppycrawl.tools.checkstyle.api.CheckstyleException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.MavenInvocationException;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import picocli.CommandLine;

@CommandLine.Command(
    name = "zeus",
    mixinStandardHelpOptions = true,
    version = Constants.VERSION,
    description = "Zeus the Mighty")
public class TheMighty implements Callable<Integer> {

  private final String[][][] classNames = {
      {
          {"com.kovalevskyi.academy.codingbootcamp.week0.day0.MainTest"},
          {
              "com.kovalevskyi.academy.codingbootcamp.week0.day1.AlphabetTest",
              "com.kovalevskyi.academy.codingbootcamp.week0.day1.Numbers1Test"
          },
          {"com.kovalevskyi.academy.codingbootcamp.week0.day2.Numbers2Test"},
          {"com.kovalevskyi.academy.codingbootcamp.week0.day3.PointTest"}
      },
      {
          {
              "com.kovalevskyi.academy.codingbootcamp.week1.day0.PointWithLabelTest",
              "com.kovalevskyi.academy.codingbootcamp.week1.day0.PointWithValueTest",
              "com.kovalevskyi.academy.codingbootcamp.week1.day0.SortingTest"
          },
          {
              "com.kovalevskyi.academy.codingbootcamp.week1.day1.StringUtilsTest",
              "com.kovalevskyi.academy.codingbootcamp.week1.day1.StdString1Test"
          },
          {"com.kovalevskyi.academy.codingbootcamp.week1.day2.ListTest"},
          {"com.kovalevskyi.academy.codingbootcamp.week1.day3.StdString2Test"}
      },
      {
          {
              "com.kovalevskyi.academy.codingbootcamp.week2.day0.MainPrintParamTest",
              "com.kovalevskyi.academy.codingbootcamp.week2.day0.MainPrintReversedParamTest",
              "com.kovalevskyi.academy.codingbootcamp.week2.day0.CalculatorTest",
              "com.kovalevskyi.academy.codingbootcamp.week2.day0.MainPrintSortedParamTest"
          },
          {
              "com.kovalevskyi.academy.codingbootcamp.week2.day1.TextPrinter2Test",
              "com.kovalevskyi.academy.codingbootcamp.week2.day1.TextPrinterTest"
          },
      }
    };

  @CommandLine.Option(
      names = {"-w", "--week"},
      description = "Number of the week to test",
      defaultValue = "-1")
  private int week;

  @CommandLine.Option(
      names = {"-d", "--day"},
      description = "Number of the day to test",
      defaultValue = "-1")
  private int day;

  @CommandLine.Option(
      names = {"-a", "--all"},
      description = "Run all tests",
      defaultValue = "false")
  private boolean all;

  @CommandLine.Option(
      names = {"-m", "--maven"},
      description = "Path to the maven home")
  private String mavenHome;

  @CommandLine.Option(
      names = {"-s", "--show"},
      description = "Show tests for week/day")
  private boolean show;

  @CommandLine.Option(
      names = {"-c", "--checkstyle"},
      description = "Run only checkstyle for week/day")
  private boolean checkstyle;

  @CommandLine.Option(
      names = {"-e", "--error"},
      description = "JUnit error mode (only error prints)")
  private boolean error;

  @CommandLine.Option(
      names = {"-t", "--test"},
      description = "Specific test to executed")
  private String test;

  @CommandLine.Option(
      names = {"-b", "--build"},
      description = "Build jar")
  private boolean build;

  @Override
  public Integer call() {
    var noSpecification = "You need to specify week and day like this: --week X --day Y";
    TestsConsolePrinter.setSilentMode(this.error);
    try {
      if (this.test != null && !this.all && (this.week == -1 || this.day == -1)) {
        System.out.println(noSpecification);
        return 0;
      }
      if (this.show) {
        if (this.week == -1 || this.day == -1) {
          System.out.println(noSpecification);
        } else {
          for (var dayTest : this.classNames[this.week][this.day]) {
            System.out.printf("Test: %s\n", dayTest);
          }
        }
        return 0;
      }
      if (this.checkstyle) {
        try {
          checkAllSourceFilesWithCheckstyle();
        } catch (CheckstyleException exception) {
          System.out.println(exception.getMessage());
        }
        return 0;
      }
      if (this.build) {
        mavenCompile();
        return 0;
      } else if (this.all) {
        if (this.week != -1 && this.day != -1) {
          System.out.println(
              "One can not ask Zeus to run all test and specific tests (week/day) "
                  + "at the same time!");
          return -1;
        }
        executeAllTests();
      } else if (this.test != null) {
        executeDayTest(this.test);
      } else {
        if (this.week == -1 && this.day == -1) {
          System.out.println("Launch Zeus with '-h' or '--help' to see instructions.");
          return -1;
        }
        if (this.week == -1 || this.day == -1) {
          System.out.println(noSpecification);
          return -1;
        } else {
          executeDayTests(this.classNames[this.week][this.day]);
        }
      }
    } catch (Exception e) {
      AnsiConsole.systemInstall();
      System.out.print(Ansi
          .ansi()
          .fgRed()
          .a("Zeus is VERY unhappy!!!!\n")
          .a(String.format("Error message: %s\n", e.getMessage()))
          .reset());
      AnsiConsole.systemUninstall();
      return -1;
    }
    return 0;
  }

  private void checkAllSourceFilesWithCheckstyle() throws IOException, CheckstyleException {
    var directory = new File(String.format(".%1$ssrc%1$smain%1$sjava", File.separator));
    if (!directory.exists()) {
      throw new FileNotFoundException("Directory of java source files is not exist!");
    }
    var javaFiles = FileExplorer.getFiles(directory.getAbsolutePath())
        .stream()
        .filter(entry -> entry.getName().endsWith(".java"))
        .collect(Collectors.toList());
    if (javaFiles.isEmpty()) {
      throw new FileNotFoundException("You have blank project! Nothing to check!");
    }
    Checkstyle.checkAll(Checks.GOOGLE_CHECKS, javaFiles);
  }

  private void mavenCompile() throws IOException, MavenInvocationException, CheckstyleException {
    checkAllSourceFilesWithCheckstyle();
    System.out.println("Zeus is about to start compiling your project");
    var request = new DefaultInvocationRequest();
    request.setPomFile(new File(String.format(".%spom.xml", File.separator)));
    request.setGoals(
        new ArrayList<>() {
          {
            add("clean");
            add("compile");
            add("package");
          }
        });
    var invoker = new DefaultInvoker();
    var mavenDefault = Optional.ofNullable(System.getenv("M2_HOME"));
    var mavenPath =
        Objects.requireNonNullElse(
            this.mavenHome,
            mavenDefault.orElseThrow(
                () ->
                    new FileNotFoundException(
                        "Configure Maven on your system! Either set M2_HOME or use key -m")));
    invoker.setMavenHome(new File(mavenPath));
    System.out.println("Zeus is about to execute 'mvn clean compile package'");
    invoker.execute(request);
  }

  private void executeAllTests() throws Exception {
    for (var weekTests : this.classNames) {
      for (var dayTests : weekTests) {
        executeDayTests(dayTests);
      }
    }
  }

  private void executeDayTest(String className) throws Exception {
    var testExecutor =
        (AbstractTestExecutor) Class.forName(className).getConstructors()[0].newInstance();
    testExecutor.executeTest();
  }

  private void executeDayTests(String[] classNames) throws Exception {
    for (var className : classNames) {
      executeDayTest(className);
    }
  }

  public static void main(String... args) {
    CommandLine commandLine = new CommandLine(new TheMighty());
    System.exit(commandLine.execute(args));
  }
}
