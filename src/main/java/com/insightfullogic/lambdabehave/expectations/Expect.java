package com.insightfullogic.lambdabehave.expectations;

import java.util.Collection;

// BEGIN Expect
public final class Expect {

    public BoundExpectation that(Object value) {
        return new BoundExpectation(value);
    }

    // Rest of class omitted
// END Expect

    public CollectionExpectation that(Collection<?> collection) {
        return new CollectionExpectation(collection);
    }

}
