package com.insightfullogic.lambdabehave.generated;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;
import com.insightfullogic.lambdabehave.generators.Generator;
import com.insightfullogic.lambdabehave.generators.SourceGenerator;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.insightfullogic.lambdabehave.generators.Generator.*;
import static com.insightfullogic.lambdabehave.generators.SourceGenerator.deterministicNumbers;

@RunWith(JunitSuiteRunner.class)
public class ValueGenerationSpec {{

    describe("value generation", it -> {

        List<Generator<?>> generators = Arrays.asList(
            asciiCharacters(),
            asciiStrings(),
            doubles(),
            floats(),
            integersUpTo(Integer.MAX_VALUE),
            longs(),
            strings());

        it.requires(30)
          .example(integersUpTo(100))
          .toShow("value generation is deterministic", (expect, source) -> {
              long seed = source + System.currentTimeMillis();
              SourceGenerator generator1 = deterministicNumbers(seed);
              SourceGenerator generator2 = deterministicNumbers(seed);

              generators.forEach(gen -> {
                  Object value1 = gen.generate(generator1);
                  Object value2 = gen.generate(generator2);

                  expect.that(value1).isEqualTo(value2);
              });
          });

    });

}}
