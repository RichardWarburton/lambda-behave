package com.insightfullogic.lambdabehave.expectations;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.util.Collection;
import java.util.List;

import static org.hamcrest.Matchers.empty;
import static org.junit.Assert.assertThat;

public final class CollectionExpectation<T> extends BoundExpectation<Collection<T>> {

    CollectionExpectation(Collection<T> objectUnderTest, boolean positive) {
        super(objectUnderTest, positive);
    }

    public CollectionExpectation<T> isEmpty() {
        return matches(empty());
    }

    public CollectionExpectation<T> hasItem(T item) {
        return matches(Matchers.hasItem(item));
    }

    public CollectionExpectation<T> hasItem(Matcher<? super T> item) {
        return matches(Matchers.hasItem(item));
    }

    public CollectionExpectation<T> hasItems(T ... items) {
        return matches(Matchers.hasItems(items));
    }

    public CollectionExpectation<T> contains(T ... items) {
        return matches(Matchers.contains(items));
    }

    public CollectionExpectation<T> contains(Matcher<? super  T> ... items) {
        return matches(Matchers.contains(items));
    }

    public CollectionExpectation<T> contains(List<Matcher<? super  T>> items) {
        return matches(Matchers.contains(items));
    }

    public CollectionExpectation<T> containsInAnyOrder(T ... items) {
        return matches(Matchers.containsInAnyOrder(items));
    }

    public CollectionExpectation<T> containsInAnyOrder(Matcher<? super  T> ... items) {
        return matches(Matchers.containsInAnyOrder(items));
    }

    public CollectionExpectation<T> containsInAnyOrder(Collection<Matcher<? super  T>> items) {
        return matches(Matchers.containsInAnyOrder(items));
    }

    public CollectionExpectation<T> emptyCollectionOf(Class<T> type) {
        return matches(Matchers.emptyCollectionOf(type));
    }

    public CollectionExpectation<T> hasSize(int size) {
        return matches(Matchers.hasSize(size));
    }

    public CollectionExpectation<T> hasSize(Matcher<? super Integer> size) {
        return matches(Matchers.hasSize(size));
    }

    public CollectionExpectation<T> hasItems(Matcher<? super T> ... items) {
        return matches(Matchers.hasItems(items));
    }

    private CollectionExpectation<T> matches(Matcher<? super Collection<T>> matcher) {
        assertThat(objectUnderTest, negatedIfNeeded(matcher));
        return this;
    }

}
