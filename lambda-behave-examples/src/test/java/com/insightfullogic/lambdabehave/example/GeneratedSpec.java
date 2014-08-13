package com.insightfullogic.lambdabehave.example;

import com.insightfullogic.lambdabehave.JunitSuiteRunner;
import org.junit.runner.RunWith;

import static com.insightfullogic.lambdabehave.Suite.describe;
import static com.insightfullogic.lambdabehave.generators.Generator.*;

@RunWith(JunitSuiteRunner.class)
public class GeneratedSpec {{

    describe("a String", it -> {

        it.requires(10)
          .example(asciiStrings())
          .toShow("reversing a String twice returns the original String", (expect, str) -> {
              String same = new StringBuilder(str).reverse().reverse().toString();
              expect.that(same).isEqualTo(str);
          });

        it.requires(10)
          .example(strings(), integersUpTo(9))
          .toShow("adding a digit to the end of the String increments its length by one", (expect, str, x) -> {
              String result = str + x;
              expect.that(result.length()).is(str.length() + 1);
          });

    });

}}
