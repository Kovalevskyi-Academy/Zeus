package com.kovalevskyi.academy.codingbootcamp.suite;

import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(name = "zeus", mixinStandardHelpOptions = true, version = Constants.VERSION,
        description = "Prints the checksum (MD5 by default) of a file to STDOUT.")
public class Zeus implements Callable<Integer> {

  private final String[][][] classNames = {
          {
                  {
                          "com.kovalevskyi.academy.codingbootcamp.suite.week0.day0.MainTest"
                  },
                  {
                          "com.kovalevskyi.academy.codingbootcamp.suite.week0.day1.AlphabetTest",
                          "com.kovalevskyi.academy.codingbootcamp.suite.week0.day1.NumbersTest"
                  }
          }
  };

  @CommandLine.Option(names = { "-w", "--week" }, description = "number of the week to test", defaultValue = "-1")
  private int week;

  @CommandLine.Option(names = { "-d", "--day" }, description = "number of the day to test", defaultValue = "-1")
  private int day;

  @Override
  public Integer call() {
    try{
      if (this.week == -1 || this.day == -1) {
        System.out.println("error");
        return 0;
      }
      executeTest();
    } catch (Exception e) {
      System.out.println(String.format("Error: %s", e));
      return -1;
    }
    return 0;
  }

  private void executeTest() throws Exception {
    var classNames = this.classNames[this.week][this.day];
    for (var className : classNames) {
      var testExecutor = (AbstractTestExecutor)
              Class.forName(className)
                      .getConstructors()[0].newInstance();
      testExecutor.executeTest();
    }
  }

  public static void main(String... args) {
    int exitCode = new CommandLine(new Zeus()).execute(args);
    System.exit(exitCode);
  }
}
