package com.kovalevskyi.academy.codingbootcamp.suite.week0.day0;


import com.kovalevskyi.academy.codingbootcamp.suite.AbstractTestExecutor;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;

public class MainTest extends AbstractTestExecutor {
  @Test
  public void testGreen() {
    assertThat(true).isTrue();
  }
}
