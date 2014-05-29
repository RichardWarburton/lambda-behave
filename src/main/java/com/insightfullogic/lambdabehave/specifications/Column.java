package com.insightfullogic.lambdabehave.specifications;

/**
 * .
 */
public final class Column<T> {

    public Column<T> where(T value) {
        hasElement(value);
        return new OneRow<>(this);
    }

    void hasElement(T value) {
        specifier.specifyBehaviour(description, value, specification);
    }

}
