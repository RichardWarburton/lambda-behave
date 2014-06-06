package com.insightfullogic.lambdabehave;

import org.junit.Test;

import javax.annotation.concurrent.Immutable;

/**
 * Junit test to bootstrap the running of the specification suite
 */
@Immutable
public class BehaveSuiteTest {

    @Test
    public void entryPoint() {
        new BehaveRunner("com.insightfullogic.lambdabehave*Spec")
                .runSpecifications()
                .printReport();
    }

}
