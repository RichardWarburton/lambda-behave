package com.insightfullogic.lambdabehave.impl;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

import java.util.function.Function;

/**
 * .
 */
public class HasTypesafeProperty<T, P> extends BaseMatcher<T> {

    private final Function<T, P> getter;
    private final Matcher<? super P> propertyMatcher;

    public HasTypesafeProperty(final Function<T, P> getter, final Matcher<? super P> propertyMatcher) {
        this.getter = getter;
        this.propertyMatcher = propertyMatcher;
    }

    @Override
    public boolean matches(final Object item) {
        P propertyValue = getProperty(item);
        return propertyMatcher.matches(propertyValue);
    }

    @Override
    public void describeTo(final Description description) {
        description.appendText("hasProperty(")
                   .appendDescriptionOf(propertyMatcher).appendText(")");
    }

    @SuppressWarnings("unchecked")
    private P getProperty(Object item) {
        return getter.apply((T) item);
    }

}
