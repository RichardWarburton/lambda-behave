package com.insightfullogic.lambdabehave.testcases.data_driven;

import com.insightfullogic.lambdabehave.specifications.ColumnDataSpecification;

import java.util.stream.Stream;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static org.mockito.Mockito.mock;

public class DataDrivenByStream {

    public static final ColumnDataSpecification<Integer> specification = mock(ColumnDataSpecification.class);

{
    describe("a stream data driven suite", it -> {
        it.uses(Stream.of(1, 2))
          .and(3)
          .toShow("%d", specification);
    });
}}
