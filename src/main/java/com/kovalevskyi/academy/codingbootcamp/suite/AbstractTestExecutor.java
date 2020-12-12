package com.kovalevskyi.academy.codingbootcamp.suite;

import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

public abstract class AbstractTestExecutor {
    private final Class<?> classToTest;

    protected AbstractTestExecutor(Class<?> classToTest) {
        this.classToTest = classToTest;
    }

    public void executeTest() {
        JUnitCore junit = new JUnitCore();
        junit.addListener(new TextListener(System.out));
        junit.run(classToTest);
    }
}
