package com.insightfullogic.lambdabehave.impl;

import com.insightfullogic.lambdabehave.impl.reports.SpecificationReport;

public interface CompleteBehaviour {
    SpecificationReport checkCompleteBehaviour();

    String getDescription();

    String getSuiteName();
}
