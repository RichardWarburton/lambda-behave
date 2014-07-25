package com.insightfullogic.lambdabehave.testcases.generated;

import com.insightfullogic.lambdabehave.generators.Generator;
import com.insightfullogic.lambdabehave.specifications.ColumnDataSpecification;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static org.mockito.Mockito.mock;

public class GeneratedExample {

    public static ColumnDataSpecification<Integer> mockSpec = mock(ColumnDataSpecification.class);

    { describe("an example of testcase generation", it -> {

        it.requires(1)
          .example(Generator.integersUpTo(1))
          .and(20)
          .toShow("%d", mockSpec);

    });

}}
