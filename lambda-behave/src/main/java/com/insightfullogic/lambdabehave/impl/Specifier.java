package com.insightfullogic.lambdabehave.impl;

import com.insightfullogic.lambdabehave.Description;
import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.impl.specifications.PairBuilder;
import com.insightfullogic.lambdabehave.impl.specifications.TripletBuilder;
import com.insightfullogic.lambdabehave.impl.specifications.ValueBuilder;
import com.insightfullogic.lambdabehave.specifications.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * A Specifier defines how .
 */
public class Specifier implements Description {

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

    public <T> void specifyBehaviour(String description, T value, ColumnDataSpecification<T> specification) {
        should(description, expect -> specification.specifyBehaviour(expect, value));
    }

    public <F, S> void specifyBehaviour(String description, F first, S second, TwoColumnDataSpecification<F, S> specification) {
        should(description, expect -> specification.specifyBehaviour(expect, first, second));
    }

    public <F, S, T> void specifyBehaviour(String description, F first, S second, T third, ThreeColumnDataSpecification<F, S, T> specification) {
        should(description, expect -> specification.specifyBehaviour(expect, first, second, third));
    }

    @Override
    public void should(String description, Specification specification) {
        behaviours.add(new Behaviour(description, specification));
    }

    @Override
    public <T> Column<T> uses(T value) {
        return new ValueBuilder<>(value, this);
    }

    @Override
    public <F, S> TwoColumns<F, S> uses(F first, S second) {
        return new PairBuilder<>(first, second, this);
    }

    @Override
    public <F, S, T> ThreeColumns<F, S, T> uses(F first, S second, T third) {
        return new TripletBuilder<>(first, second, third, this);
    }

    @Override
    public void shouldSetup(Runnable block) {
        prefixes.add(block);
    }

    @Override
    public void shouldInitialize(Runnable block) {
        // TODO
    }

    public void shouldTearDown(Runnable block) {
        postfixes.add(block);
    }

    @Override
    public void shouldComplete(Runnable block) {
        // TODO
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
