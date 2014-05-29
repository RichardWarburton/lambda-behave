package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.specifications.OneColumn;
import com.insightfullogic.lambdabehave.specifications.OneColumnDataSpecification;
import com.insightfullogic.lambdabehave.specifications.Specification;
import com.insightfullogic.lambdabehave.expectations.Expect;
import com.insightfullogic.lambdabehave.impl.reports.Report;

public final class Description {

    private final Specifier specifier;

    public Description(Specifier specifier) {
        this.specifier = specifier;
    }

    public void should(String description, Specification specification) {
        specifier.specifyBehaviour(description, specification);
    }

    public <T> OneColumn<T> should(String description, OneColumnDataSpecification<T> specification) {
        return new OneColumn<>(specifier, description, specification);
    }

}
