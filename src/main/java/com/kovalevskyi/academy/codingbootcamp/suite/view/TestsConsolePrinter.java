package com.kovalevskyi.academy.codingbootcamp.suite.view;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestsConsolePrinter implements TestWatcher, BeforeAllCallback, AfterAllCallback {

  private int successful = 0;
  private int failed = 0;

  @Override
  public void testSuccessful(ExtensionContext context) {
    successful++;
    System.out.println(context.getDisplayName() + " - OK");
  }

  @Override
  public void testFailed(ExtensionContext context, Throwable cause) {
    failed++;
    System.out.println(context.getDisplayName() + " - BAD");
    var message = cause.getMessage().split("\n");
    for (String line : message) {
      if (!line.isEmpty()) {
        System.out.println(" " + line);
      }
    }
  }

  @Override
  public void beforeAll(ExtensionContext extensionContext) {
    var testClass = extensionContext.getTestClass();
    testClass.ifPresent(entry -> System.out.printf("Result of %s\n\n", entry.getSimpleName()));
  }

  @Override
  public void afterAll(ExtensionContext extensionContext) {
    System.out.printf("\nTotal: %d\n", successful + failed);
    System.out.printf("Successful: %d\n", successful);
    System.out.printf("Failed : %d\n", failed);
    System.out.print("------------------------------\n");
  }
}