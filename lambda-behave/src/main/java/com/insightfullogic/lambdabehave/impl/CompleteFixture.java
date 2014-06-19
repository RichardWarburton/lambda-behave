package com.insightfullogic.lambdabehave.impl;

import com.insightfullogic.lambdabehave.impl.reports.SpecificationReport;

final class CompleteFixture implements CompleteBehaviour {

    private final String description;
    private final Runnable block;

    public CompleteFixture(String description, Runnable block) {
        this.description = description;
        this.block = block;
    }

    @Override
    public SpecificationReport checkCompleteBehaviour() {
        try {
            block.run();
            return SpecificationReport.success(getDescription());
        } catch (Exception e) {
            return SpecificationReport.error(getDescription(), e);
        }
    }

    @Override
    public String getDescription() {
        return description;
    }

}
