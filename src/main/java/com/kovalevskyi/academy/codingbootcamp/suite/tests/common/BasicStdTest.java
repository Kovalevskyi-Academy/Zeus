package com.kovalevskyi.academy.codingbootcamp.suite.tests.common;

import com.kovalevskyi.academy.codingbootcamp.suite.AbstractTestExecutor;
import org.junit.Before;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

abstract public class BasicStdTest extends AbstractTestExecutor {

    protected final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

}
