package com.insightfullogic.lambdabehave.testcases.data_driven;

import com.insightfullogic.lambdabehave.specifications.TwoColumnDataSpecification;

import java.util.stream.Stream;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static org.mockito.Mockito.mock;

public class TwoColDataDrivenByStream {

    public static final TwoColumnDataSpecification<Integer, Integer> specification
            = mock(TwoColumnDataSpecification.class);

{
    describe("a stream data driven suite", it -> {
        it.uses(Stream.of(1, 2), Stream.of(2, 4))
          .and(3, 6)
          .toShow("%d %d", specification);
    });
}}
