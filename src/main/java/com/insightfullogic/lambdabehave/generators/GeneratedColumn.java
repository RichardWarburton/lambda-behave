package com.insightfullogic.lambdabehave.generators;

import com.insightfullogic.lambdabehave.specifications.ColumnDataSpecification;

public interface GeneratedColumn<T> {

    void toShow(String description, ColumnDataSpecification<T> specification);

}
