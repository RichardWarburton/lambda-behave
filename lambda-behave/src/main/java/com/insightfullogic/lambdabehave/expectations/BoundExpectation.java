package com.insightfullogic.lambdabehave.expectations;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.util.Collection;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BoundExpectation<T> {

    protected final boolean positive;
    protected final T objectUnderTest;

    BoundExpectation(T value, boolean positive) {
        this.objectUnderTest = value;
        this.positive = positive;
    }

    public <T> BoundExpectation<T> and(T value) {
        return new BoundExpectation<T>(value, true);
    }

    public CollectionExpectation and(Collection<?> collection) {
        return new CollectionExpectation<>(collection, true);
    }

    public StringExpectation and(String str) {
        return new StringExpectation(str, true);
    }

    public BoundExpectation<T> isEqualTo(T expected) {
        return matches(equalTo(expected));
    }

    public BoundExpectation<T> hasToString(Matcher<? super String> str) {
        return matches(Matchers.hasToString(str));
    }

    public BoundExpectation<T> hasToString(String str) {
        return matches(Matchers.hasToString(str));
    }

    public BoundExpectation<T> instanceOf(Class<?> type) {
        return matches(Matchers.instanceOf(type));
    }

    public BoundExpectation<T> isNotNull() {
        return matches(Matchers.<T>notNullValue());
    }

    public BoundExpectation<T> toBeNull() {
        return matches(Matchers.nullValue());
    }

    public BoundExpectation<T> sameInstance(T target) {
        return matches(Matchers.sameInstance(target));
    }

    public BoundExpectation<T> any() {
        return matches(Matchers.any(getWrappedClass()));
    }

    public BoundExpectation<T> hasProperty(String propertyName, Matcher<?> propertyValue) {
        return matches(Matchers.hasProperty(propertyName, propertyValue));
    }

    public BoundExpectation<T> is(Matcher<? super T> matcher) {
        return matches(matcher);
    }

    public BoundExpectation<T> is(T value) {
        return matches(Matchers.is(value));
    }

    public BoundExpectation<T> isIn(Collection<T> values) {
        return matches(Matchers.isIn(values));
    }

    public BoundExpectation<T> isIn(T ... values) {
        return matches(Matchers.isIn(values));
    }

    public BoundExpectation<T> never() {
        return new BoundExpectation<>(objectUnderTest, !positive);
    }

    private BoundExpectation<T> matches(Matcher<? super T> matcher) {
        assertThat(objectUnderTest, negatedIfNeeded(matcher));
        return this;
    }

    protected Matcher<? super T> negatedIfNeeded(Matcher<? super T> matcher) {
        return positive ? matcher : Matchers.not(matcher);
    }

    protected Class<T> getWrappedClass() {
        return (Class<T>)objectUnderTest.getClass();
    }

}
