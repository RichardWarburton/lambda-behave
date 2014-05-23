package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.example.StackSpec;
import com.insightfullogic.lambdabehave.reporting.Reporter;

/**
 * .
 */
public class ConsoleRunner {

    public ConsoleRunner() {
        // TODO
        Reporter.current.run(StackSpec.class);
        Reporter.current.printReport();
    }
}
