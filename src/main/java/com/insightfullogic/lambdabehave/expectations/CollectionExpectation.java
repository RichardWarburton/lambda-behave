package com.insightfullogic.lambdabehave.expectations;

import java.util.Collection;

public final class CollectionExpectation extends BoundExpectation<Collection<?>> {

    private final Collection<?> objectUnderTest;

    CollectionExpectation(Collection<?> objectUnderTest) {
        super(objectUnderTest);
        this.objectUnderTest = objectUnderTest;
    }

    public void isEmpty() {

    }

}
