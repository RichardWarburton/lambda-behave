package com.insightfullogic.lambdabehave.generators;

import com.insightfullogic.lambdabehave.specifications.ColumnDataSpecification;

/**
 * A generated column represents a single series of data values
 * that has been automatically generated.
 */
public interface GeneratedColumn<T> {

    /**
     * Specify the actual behaviour.
     *
     * @param description a human readable description of the behaviour you're expecting.
     * @param specification a function which describes in code the expected behaviour.
     */
    void toShow(String description, ColumnDataSpecification<T> specification);

}
