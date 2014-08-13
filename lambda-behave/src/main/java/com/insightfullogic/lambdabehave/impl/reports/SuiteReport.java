package com.insightfullogic.lambdabehave.impl.reports;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public final class SuiteReport {

    private final String name;
    private final List<SpecificationReport> specifications;

    public SuiteReport(final String name) {
        Objects.requireNonNull(name);
        this.name = name;
        specifications = new ArrayList<>();
    }

    public SuiteReport with(final SpecificationReport specification) {
        specifications.add(specification);
        return this;
    }

    public Stream<SpecificationReport> specifications() {
        return specifications.stream();
    }

    public List<SpecificationReport> getSpecifications() {
        return specifications;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SuiteReport that = (SuiteReport) o;

        if (!name.equals(that.name)) return false;
        if (!specifications.equals(that.specifications)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + specifications.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "SuiteReport{" +
                "name='" + name + '\'' +
                ", specifications=" + specifications +
                '}';
    }
}
