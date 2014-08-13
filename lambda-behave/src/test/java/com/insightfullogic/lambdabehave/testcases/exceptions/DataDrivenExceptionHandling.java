package com.insightfullogic.lambdabehave.testcases.exceptions;

import static com.insightfullogic.lambdabehave.Suite.describe;

public class DataDrivenExceptionHandling {{

    describe("exceptions in data driven tests", it -> {

        it.uses(1)
          .toShow("hello %d", (expect, n1) -> {
              expect.exception(Exception.class, () -> {
                  throw new Exception();
              });
          });

    });

}}
