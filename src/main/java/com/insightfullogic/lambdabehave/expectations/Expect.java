package com.insightfullogic.lambdabehave.expectations;

import junit.framework.Assert;

import java.util.Collection;

public final class Expect {

    public <T> BoundExpectation<T> that(T value) {
        return new BoundExpectation<T>(value);
    }

    public <T> CollectionExpectation<T> that(Collection<T> collection) {
        return new CollectionExpectation<>(collection);
    }

    public StringExpectation that(String str) {
        return new StringExpectation(str);
    }

    public void toThrow(Class<? extends Throwable> exceptionClass) {

    }

    public void failure(final String message) {
        Assert.fail(message);
    }

}
