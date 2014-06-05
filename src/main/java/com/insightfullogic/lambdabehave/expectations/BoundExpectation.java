package com.insightfullogic.lambdabehave.expectations;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.util.Collection;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class BoundExpectation<T> {

    protected final T objectUnderTest;

    BoundExpectation(T value) {
        this.objectUnderTest = value;
    }

    public <T> BoundExpectation<T> and(T value) {
        return new BoundExpectation<T>(value);
    }

    public CollectionExpectation and(Collection<?> collection) {
        return new CollectionExpectation(collection);
    }

    public StringExpectation and(String str) {
        return new StringExpectation(str);
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

    public BoundExpectation<T> toNotBeNull() {
        return matches(Matchers.notNullValue(getWrappedClass()));
    }

    public BoundExpectation<T> toBeNull() {
        return matches(Matchers.nullValue(getWrappedClass()));
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

    public BoundExpectation<T> is(Matcher<T> matcher) {
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

    public BoundExpectation<T> isNot(T value) {
        return matches(Matchers.is(Matchers.not(value)));
    }

    public BoundExpectation<T> isNot(Matcher<T> value) {
        return matches(Matchers.is(Matchers.not(value)));
    }

    private BoundExpectation<T> matches(Matcher<T> matcher) {
        assertThat(objectUnderTest, matcher);
        return this;
    }

    protected Class<T> getWrappedClass() {
        return (Class<T>)objectUnderTest.getClass();
    }

}
