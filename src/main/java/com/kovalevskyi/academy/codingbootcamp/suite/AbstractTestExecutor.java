package com.kovalevskyi.academy.codingbootcamp.suite;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

public abstract class AbstractTestExecutor {

  SummaryGeneratingListener listener = new SummaryGeneratingListener();

  public void executeTest() {
    LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
        .selectors(selectClass(this.getClass()))
        .build();
    Launcher launcher = LauncherFactory.create();
    TestPlan testPlan = launcher.discover(request);
    launcher.registerTestExecutionListeners(listener);
    launcher.execute(request);
  }
}
