package com.insightfullogic.lambdabehave.impl.generators;

import com.insightfullogic.lambdabehave.generators.Generator;
import com.insightfullogic.lambdabehave.generators.SourceGenerator;

import java.util.stream.IntStream;

public class StringGenerator implements Generator<String> {

    private static final int MAX_STRING_LENGTH = 10;

    private final int minValue;
    private final int range;

    public StringGenerator(final int minCharacter, final int maxCharacter) {
        this.minValue = minCharacter;
        range = maxCharacter - minCharacter;
    }

    @Override
    public String generate(final SourceGenerator source) {
        final int length = source.generateInt(MAX_STRING_LENGTH);

        int[] characters = new int[length];
        for (int i = 0; i < length; i++) {
            characters[i] = nextCodePoint(source);
        }

        return new String(characters, 0, length);
    }
    
    private int nextCodePoint(final SourceGenerator source) {
        return IntStream.range(0, MAX_TRIES)
                        .map(i -> minValue + source.generateInt(range))
                        .filter(Character::isDefined)
                        .findFirst()
                        .orElseThrow(Generators::exceededMaxTries);
    }

}
