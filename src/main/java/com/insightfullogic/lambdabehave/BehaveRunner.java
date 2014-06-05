package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.impl.output.ConsoleFormatter;
import com.insightfullogic.lambdabehave.impl.output.ReportFormatter;
import com.insightfullogic.lambdabehave.impl.reports.ReportFactory;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Entrypoint to run the lambda behave specification library.
 */
public final class BehaveRunner {

    public static void main(String[] args) {
        new BehaveRunner(args)
                .runSpecifications()
                .printReport();
    }

    private final List<Class<?>> specifications;

    public BehaveRunner(String ... specifications) {
        this(Stream.of(specifications)
                   .map(name -> {
                       try {
                           return Class.forName(name);
                       } catch (ClassNotFoundException e) {
                           throw new IllegalArgumentException(e);
                       }
                   })
                   .collect(toList()));
    }

    public BehaveRunner(List<Class<?>> specifications) {
        this.specifications = specifications;
        ReportFactory.init();
    }

    public BehaveRunner runSpecifications() {
        specifications.forEach(this::run);
        return this;
    }

    public BehaveRunner run(Class<?> specification) {
        try {
            specification.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return this;
    }

    public BehaveRunner printReport() {
        ReportFormatter formatter = new ConsoleFormatter();
        formatter.format(ReportFactory.getReport());
        return this;
    }

}
