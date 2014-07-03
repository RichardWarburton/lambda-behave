package com.insightfullogic.lambdabehave.testcases.data_driven;

import com.insightfullogic.lambdabehave.specifications.ThreeColumnDataSpecification;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.mock;

public class ThreeColDataDrivenWrongLength {

    public static final ThreeColumnDataSpecification<Integer, Integer, Integer> specification
            = mock(ThreeColumnDataSpecification.class);

{
    describe("a list data driven suite", it -> {
        it.uses(asList(1, 2), asList(2), asList(3, 6, 9))
          .and(3, 6, 9)
          .toShow("%d %d %d", specification);
    });
}}
