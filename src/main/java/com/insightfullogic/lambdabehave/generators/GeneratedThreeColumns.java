package com.insightfullogic.lambdabehave.generators;

import com.insightfullogic.lambdabehave.specifications.ThreeColumnDataSpecification;

public interface GeneratedThreeColumns<F, S, T> {

    void toShow(String description, ThreeColumnDataSpecification<F, S, T> specification);

}
