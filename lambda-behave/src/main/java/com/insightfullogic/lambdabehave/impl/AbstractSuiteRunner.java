package com.insightfullogic.lambdabehave.impl;

import java.util.List;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.insightfullogic.lambdabehave.SpecificationError;
import com.insightfullogic.lambdabehave.impl.reports.SpecificationReport;

public abstract class AbstractSuiteRunner extends ParentRunner<CompleteBehaviour> {

	protected static final Logger log = LoggerFactory.getLogger(AbstractSuiteRunner.class);

    protected final List<CompleteBehaviour> children;
    protected final String name;

    public AbstractSuiteRunner(final Class<?> testClass, String name, List<CompleteBehaviour> children) throws InitializationError {
        super(testClass);
        this.name = name;
        this.children = children;
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
    protected Description describeChild(final CompleteBehaviour child) {
        return Description.createTestDescription(child.getSuiteName(), child.getDescription());
    }

    @Override
    protected void runChild(final CompleteBehaviour child, final RunNotifier notifier) {
        try {
            Description childDescription = describeChild(child);
            notifier.fireTestStarted(childDescription);
            SpecificationReport report = child.playbackBehaviour();
            reportResults(notifier, report, childDescription);
        } catch (final Exception e) {
            notifier.fireTestFailure(new Failure(getDescription(), e));
            log.error(e.getMessage(), e);
        }
    }

    protected void reportResults(final RunNotifier notifier, final SpecificationReport spec, final Description test) {
        switch (spec.getResult()) {
            case SUCCESS:
                notifier.fireTestFinished(test);
                return;
            case FAILURE:
                notifier.fireTestFailure(new Failure(test, spec.getCause()));
                return;
            case ERROR:
            default:
                throw new SpecificationError(spec.getMessage(), spec.getCause());
        }
    }

}
