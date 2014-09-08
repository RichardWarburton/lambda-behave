package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.testcases.runner.ValidTestWithOneDescription;
import com.insightfullogic.lambdabehave.testcases.runner.ValidTestWithTwoDescriptions;
import org.junit.Test;
import org.junit.runner.Runner;
import org.junit.runners.model.InitializationError;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.runner.Description.createSuiteDescription;

public class JunitSuiteRunnerTest {

    @Test
    public void shouldNameSuiteAfterDefiningClass() throws InitializationError {
        JunitSuiteRunner testee = new JunitSuiteRunner(ValidTestWithOneDescription.class);
        assertEquals(createSuiteDescription(ValidTestWithOneDescription.class), testee.getDescription());
    }

    @Test
    public void shouldCreateChildRunnerForValidSingleDescriptionSuite() throws InitializationError {
        JunitSuiteRunner testee = new JunitSuiteRunner(ValidTestWithOneDescription.class);
        assertEquals(1, testee.getChildren().size());
    }

    @Test
    public void shouldCreateChildRunnerForEachDescriptionInClass() throws InitializationError {
        JunitSuiteRunner testee = new JunitSuiteRunner(ValidTestWithTwoDescriptions.class);
        assertEquals(2, testee.getChildren().size());
    }

    @Test
    public void shouldNamedChildrenAfterTheirSuites() throws InitializationError {
        JunitSuiteRunner testee = new JunitSuiteRunner(ValidTestWithTwoDescriptions.class);
        List<Runner> actual = testee.getChildren();
        assertEquals(createSuiteDescription("a thing"), actual.get(0).getDescription());
        assertEquals(createSuiteDescription("another thing"), actual.get(1).getDescription());
    }

    private static class InvalidDueToVisibility {

    }

    @Test(expected = SpecificationDeclarationException.class)
    public void shouldThrowSpecificationDeclarationExceptionWhenSuiteInvalid() throws InitializationError {
        new JunitSuiteRunner(InvalidDueToVisibility.class);
    }

}
