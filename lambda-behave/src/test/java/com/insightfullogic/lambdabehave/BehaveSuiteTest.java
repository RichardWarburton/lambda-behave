package com.insightfullogic.lambdabehave;

import org.junit.Test;

/**
 * Junit test to bootstrap the running of the specification suite
 */
public class BehaveSuiteTest {

    @Test
    public void entryPoint() {
        new BehaveRunner("com.insightfullogic.lambdabehave*Spec")
                .runSpecifications()
                .printReport();
    }

}
