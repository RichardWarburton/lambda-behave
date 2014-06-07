package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.impl.output.ConsoleFormatter;
import com.insightfullogic.lambdabehave.impl.output.ReportFormatter;
import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.impl.reports.Specifiers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Entrypoint to run the lambda behave specification library.
 */
public final class BehaveRunner {

    public static void main(String[] args) {
        new BehaveRunner(args)
                .runAll()
                .printReport();
    }

    public static Report runOnly(Class<?> specClass) {
        return new BehaveRunner().run(specClass).getReport();
    }

    static Specifier declareOnly(Class<?> specClass) {
        return new BehaveRunner().declare(specClass);
    }

    public BehaveRunner() {
        this(Collections.emptyList());
    }

    public BehaveRunner(String ... specifications) {
        this(Stream.of(specifications)
                   .flatMap(BehaveRunner::loadClassOrPackage)
                   .collect(toList()));
    }

    private static Stream<Class<?>> loadClassOrPackage(String name) {
        try {
            return Stream.of(Class.forName(name));
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private final List<Class<?>> specifications;
    private final Report report = new Report();

    public BehaveRunner(Class<?> ... specifications) {
        this(Arrays.asList(specifications));
    }

    public BehaveRunner(List<Class<?>> specifications) {
        this.specifications = specifications;
    }

    public BehaveRunner runAll() {
        specifications.forEach(this::run);
        return this;
    }

    private BehaveRunner run(Class<?> specification) {
        declare(specification).checkSpecifications(report);
        return this;
    }

    private Specifier declare(Class<?> specification) {
        try {
            specification.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return Specifiers.pop();
    }

    public BehaveRunner printReport() {
        ReportFormatter formatter = new ConsoleFormatter();
        formatter.format(report);
        return this;
    }

    public Report getReport() {
        return report;
    }

}