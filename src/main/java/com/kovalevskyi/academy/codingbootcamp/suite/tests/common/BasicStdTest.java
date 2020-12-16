package com.kovalevskyi.academy.codingbootcamp.suite.tests.common;

import com.kovalevskyi.academy.codingbootcamp.suite.AbstractTestExecutor;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;

public abstract class BasicStdTest extends AbstractTestExecutor {

  protected final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @Before
  public void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }
}