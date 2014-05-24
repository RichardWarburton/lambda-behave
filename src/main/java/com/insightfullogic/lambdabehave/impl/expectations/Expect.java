package com.insightfullogic.lambdabehave.impl.expectations;

import java.util.Collection;

public final class Expect {

    public BoundExpectation that(Object value) {
        return new BoundExpectation(value);
    }

    public CollectionExpectation that(Collection<?> collection) {
        return new CollectionExpectation(collection);
    }

}
