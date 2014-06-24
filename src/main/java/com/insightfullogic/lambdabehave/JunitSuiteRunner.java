package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.impl.CompleteBehaviour;
import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.impl.reports.SpecificationReport;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * This runner can be used to run a specification suite with a junit runner,
 */
public final class JunitSuiteRunner extends ParentRunner<CompleteBehaviour> {

    private static final Logger log = LoggerFactory.getLogger(JunitSuiteRunner.class);

    private final Class<?> testClass;
    private final List<CompleteBehaviour> children;
    private final String name;

    public JunitSuiteRunner(Class<?> testClass) throws InitializationError {
        super(testClass);
        this.testClass = testClass;
        Specifier specifier = BehaveRunner.declareOnly(testClass);
        name = specifier.getSuiteName();
        children = specifier.completeBehaviours().collect(toList());
    }

    @Override
    protected String getName() {
        return name;
    }

    @Override
    protected List<CompleteBehaviour> getChildren() {
        return children;
    }

    @Override
    protected Description describeChild(CompleteBehaviour child) {
        return Description.createTestDescription(getName(), child.getDescription());
    }

    @Override
    protected void runChild(CompleteBehaviour child, RunNotifier notifier) {
        try {
            SpecificationReport report = child.checkCompleteBehaviour();
            reportResults(notifier, report, describeChild(child));
        } catch (Exception e) {
            notifier.fireTestFailure(new Failure(getDescription(), e));
            log.error(e.getMessage(), e);
        }
    }

    private void reportResults(RunNotifier notifier, SpecificationReport spec, Description test) {
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
