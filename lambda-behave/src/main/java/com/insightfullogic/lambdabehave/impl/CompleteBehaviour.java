package com.insightfullogic.lambdabehave.impl;

import com.insightfullogic.lambdabehave.impl.reports.SpecificationReport;

import java.util.Objects;

public interface CompleteBehaviour {

    SpecificationReport playbackBehaviour();

    String getDescription();

    String getSuiteName();

    default boolean hasDescription(String description) {
        Objects.requireNonNull(description);
        return description.equals(getDescription());
    }

}
