package com.insightfullogic.lambdabehave.impl;

import com.insightfullogic.lambdabehave.Block;
import com.insightfullogic.lambdabehave.impl.reports.SpecificationReport;

final class CompleteFixture implements CompleteBehaviour {

    private final String suiteName;
    private final String description;
    private final Block block;

    public CompleteFixture(final String suiteName, final String description, final Block block) {
        this.suiteName = suiteName;
        this.description = description;
        this.block = block;
    }

    @Override
    public SpecificationReport playbackBehaviour() {
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

    @Override
    public String getSuiteName() {
        return suiteName;
    }

}
