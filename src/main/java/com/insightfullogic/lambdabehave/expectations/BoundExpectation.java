package com.insightfullogic.lambdabehave.expectations;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasToString;
import static org.junit.Assert.assertThat;

public class BoundExpectation<T> {

    private final T objectUnderTest;

    BoundExpectation(T value) {
        this.objectUnderTest = value;
    }

    public void isEqualTo(T expected) {
        is(equalTo(expected));
    }

    public void hasToString(Matcher<? super String> str) {
        expect(Matchers.hasToString(str));
    }

    public void hasToString(String str) {
        expect(Matchers.hasToString(str));
    }

    public void instanceOf(Class<?> type) {
        expect(Matchers.instanceOf(type));
    }

    public void notNullValue() {
        expect(Matchers.notNullValue(getWrappedClass()));
    }

    public void nullValue() {
        expect(Matchers.nullValue(getWrappedClass()));
    }

    public void sameInstance(T target) {
        expect(Matchers.sameInstance(target));
    }

    public void hasProperty(String propertyName, Matcher<?> propertyValue) {
        expect(Matchers.hasProperty(propertyName, propertyValue));
    }

    public void is(Matcher<T> matcher) {
        expect(Matchers.is(matcher));
    }

    protected void expect(Matcher<T> matcher) {
        assertThat(objectUnderTest, matcher);
    }

    protected Class<T> getWrappedClass() {
        return (Class<T>)objectUnderTest.getClass();
    }

}
