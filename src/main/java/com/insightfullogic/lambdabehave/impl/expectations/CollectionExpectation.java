package com.insightfullogic.lambdabehave.impl.expectations;

import java.util.Collection;

public class CollectionExpectation extends BoundExpectation {

    private final Collection<?> objectUnderTest;

    public CollectionExpectation(Collection<?> objectUnderTest) {
        super(objectUnderTest);
        this.objectUnderTest = objectUnderTest;
    }

    public void isEmpty() {
        // TODO
        // objectUnderTest.isEmpty());
    }

}
