package com.insightfullogic.lambdabehave.impl;

import com.insightfullogic.lambdabehave.expectations.Expect;
import com.insightfullogic.lambdabehave.impl.reports.SpecificationReport;
import com.insightfullogic.lambdabehave.specifications.Specification;

import java.util.List;

import static com.insightfullogic.lambdabehave.impl.reports.SpecificationReport.*;

/**
 * A complete behaviour is a composite object consisting of a specification behaviour with its associated
 * setup and teardown behaviours.
 */
final class CompleteSpecification implements CompleteBehaviour {

    private final List<Runnable> prefixes;
    private final Behaviour behaviour;
    private final List<Runnable> postfixes;
    private final String suiteName;

    public CompleteSpecification(List<Runnable> prefixes, Behaviour behaviour, List<Runnable> postfixes, String suiteName) {
        this.prefixes = prefixes;
        this.behaviour = behaviour;
        this.postfixes = postfixes;
        this.suiteName = suiteName;
    }

    @Override
    public SpecificationReport checkCompleteBehaviour() {
        runAll(prefixes);
        SpecificationReport report = checkBehaviour();
        runAll(postfixes);
        return report;
    }

    private SpecificationReport checkBehaviour() {
        Specification specification = behaviour.getSpecification();
        String description = behaviour.getDescription();
        try {
            Expect expect = new Expect();
            specification.specifyBehaviour(expect);
            return success(description);
        } catch (AssertionError cause) {
            return failure(description, cause);
        } catch (Throwable cause) {
            return error(description, cause);
        }
    }

    private void runAll(List<Runnable> blocks) {
        blocks.forEach(Runnable::run);
    }

    @Override
    public String getDescription() {
        return behaviour.getDescription();
    }

    public String getSuiteName() {
        return suiteName;
    }
}
