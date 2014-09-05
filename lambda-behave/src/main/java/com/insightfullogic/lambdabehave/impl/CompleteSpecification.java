package com.insightfullogic.lambdabehave.impl;

import com.insightfullogic.lambdabehave.expectations.Expect;
import com.insightfullogic.lambdabehave.impl.reports.SpecificationReport;
import com.insightfullogic.lambdabehave.specifications.Specification;

import java.util.Optional;

import static com.insightfullogic.lambdabehave.impl.reports.SpecificationReport.*;

/**
 * A complete behaviour is a composite object consisting of a specification behaviour with its associated
 * setup and teardown behaviours.
 */
final class CompleteSpecification implements CompleteBehaviour {

    private final Blocks prefixes;
    private final Specification specification;
    private final String description;
    private final Blocks postfixes;
    private final String suiteName;

    public CompleteSpecification(final Specification specification, final String description, final String suiteName) {
        this(new Blocks(suiteName), specification, description, new Blocks(suiteName), suiteName);
    }

    public CompleteSpecification(final Blocks prefixes, final Specification specification, final String description, final Blocks postfixes, final String suiteName) {
        this.prefixes = prefixes;
        this.specification = specification;
        this.description = description;
        this.postfixes = postfixes;
        this.suiteName = suiteName;
    }

    @Override
    public SpecificationReport playbackBehaviour() {
        SpecificationReport report = prefixes.runAll(getDescription())
                                    .orElseGet(this::checkBehaviour);

        Optional<SpecificationReport> suffixReport = postfixes.runAll(getDescription());
        if (report.isSuccess() && suffixReport.isPresent()) {
            return suffixReport.get();
        } else {
            return report;
        }
    }

    private SpecificationReport checkBehaviour() {
        try {
            Expect expect = new Expect();
            specification.specifyBehaviour(expect);
            return success(description);
        } catch (final AssertionError cause) {
            return failure(description, cause);
        } catch (final Throwable cause) {
            return error(description, cause);
        }
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getSuiteName() {
        return suiteName;
    }
}
