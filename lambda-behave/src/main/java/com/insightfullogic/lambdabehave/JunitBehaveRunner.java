package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.impl.reports.SuiteReport;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;

import static org.junit.runner.Description.createTestDescription;

/**
 * Wrapper to run lambda behave specifications under junit.
 */
public final class JunitBehaveRunner extends Runner {

    private final Class<?> testClass;
    private final Specifier specifier;
    private final Description suiteDescription;

    public JunitBehaveRunner(Class<?> testClass) {
        this.testClass = testClass;
        specifier = BehaveRunner.declareOnly(testClass);
        suiteDescription = createTestDescription(this.testClass, specifier.getSuiteName());
    }

    @Override
    public Description getDescription() {
        return suiteDescription;
    }

    @Override
    public void run(RunNotifier notifier) {
        try {
            Report report = new Report();
            specifier.checkSpecifications(report);
            reportResults(notifier, report);
        } catch (Exception e) {
            notifier.fireTestFailure(new Failure(getDescription(), e));
            // TODO: log
            e.printStackTrace();
        }
    }

    private void reportResults(RunNotifier notifier, Report report) {
        report.suites()
              .flatMap(SuiteReport::specifications)
              .forEach(spec -> {
                  Description test = createTestDescription(testClass, spec.getDescription());
                  notifier.fireTestStarted(test);
                  switch (spec.getResult()) {
                      case SUCCESS:
                          notifier.fireTestFinished(test);
                          return;
                      case FAILURE:
                          notifier.fireTestFailure(new Failure(test, new TestFailure(spec.getMessage())));
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
