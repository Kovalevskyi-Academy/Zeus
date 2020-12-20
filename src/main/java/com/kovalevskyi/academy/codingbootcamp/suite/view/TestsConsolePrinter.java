package com.kovalevskyi.academy.codingbootcamp.suite.view;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

public class TestsConsolePrinter implements TestWatcher, BeforeAllCallback {

  public static void printSummary(TestExecutionSummary summary) {
    var ms = summary.getTimeFinished() - summary.getTimeStarted();
    System.out.print("*\n");
    System.out.printf("* Tests finished after %d ms\n", ms);
    System.out.printf("* Total: %d\n", summary.getTestsFoundCount());
    System.out.printf("* Successful: %d\n", summary.getTestsSucceededCount());
    System.out.printf("* Failed : %d\n", summary.getTestsFailedCount());
    System.out.print("------------------------------\n");
  }

  @Override
  public void testSuccessful(ExtensionContext context) {
    System.out.println("* " + context.getDisplayName() + " - OK");
  }

  @Override
  public void testFailed(ExtensionContext context, Throwable cause) {
    System.out.println("* " + context.getDisplayName() + " - BAD");
    var message = cause.getMessage().split("\n");
    for (String line : message) {
      if (!line.isEmpty()) {
        System.out.println("* ~ " + line);
      }
    }
  }

  @Override
  public void beforeAll(ExtensionContext extensionContext) {
    var testClass = extensionContext.getTestClass();
    if (testClass.isPresent()) {
      System.out.print("------------------------------\n");
      System.out.printf("* Result of %s\n", testClass.get().getSimpleName());
      System.out.print("*\n");
    }
  }
}