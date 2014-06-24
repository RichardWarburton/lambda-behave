package com.insightfullogic.lambdabehave.testcases.data_driven;

import com.insightfullogic.lambdabehave.specifications.ColumnDataSpecification;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;

public class DataDrivenByList {

    public static final ColumnDataSpecification<Integer> specification = mock(ColumnDataSpecification.class);

{
    describe("a list data driven suite", it -> {
        it.uses(asList(1, 2))
          .and(3)
          .toShow("%d", specification);
    });
}}
