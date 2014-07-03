package com.insightfullogic.lambdabehave.generators;

import com.insightfullogic.lambdabehave.specifications.TwoColumnDataSpecification;

/**
 * Represents a two column table of data that has been automatically generated.
 */
public interface GeneratedTwoColumns<F, S> {

    /**
     * Specify the actual behaviour.
     *
     * @param description a human readable description of the behaviour you're expecting.
     * @param specification a function which describes in code the expected behaviour.
     */
    void toShow(String description, TwoColumnDataSpecification<F, S> specification);

}
