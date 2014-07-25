package com.insightfullogic.lambdabehave.generated;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;
import com.insightfullogic.lambdabehave.generators.SourceGenerator;
import org.junit.runner.RunWith;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.insightfullogic.lambdabehave.Suite.describe;

@RunWith(JunitSuiteRunner.class)
public class SourceGeneratorSpec {{

    describe("value generation", it -> {

        SourceGenerator valueGenerator = SourceGenerator.values(1, 10, 20);

        it.uses(IntStream.range(0, 10).boxed())
          .toShow("source values are <= the maxValue ", (expect, maxValue) -> {
              for (int i = 0; i < 3; i++) {
                  int value = valueGenerator.generateInt(maxValue);
                  expect.that(value).isLessThanOrEqualTo(maxValue);
              }
          });

        it.uses(Stream.of(1, 10, 20, 1, 10, 20, 1, 10, 20))
          .toShow("values wrap around their requirement ", (expect, x) -> {
              int value = valueGenerator.generateInt(20);
              expect.that(value).isEqualTo(x);
          });
    });

}}
