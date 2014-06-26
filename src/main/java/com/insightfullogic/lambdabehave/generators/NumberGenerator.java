package com.insightfullogic.lambdabehave.generators;

@FunctionalInterface
public interface NumberGenerator {

    int generateInt(int maxValue);

    default boolean generateBoolean() {
        return generateInt(2) == 0;
    }

}
