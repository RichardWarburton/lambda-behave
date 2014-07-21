package com.insightfullogic.lambdabehave;

import org.junit.runners.model.InitializationError;

import com.insightfullogic.lambdabehave.impl.AbstractSuiteRunner;

/**
 * This runner can be used to run a specification suite with a junit runner,
 */
public final class JunitSuiteRunner extends AbstractSuiteRunner {

	public JunitSuiteRunner(Class<?> testClass) throws InitializationError {
		super(testClass);
	}
	
}