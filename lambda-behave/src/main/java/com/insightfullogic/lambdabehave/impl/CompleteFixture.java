package com.insightfullogic.lambdabehave.impl;

import com.insightfullogic.lambdabehave.Block;
import com.insightfullogic.lambdabehave.impl.reports.SpecificationReport;

final class CompleteFixture implements CompleteBehaviour {

    private final String description;
    private final Block block;

    public CompleteFixture(final String description, final Block block) {
        this.description = description;
        this.block = block;
    }

    @Override
    public SpecificationReport checkCompleteBehaviour() {
        try {
            block.run();
            return SpecificationReport.success(getDescription());
        } catch (final Exception e) {
            return SpecificationReport.error(getDescription(), e);
        }
    }

    @Override
    public String getDescription() {
        return description;
    }

}
