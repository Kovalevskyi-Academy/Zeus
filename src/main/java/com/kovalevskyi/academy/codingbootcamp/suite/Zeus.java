package com.kovalevskyi.academy.codingbootcamp.suite;

import com.kovalevskyi.academy.codingbootcamp.suite.util.Checkstyle;
import com.kovalevskyi.academy.codingbootcamp.suite.util.Checkstyle.Checks;
import com.kovalevskyi.academy.codingbootcamp.suite.util.PathParser;
import com.kovalevskyi.academy.testing.AbstractTestExecutor;
import com.kovalevskyi.academy.testing.view.TestsConsolePrinter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.Callable;
import org.apache.maven.shared.invoker.DefaultInvocationRequest;
import org.apache.maven.shared.invoker.DefaultInvoker;
import org.apache.maven.shared.invoker.InvocationRequest;
import org.apache.maven.shared.invoker.Invoker;
import picocli.CommandLine;

@CommandLine.Command(
    name = "zeus",
    mixinStandardHelpOptions = true,
    version = Constants.VERSION,
    description = "Zeus the Mighty")
public class Zeus implements Callable<Integer> {

  private final String[][][] classNames = {
      {
        {PathParser.putAndGet("com.kovalevskyi.academy.codingbootcamp.week0.day0.MainTest")},
        {
          PathParser.putAndGet("com.kovalevskyi.academy.codingbootcamp.week0.day1.AlphabetTest"),
          PathParser.putAndGet("com.kovalevskyi.academy.codingbootcamp.week0.day1.NumbersTest")
        },
        {PathParser.putAndGet("com.kovalevskyi.academy.codingbootcamp.week0.day2.NumbersTest")},
        {PathParser.putAndGet("com.kovalevskyi.academy.codingbootcamp.week0.day3.PointTest")}
      },
      {
        {},
        {
          PathParser.putAndGet("com.kovalevskyi.academy.codingbootcamp.week1.day1.StringUtilsTest"),
          PathParser.putAndGet("com.kovalevskyi.academy.codingbootcamp.week1.day1.StdStringTest")
        },
        {PathParser.putAndGet("com.kovalevskyi.academy.codingbootcamp.week1.day2.ListTest")}
      },
      {
        {
          PathParser.putAndGet(
              "com.kovalevskyi.academy.codingbootcamp.week2.day0.MainPrintParamTest"),
          PathParser.putAndGet(
              "com.kovalevskyi.academy.codingbootcamp.week2.day0.MainPrintReversedParamTest"),
          PathParser.putAndGet("com.kovalevskyi.academy.codingbootcamp.week2.day0.CalculatorTest"),
          PathParser.putAndGet(
              "com.kovalevskyi.academy.codingbootcamp.week2.day0.MainPrintSortedParamTest")
        },
        {
          PathParser.putAndGet("com.kovalevskyi.academy.codingbootcamp.week2.day1"
                  + ".BoxGeneratorTest"),
          PathParser.putAndGet("com.kovalevskyi.academy.codingbootcamp.week2.day1"
                  + ".TextPrinter2Test"),
          PathParser.putAndGet("com.kovalevskyi.academy.codingbootcamp.week2.day1.TextPrinterTest")
        },
        {},
        {PathParser.putAndGet("com.kovalevskyi.academy.codingbootcamp.week2.day3.ListTest")}
      }
    };

  @CommandLine.Option(
      names = {"-w", "--week"},
      description = "number of the week to test",
      defaultValue = "-1")
  private int week;

  @CommandLine.Option(
      names = {"-d", "--day"},
      description = "number of the day to test",
      defaultValue = "-1")
  private int day;

  @CommandLine.Option(
      names = {"-a", "--all"},
      description = "run all tests",
      defaultValue = "false")
  private boolean all;

  @CommandLine.Option(
      names = {"-m", "--maven"},
      description = "path to the maven home")
  private String mavenHome;

  @CommandLine.Option(
      names = {"-s", "--show"},
      description = "show tests for week/day")
  private boolean show;

  @CommandLine.Option(
      names = {"-c", "--checkstyle"},
      description = "run only checkstyle for week/day")
  private boolean checkstyle;

  @CommandLine.Option(
      names = {"-e", "--error"},
      description = "JUnit error mode (only error prints)")
  private boolean error;

  @CommandLine.Option(
      names = {"-t", "--test"},
      description = "specific test to executed")
  private String test;

  @CommandLine.Option(
      names = {"-b", "--build"},
      description = "build jar")
  private boolean build;

  @Override
  public Integer call() {
    String noSpecification = "you need to specify week and day like this: --week X --day Y";
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
            System.out.printf("test: %s\n", dayTest);
          }
        }
        return 0;
      }
      if (this.checkstyle) {
        if (this.week == -1 || this.day == -1) {
          System.out.println(noSpecification);
        } else {
          for (var dayTest : this.classNames[this.week][this.day]) {
            Checkstyle.check(Checks.GOOGLE_CHECKS, dayTest);
          }
        }
        return 0;
      }
      if (this.build) {
        System.out.println("Zeus is about to start compiling your project");
        mavenCompile();
        System.out.println("Zeus happy");
        return 0;
      } else if (this.all) {
        if (this.week != -1 && this.day != -1) {
          System.out.println(
              "one can not ask Zeus to run all test and specific tests (week/day) "
                  + "at the same time!");
          return -1;
        }
        executeAllTests();
      } else if (this.test != null) {
        executeDayTest(this.test);
      } else {
        if (this.week == -1 || this.day == -1) {
          System.out.println(noSpecification);
          return -1;
        } else {
          executeDayTests(this.classNames[this.week][this.day]);
        }
      }
    } catch (Exception e) {
      System.out.println("Zeus is VERY unhappy!!!!");
      e.printStackTrace();
      return -1;
    }
    return 0;
  }

  private void mavenCompile() throws Exception {
    InvocationRequest request = new DefaultInvocationRequest();
    request.setPomFile(new File(System.getProperty("user.dir") + File.separator + "pom.xml"));
    request.setGoals(
        new ArrayList<>() {
          {
            add("clean");
            add("compile");
            add("package");
          }
        });
    Invoker invoker = new DefaultInvoker();
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
      Checkstyle.check(Checks.GOOGLE_CHECKS, className);
      executeDayTest(className);
    }
  }

  public static void main(String... args) {
    CommandLine commandLine = new CommandLine(new Zeus());
    int exitCode = commandLine.execute(args);
    if (exitCode < 0) {
      commandLine.usage(System.out);
    }
    System.exit(exitCode);
  }
}
