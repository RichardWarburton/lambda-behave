package com.insightfullogic.lambdabehave.impl;

import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.specifications.ColumnDataSpecification;
import com.insightfullogic.lambdabehave.specifications.Specification;
import com.insightfullogic.lambdabehave.specifications.ThreeColumnDataSpecification;
import com.insightfullogic.lambdabehave.specifications.TwoColumnDataSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * A Specifier defines how .
 */
public class Specifier {

    private final String suiteName;

    private final List<Runnable> prefixes;
    private final List<Behaviour> behaviours;
    private final List<Runnable> postfixes;

    public Specifier(String suite) {
        this.suiteName = suite;

        prefixes = new ArrayList<>();
        behaviours = new ArrayList<>();
        postfixes = new ArrayList<>();
    }

    public void specifyBehaviour(String description, Specification specification) {
        behaviours.add(new Behaviour(description, specification));
    }

    public <T> void specifyBehaviour(String description, T value, ColumnDataSpecification<T> specification) {
        specifyBehaviour(description, expect -> specification.specifyBehaviour(expect, value));
    }

    public <F, S> void specifyBehaviour(String description, F first, S second, TwoColumnDataSpecification<F, S> specification) {
        specifyBehaviour(description, expect -> specification.specifyBehaviour(expect, first, second));
    }

    public <F, S, T> void specifyBehaviour(String description, F first, S second, T third, ThreeColumnDataSpecification<F, S, T> specification) {
        specifyBehaviour(description, expect -> specification.specifyBehaviour(expect, first, second, third));
    }

    public void addPrefix(Runnable block) {
        prefixes.add(block);
    }

    public void addPostfix(Runnable block) {
        postfixes.add(block);
    }

    public void checkSpecifications(Report report) {
        report.onSuiteName(suiteName);
        completeBehaviours().forEach(behaviour -> report.recordSpecification(suiteName, behaviour.checkCompleteBehaviour()));
    }

    public Stream<CompleteBehaviour> completeBehaviours() {
        return behaviours.stream()
                         .map(behaviour -> new CompleteBehaviour(prefixes, behaviour, postfixes, suiteName));
    }

    public String getSuiteName() {
        return suiteName;
    }

}
