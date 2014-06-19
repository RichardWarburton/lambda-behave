package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.testcases.running.EmptyExample;
import org.junit.runner.RunWith;

import static com.insightfullogic.lambdabehave.BehaveRunner.runOnly;
import static com.insightfullogic.lambdabehave.Suite.describe;

@RunWith(JunitSuiteRunner.class)
public class FixtureSpec {{

    describe("lambda behave fixtures", it -> {

        it.should("execute beforeEach() before a test", expect -> {
            runOnly(EmptyExample.class);
        });

    });

}}
