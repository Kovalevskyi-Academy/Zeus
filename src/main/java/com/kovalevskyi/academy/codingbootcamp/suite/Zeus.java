package com.kovalevskyi.academy.codingbootcamp.suite;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.Callable;

import org.apache.maven.shared.invoker.*;
import picocli.CommandLine;

@CommandLine.Command(
    name = "zeus",
    mixinStandardHelpOptions = true,
    version = Constants.VERSION,
    description = "Zeus the Mighty")
public class Zeus implements Callable<Integer> {

  private final String[][][] classNames = {
    {
      {"com.kovalevskyi.academy.codingbootcamp.suite.tests.week0.day0.MainTest"},
      {
        "com.kovalevskyi.academy.codingbootcamp.suite.tests.week0.day1.AlphabetTest",
        "com.kovalevskyi.academy.codingbootcamp.suite.tests.week0.day1.NumbersTest"
      },
      {"com.kovalevskyi.academy.codingbootcamp.suite.tests.week0.day2.NumbersTest"},
      {"com.kovalevskyi.academy.codingbootcamp.suite.tests.week0.day3.PointTest"}
    },
    {
      {},
      {
        "com.kovalevskyi.academy.codingbootcamp.suite.tests.week1.day1.StringUtilsTest",
        "com.kovalevskyi.academy.codingbootcamp.suite.tests.week1.day1.StdStringTest"
      },
      {"com.kovalevskyi.academy.codingbootcamp.suite.tests.week1.day2.ListTest"}
    },
    {
      {
        "com.kovalevskyi.academy.codingbootcamp.suite.tests.week2.day0.MainPrintParamTest",
        "com.kovalevskyi.academy.codingbootcamp.suite.tests.week2.day0.MainPrintReversedParamTest",
        "com.kovalevskyi.academy.codingbootcamp.suite.tests.week2.day0.CalculatorTest",
        "com.kovalevskyi.academy.codingbootcamp.suite.tests.week2.day0.MainPrintSortedParamTest"
      },
      {},
      {}
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
      description = "path to the maven home",
      required = true)
  private String mavenHome;

  @Override
  public Integer call() {
    try {
      if (!this.all && (this.week == -1 || this.day == -1)) {
        System.out.println("you need to specify week and day like this: --week X --day Y");
        return 0;
      }
      System.out.println("Zeus is about to start compiling your project");
      mavenCompile();
      System.out.println("Zeus happy");
      if (this.all) {
        if (this.week != -1 && this.day != -1) {
          System.out.println(
              "one can not ask Zeus to run all test and specific tests (week/day) "
                  + "at the same time!");
          return 0;
        }
        executeAllTests();
      } else {
        executeTest(this.classNames[this.week][this.day]);
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
    request.setPomFile(new File("./pom.xml"));
    request.setGoals(
        new ArrayList<>() {
          {
            add("clean");
            add("compile");
            add("package");
          }
        });
    Invoker invoker = new DefaultInvoker();
    invoker.setMavenHome(new File(mavenHome));
    System.out.println("Zeus is about to execute 'mvn clean compile package'");
    invoker.execute(request);
  }

  private void executeAllTests() throws Exception {
    for (var weekTests : this.classNames) {
      for (var dayTests : weekTests) {
        executeTest(dayTests);
      }
    }
  }

  private void executeTest(String[] classNames) throws Exception {
    for (var className : classNames) {
      var testExecutor =
          (AbstractTestExecutor) Class.forName(className).getConstructors()[0].newInstance();
      testExecutor.executeTest();
    }
  }

  public static void main(String... args) {
    int exitCode = new CommandLine(new Zeus()).execute(args);
    System.exit(exitCode);
  }
}
