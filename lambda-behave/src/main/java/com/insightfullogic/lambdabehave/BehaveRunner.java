package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.impl.Specifier;
import com.insightfullogic.lambdabehave.impl.output.ConsoleFormatter;
import com.insightfullogic.lambdabehave.impl.output.ReportFormatter;
import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.impl.reports.Specifiers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * <p>
 * BehaveRunner is the entry point class for running the lambda behave
 * specification library. You can run specifications from your own
 * code, for example:
 * </p>
 *
 * <code>
 *  new BehaveRunner(specClass).runAll().getReport();
 * </code>
 *
 * <p>
 *     If you want to run lambda behave specifications via junit then
 *     you should use {@link com.insightfullogic.lambdabehave.JunitSuiteRunner}.
 * </p>
 *
 * @see com.insightfullogic.lambdabehave.JunitSuiteRunner
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
            throw new SpecificationDeclarationException("Unable to create suite from: " + name, e);
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
        } catch (InstantiationException | IllegalAccessException e) {
            String message = "Unable to create specification from: " + specification.getSimpleName();
            throw new SpecificationDeclarationException(message, e);
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
