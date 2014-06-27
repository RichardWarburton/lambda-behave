package com.insightfullogic.lambdabehave.generators;

public interface GeneratedDescription {

    GeneratedDescription numberedBy(NumberGenerator numberGenerator);

    <T> GeneratedColumn<T> example(Generator<T> cls);

}
