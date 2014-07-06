package com.insightfullogic.lambdabehave;

/**
 * A runnable which can throw an Exception.
 */
@FunctionalInterface
public interface Block {

    /**
     * The method invoked to run the block of code.
     *
     * @throws Exception an exception that will cause tests to error
     */
    public void run() throws Exception;

}
