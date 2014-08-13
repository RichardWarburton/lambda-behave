package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.impl.AbstractSuiteRunner;
import org.junit.runners.model.InitializationError;

/**
 * This runner can be used to run a specification suite with a junit runner,
 */
public final class JunitSuiteRunner extends AbstractSuiteRunner {

	public JunitSuiteRunner(final Class<?> testClass) throws InitializationError {
		super(testClass);
	}
	
}
