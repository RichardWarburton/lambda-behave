package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.impl.output.ConsoleFormatter;
import com.insightfullogic.lambdabehave.impl.output.ReportFormatter;
import com.insightfullogic.lambdabehave.impl.reports.Report;
import com.insightfullogic.lambdabehave.impl.reports.ReportStore;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
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
            return tryToLoadPackage(name);
        }
    }

    private static Stream<Class<?>> tryToLoadPackage(String name) {
        int splitPoint = name.indexOf('*');
        if (splitPoint == -1) {
            throw new IllegalArgumentException("Invalid specification specifier: " + name);
        }

        String packageName = name.substring(0, splitPoint);
        String classSuffix = name.substring(splitPoint + 1, name.length());

        Reflections reflections = makeReflections(packageName);
        return reflections.getSubTypesOf(Object.class)
                          .stream()
                          .filter(cls -> cls.getSimpleName().endsWith(classSuffix));
    }

    private static Reflections makeReflections(String packageName) {
        // This BS is required to get subclasses of Object
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        return new Reflections(new ConfigurationBuilder()
                    .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
                    .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                    .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(packageName))));
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
        ReportStore.pushReport(report);
        try {
            specification.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
            Report poppedReport = ReportStore.popReport();
            if (poppedReport != report) {
                throw new IllegalStateException("Different report instances popped, error running nested" + getClass().getSimpleName());
            }
        }
        return this;
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