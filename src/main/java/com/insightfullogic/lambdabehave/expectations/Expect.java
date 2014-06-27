package com.insightfullogic.lambdabehave.expectations;

import com.insightfullogic.lambdabehave.Block;
import org.junit.Assert;

import java.util.Collection;

public final class Expect {

    public <T> BoundExpectation<T> that(T value) {
        return new BoundExpectation<T>(value, true);
    }

    public <T> CollectionExpectation<T> that(Collection<T> collection) {
        return new CollectionExpectation<>(collection, true);
    }

    public <T extends Comparable<T>> ComparableExpectation<T> that(T comparable) {
        return new ComparableExpectation<T>(comparable, true);
    }

    public StringExpectation that(String str) {
        return new StringExpectation(str, true);
    }

    public DoubleExpectation that(Double value) {
        return new DoubleExpectation(value, true);
    }

    public <T> ArrayExpectation that(T[] array) {
        return new ArrayExpectation<>(array, true);
    }

    public void exception(Class<? extends Throwable> expectedException, Block block) throws Exception {
        String expectedName = expectedException.getName();
        try {
            block.run();
            failure("Expected exception: " + expectedName + ", but no exception was thrown");
        } catch (Exception e) {
            if (!expectedException.isInstance(e)) {
                String name = e.getClass().getName();
                failure("Expected exception: " + expectedName + ", but " + name + " was thrown");
            }
        }
    }

    // NB: no failure without a message because its a bad idea to not have test failure diagnostics
    public void failure(final String message) {
        Assert.fail(message);
    }

}
