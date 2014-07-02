package com.insightfullogic.lambdabehave.testcases.data_driven;

import com.insightfullogic.lambdabehave.specifications.ThreeColumnDataSpecification;

import java.util.stream.Stream;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static org.mockito.Mockito.mock;

public class ThreeColDataDrivenByStream {

    public static final ThreeColumnDataSpecification<Integer, Integer, Integer> specification
            = mock(ThreeColumnDataSpecification.class);

{
    describe("a list data driven suite", it -> {
        it.uses(Stream.of(1, 2), Stream.of(2, 4), Stream.of(3, 6))
          .and(3, 6, 9)
          .toShow("%d %d %d", specification);
    });
}}
