package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.impl.reports.ReportFactory;
import com.insightfullogic.lambdabehave.impl.reports.SuiteReport;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

import static java.util.Collections.singletonList;
import static org.junit.runner.Description.createSuiteDescription;
import static org.junit.runner.Description.createTestDescription;

/**
 * .
 */
public class JunitBehaveRunner extends Runner {

    private final Class<?> testClass;
    private final Description suiteDescription;

    public JunitBehaveRunner(Class<?> testClass) {
        this.testClass = testClass;
        suiteDescription = createSuiteDescription(testClass);
    }

    @Override
    public Description getDescription() {
        return suiteDescription;
    }

    @Override
    public void run(RunNotifier notifier) {
        try {
            notifier.fireTestStarted(suiteDescription);
            BehaveRunner runner = new BehaveRunner(singletonList(testClass));
            runner.runSpecifications();
            reportResults(notifier);
        } catch (Exception e) {
            notifier.fireTestFailure(new Failure(getDescription(), e));
            // TODO: log
            e.printStackTrace();
        }
    }

    private void reportResults(RunNotifier notifier) {
        Report report = ReportFactory.getReport();
        report.suites()
              .flatMap(SuiteReport::specifications)
              .forEach(spec -> {
                  Description description = Description.createTestDescription(testClass, spec.getDescription());
                  notifier.fireTestStarted(description);
                  switch (spec.getResult()) {
                      case SUCCESS:
                          notifier.fireTestFinished(description);
                          return;
                      case FAILURE:
                          notifier.fireTestFailure(new Failure(description, new TestFailure(spec.getMessage())));
                          return;
                      case ERROR:
                          throw new SpecificationError(spec.getMessage());
                  }
              });
    }

    public static class SpecificationError extends RuntimeException {
        public SpecificationError(String message) {
            super(message);
        }
    }

    public static class TestFailure extends RuntimeException {
        public TestFailure(String message) {
            super(message);
        }
    }

}
