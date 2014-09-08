package com.insightfullogic.lambdabehave;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.Description;
import org.junit.runner.Runner;
import org.junit.runners.model.InitializationError;

public class JunitSuiteRunnerTest {


	public static class ValidTestWithOneDescription {{
	    describe("a thing", it -> {
	        it.should("pass)", expect -> {
	        });
	});
	}}
	
	@Test
	public void shouldNameSuiteAfterDefiningClass() throws InitializationError {
		JunitSuiteRunner testee = new JunitSuiteRunner(ValidTestWithOneDescription.class);
		assertEquals(Description.createSuiteDescription(ValidTestWithOneDescription.class),testee.getDescription());
	}

	@Test
	public void shouldCreateChildRunnerForValidSingleDescriptionSuite() throws InitializationError {
		JunitSuiteRunner testee = new JunitSuiteRunner(ValidTestWithOneDescription.class);
		assertEquals(1,testee.getChildren().size());
	}
	
	public static class ValidTestWithTwoDescriptions {{
	    describe("a thing", it -> {
	        it.should("pass)", expect -> {
	        });
	});
	    
	    describe("another thing", it -> {
	        it.should("pass)", expect -> {
	        });
	});
	    
	}}
	
	@Test
	public void shouldCreateChildRunnerForEachDescriptionInClass() throws InitializationError {
		JunitSuiteRunner testee = new JunitSuiteRunner(ValidTestWithTwoDescriptions.class);
		assertEquals(2,testee.getChildren().size());
	}
	
	@Test
	public void shouldNamedChildrenAfterTheirSuites() throws InitializationError {
		JunitSuiteRunner testee = new JunitSuiteRunner(ValidTestWithTwoDescriptions.class);
		List<Runner> actual = testee.getChildren();
		assertEquals(Description.createSuiteDescription("a thing"),actual.get(0).getDescription());
		assertEquals(Description.createSuiteDescription("another thing"),actual.get(1).getDescription());		
	}
	
	private static class InvalidDueToVisibility {
		
	}
	
	@Test(expected = SpecificationDeclarationException.class)
	public void shouldThrowSpecificationDeclarationExceptionWhenSuiteInvalid()
			throws InitializationError {
		new JunitSuiteRunner(InvalidDueToVisibility.class);
	}

}
