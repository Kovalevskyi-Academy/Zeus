package com.kovalevskyi.academy.codingbootcamp.suite.tests.week0.day0;

import static com.google.common.truth.Truth.assertThat;

import com.kovalevskyi.academy.codingbootcamp.suite.AbstractTestExecutor;
import org.junit.jupiter.api.Test;

public class MainTest extends AbstractTestExecutor {
  @Test
  public void testGreen() {
    assertThat(true).isTrue();
  }
}
