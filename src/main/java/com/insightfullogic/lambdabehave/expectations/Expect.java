package com.insightfullogic.lambdabehave.expectations;

import java.util.Collection;

public final class Expect {

    public <T> BoundExpectation<T> that(T value) {
        return new BoundExpectation<T>(value);
    }

    public CollectionExpectation that(Collection<?> collection) {
        return new CollectionExpectation(collection);
    }

    public StringExpectation that(String str) {
        return new StringExpectation(str);
    }

}
