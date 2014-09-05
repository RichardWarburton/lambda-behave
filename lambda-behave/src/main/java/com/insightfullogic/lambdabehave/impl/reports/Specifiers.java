package com.insightfullogic.lambdabehave.impl.reports;

import com.insightfullogic.lambdabehave.SpecificationDeclarationException;
import com.insightfullogic.lambdabehave.Suite;
import com.insightfullogic.lambdabehave.generators.SourceGenerator;
import com.insightfullogic.lambdabehave.impl.DescriptionRecorder;
import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.impl.generators.NumberGenerators;

import java.util.*;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * Lambda behave uses some global state to setup all the specifications. This is all
 * maintained within this class.
 */
public final class Specifiers {

    private static final Deque<Specifier> specifiers = new ArrayDeque<>();

    private static DescriptionRecorder descriptionRecorder;
    private static Class<?> classBeingSpecified;

    public static int startSpecifying(Class<?> classBeingSpecified) {
        Specifiers.classBeingSpecified = classBeingSpecified;
        return specifiers.size();
    }

    public static void push(final Specifier specifier) {
        specifiers.push(specifier);
    }

    public static List<Specifier> finishSpecifying(int mark) {
        classBeingSpecified = null;
        return IntStream.range(mark, specifiers.size())
                        .mapToObj(i -> specifiers.pop())
                        .collect(toList());
    }

    public static void describe(String name, Suite behaviours) {
        if (isCurrentlyPlaying()) {
            behaviours.specifySuite(descriptionRecorder);
            return;
        }

        SourceGenerator generator = NumberGenerators.peek();
        Specifier specifier = new Specifier(name, generator);
        behaviours.specifySuite(specifier);
        Specifiers.push(specifier);
    }

    private static boolean isCurrentlyPlaying() {
        return descriptionRecorder != null;
    }

    public static DescriptionRecorder recordCode(SourceGenerator sourceGenerator, String suiteName, String description) {
        descriptionRecorder = new DescriptionRecorder(sourceGenerator, suiteName, description);
        try {
            classBeingSpecified.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            String message = "Unable to create specification from: " + suiteName;
            throw new SpecificationDeclarationException(message, e);
        }
        try {
            return descriptionRecorder;
        } finally {
            descriptionRecorder = null;
        }
    }

}
