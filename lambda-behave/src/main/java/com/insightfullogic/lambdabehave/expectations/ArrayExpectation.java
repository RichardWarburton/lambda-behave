package com.insightfullogic.lambdabehave.expectations;

import org.hamcrest.Matcher;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public final class ArrayExpectation<T> extends BoundExpectation<T[]> {

    ArrayExpectation(T[] array, boolean positive) {
        super(array, positive);
    }

    public ArrayExpectation<T> isArrayWithSize(int size) {
        return matches(arrayWithSize(size));
    }

    public ArrayExpectation<T> isArrayWithSize(Matcher<? super Integer> matcher) {
        return matches(arrayWithSize(matcher));
    }

    public ArrayExpectation<T> isArray(Matcher<? super T> ... elementMatchers) {
        return matches(array(elementMatchers));
    }

    public ArrayExpectation<T> isArrayContaining(T ... items) {
        return matches(arrayContaining(items));
    }

    public ArrayExpectation<T> isArrayContaining(Matcher<? super T> ... elements) {
        return matches(arrayContaining(elements));
    }

    public ArrayExpectation<T> isArrayContaining(List<Matcher<? super T>> elements) {
        return matches(arrayContaining(elements));
    }

    public ArrayExpectation<T> isArrayContainingInAnyOrder(T ... items) {
        return matches(arrayContainingInAnyOrder(items));
    }

    public ArrayExpectation<T> isArrayContainingInAnyOrder(Matcher<? super T> ... elements) {
        return matches(arrayContainingInAnyOrder(elements));
    }

    public ArrayExpectation<T> isArrayContainingInAnyOrder(Collection<Matcher<? super T>> elements) {
        return matches(arrayContainingInAnyOrder(elements));
    }

    public ArrayExpectation<T> isEmptyArray() {
        return matches(emptyArray());
    }

    public ArrayExpectation<T> isArrayWithItem(T item) {
        return matches(hasItemInArray(item));
    }

    public ArrayExpectation<T> isArrayWithItem(Matcher<? super T> itemMatcher) {
        return matches(hasItemInArray(itemMatcher));
    }

    @Override
    public ArrayExpectation<T> isEqualTo(T[] expected) {
        super.isEqualTo(expected);
        return this;
    }

    @Override
    public ArrayExpectation<T> hasToString(Matcher<? super String> str) {
        super.hasToString(str);
        return this;
    }

    @Override
    public ArrayExpectation<T> hasToString(String arg) {
        super.hasToString(arg);
        return this;
    }

    @Override
    public ArrayExpectation<T> instanceOf(Class<?> arg) {
        super.instanceOf(arg);
        return this;
    }

    @Override
    public ArrayExpectation<T> isNotNull() {
        super.isNotNull();
        return this;
    }

    @Override
    public ArrayExpectation<T> toBeNull() {
        super.toBeNull();
        return this;
    }

    @Override
    public ArrayExpectation<T> sameInstance(T[] target) {
        super.sameInstance(target);
        return this;
    }

    @Override
    public ArrayExpectation<T> any() {
        super.any();
        return this;
    }

    @Override
    public ArrayExpectation<T> hasProperty(String propertyName, Matcher<?> propertyValue) {
        super.hasProperty(propertyName, propertyValue);
        return this;
    }

    @Override
    public ArrayExpectation<T> is(T[] value) {
        super.is(value);
        return this;
    }

    @Override
    public ArrayExpectation<T> is(Matcher<? super T[]> matcher) {
        super.is(matcher);
        return this;
    }

    @Override
    public ArrayExpectation<T> isIn(Collection<T[]> values) {
        super.isIn(values);
        return this;
    }

    @Override
    public ArrayExpectation<T> isIn(T[] ... values) {
        super.isIn(values);
        return this;
    }

    @Override
    public ArrayExpectation<T> never() {
        super.never();
        return this;
    }

    private ArrayExpectation<T> matches(Matcher<? super T[]> matcher) {
        assertThat(objectUnderTest, matcher);
        return this;
    }

}
