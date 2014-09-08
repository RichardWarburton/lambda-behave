package com.insightfullogic.lambdabehave;

import java.util.List;
import java.util.Map;

import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.ParentRunner;
import org.junit.runners.model.InitializationError;

import com.insightfullogic.lambdabehave.impl.AbstractSuiteRunner;
import com.insightfullogic.lambdabehave.impl.CompleteBehaviour;
import com.insightfullogic.lambdabehave.impl.Specifier;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toList;

/**
 * This runner can be used to run a specification suite with a junit runner,
 */
public final class JunitSuiteRunner extends ParentRunner<Runner> {
	
    private final List<Runner> fRunners;
        
    public JunitSuiteRunner(Class<?> testClass) throws InitializationError {
		super(testClass);
		fRunners = createRunners(testClass);
	}

	private static List<Runner> createRunners(Class<?> testClass)
			throws InitializationError {
		Map<String, List<CompleteBehaviour>> suites = findSuitesInClass(testClass);

		return suites
				.entrySet().stream()
				.map(e -> tryCreateRunner(testClass, e.getKey(), e.getValue()))
				.collect(toList());

	}

	private static Map<String, List<CompleteBehaviour>> findSuitesInClass(
			Class<?> testClass) {
		List<Specifier> specifiers = BehaveRunner.declareOnly(testClass);

		return specifiers.stream()
                             .flatMap(Specifier::completeBehaviours)
                             .collect(groupingBy(b -> b.getSuiteName(), toList()));
	}
	
	private static ConcreteRunner tryCreateRunner(Class<?> testClass,
			String name, List<CompleteBehaviour> children) {
		try {
			return new ConcreteRunner(testClass, name, children);
		} catch (InitializationError e) {
			// Is this reachable in practice? Declared InitializationError is translated
			// lower down chain
			throw new SpecificationDeclarationException(
					"Unable to create specification from: " + testClass, e);
		}
	}

	@Override
	protected List<Runner> getChildren() {
		return fRunners;
	}

	@Override
	protected org.junit.runner.Description describeChild(Runner child) {
        return child.getDescription();
	}

	@Override
	protected void runChild(Runner child, RunNotifier notifier) {
		child.run(notifier);
	}
	
}

class ConcreteRunner extends AbstractSuiteRunner {
	public ConcreteRunner(Class<?> testClass, String name, List<CompleteBehaviour> children) throws InitializationError {
		super(testClass,name, children);
	}
}

