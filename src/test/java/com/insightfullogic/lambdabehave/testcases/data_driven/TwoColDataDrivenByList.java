package com.insightfullogic.lambdabehave.testcases.data_driven;

import com.insightfullogic.lambdabehave.specifications.TwoColumnDataSpecification;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;

public class TwoColDataDrivenByList {

    public static final TwoColumnDataSpecification<Integer, Integer> specification
            = mock(TwoColumnDataSpecification.class);

{
    describe("a list data driven suite", it -> {
        it.uses(asList(1, 2), asList(2, 4))
          .and(3, 6)
          .toShow("%d %d", specification);
    });
}}
