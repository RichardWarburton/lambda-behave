package com.insightfullogic.lambdabehave.impl;

import com.insightfullogic.lambdabehave.Block;
import com.insightfullogic.lambdabehave.Description;
import com.insightfullogic.lambdabehave.generators.GeneratedDescription;
import com.insightfullogic.lambdabehave.generators.SourceGenerator;
import com.insightfullogic.lambdabehave.impl.generators.GeneratedDescriptionSpecBuilder;
import com.insightfullogic.lambdabehave.impl.specifications.*;
import com.insightfullogic.lambdabehave.specifications.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * .
 */
public class DescriptionRecorder implements Description {

    private final SourceGenerator sourceGenerator;
    private final String description;
    private final Blocks prefixes;
    private final Blocks postfixes;

    private Specification specification;

    private ColumnDataSpecification<?> columnDataSpecification;
    private TwoColumnDataSpecification<?, ?> twoColumnDataSpecification;
    private ThreeColumnDataSpecification<?, ?, ?> threeColumnDataSpecification;

    public DescriptionRecorder(SourceGenerator sourceGenerator, String suiteName, String description) {
        this.sourceGenerator = sourceGenerator;
        this.description = description;
        prefixes = new Blocks(suiteName);
        postfixes = new Blocks(suiteName);
    }

    @Override
    public void should(String description, Specification specification) {
        if(description.equals(this.description)) {
            this.specification = specification;
        }
    }

    @Override
    public <T> Column<T> uses(final T value) {
        return new ValueSpecRecorder<>(this);
    }

    @Override
    public <T> Column<T> uses(final List<T> values) {
        return new ValueSpecRecorder<>(this);
    }

    @Override
    public <T> Column<T> uses(final Stream<T> values) {
        return uses(values.collect(toList()));
    }

    @Override
    public <F, S> TwoColumns<F, S> uses(final F first, final S second) {
        return new PairSpecRecorder<>(this);
    }

    @Override
    public <F, S> TwoColumns<F, S> uses(final List<F> first, final List<S> second) {
        return new PairSpecRecorder<>(this);
    }

    @Override
    public <F, S> TwoColumns<F, S> uses(final Stream<F> first, final Stream<S> second) {
        return new PairSpecRecorder<>(this);
    }

    @Override
    public <F, S, T> ThreeColumns<F, S, T> uses(final F first, final S second, final T third) {
        return new TripletSpecRecorder<>(this);
    }

    @Override
    public <F, S, T> ThreeColumns<F, S, T> uses(final List<F> first, final List<S> second, final List<T> third) {
        return new TripletSpecRecorder<>(this);
    }

    @Override
    public <F, S, T> ThreeColumns<F, S, T> uses(final Stream<F> first, final Stream<S> second, final Stream<T> third) {
        return new TripletSpecRecorder<>(this);
    }

    @Override
    public GeneratedDescription requires(final int exampleCount) {
        return new GeneratedDescriptionSpecBuilder(sourceGenerator, exampleCount, this);
    }

    @Override
    public long getSeed() {
        return sourceGenerator.getSeed();
    }

    @Override
    public void isSetupWith(Block block) {
        prefixes.add(block);
    }

    @Override
    public void isConcludedWith(Block block) {
        postfixes.add(block);
    }

    public Blocks getPrefixes() {
        return prefixes;
    }

    public Specification getSpecification() {
        return specification;
    }

    public ColumnDataSpecification<?> getColumnDataSpecification() {
        return columnDataSpecification;
    }

    public TwoColumnDataSpecification<?, ?> getTwoColumnDataSpecification() {
        return twoColumnDataSpecification;
    }

    public ThreeColumnDataSpecification<?, ?, ?> getThreeColumnDataSpecification() {
        return threeColumnDataSpecification;
    }

    public Blocks getPostfixes() {
        return postfixes;
    }

    public void toShow(String description, ColumnDataSpecification<?> specification) {
        if(description.equals(this.description)) {
            columnDataSpecification = specification;
        }
    }

    public void toShow(String description, TwoColumnDataSpecification<?, ?> specification) {
        if(description.equals(this.description)) {
            twoColumnDataSpecification = specification;
        }
    }

    public void toShow(String description, ThreeColumnDataSpecification<?, ?, ?> specification) {
        if(description.equals(this.description)) {
            threeColumnDataSpecification = specification;
        }
    }

    // ---------------- Unimplemented Methods ----------------

    @Override
    public void initializesWith(Block block) {

    }

    @Override
    public void completesWith(Block block) {

    }
}
