package com.kovalevskyi.academy.codingbootcamp.suite;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

public class TestsConsolePrinter implements TestWatcher, BeforeAllCallback {

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