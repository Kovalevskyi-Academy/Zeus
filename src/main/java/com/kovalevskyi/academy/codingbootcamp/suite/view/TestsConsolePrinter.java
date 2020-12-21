package com.kovalevskyi.academy.codingbootcamp.suite.view;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Arrays;

public class TestsConsolePrinter implements TestWatcher, BeforeAllCallback, AfterAllCallback {

  private int successful = 0;
  private int failed = 0;
  private static boolean isSilentMode;

  public static void setSilentMode(boolean silentMode) {
    isSilentMode = silentMode;
  }

  @Override
  public void testSuccessful(ExtensionContext context) {
    successful++;
    if (!isSilentMode) {
      System.out.println(context.getDisplayName() + " - OK");
    }
  }

  @Override
  public void testFailed(ExtensionContext context, Throwable cause) {
    failed++;
    if (!isSilentMode) {
      System.out.println(context.getDisplayName() + " - BAD");
      if (cause instanceof java.lang.NoClassDefFoundError) {
        System.out.println(
            "class under test not found, very-very likely you have incorrectly set class path so Zeus can not find it.");
      }
      Arrays.stream(cause.getMessage().split("\n"))
          .sequential()
          .filter(s -> !s.isEmpty())
          .forEach(System.out::println);
    } else {
      System.out.println(cause.getMessage());
    }
  }

  @Override
  public void beforeAll(ExtensionContext extensionContext) {
    if (!isSilentMode) {
      var testClass = extensionContext.getTestClass();
      testClass.ifPresent(entry -> System.out.printf("Result of %s\n\n", entry.getSimpleName()));
    }
  }

  @Override
  public void afterAll(ExtensionContext extensionContext) {
    if (!isSilentMode) {
      System.out.printf("\nTotal: %d\n", successful + failed);
      System.out.printf("Successful: %d\n", successful);
      System.out.printf("Failed : %d\n", failed);
      System.out.print("------------------------------\n");
    }
  }
}
