package com.insightfullogic.lambdabehave;

import com.insightfullogic.lambdabehave.impl.output.ConsoleFormatter;
import com.insightfullogic.lambdabehave.impl.reports.ReportFactory;
import com.insightfullogic.lambdabehave.impl.output.ReportFormatter;

import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * .
 */
public class BehaveRunner {

    public static void main(String[] args) {
        BehaveRunner behaveRunner = new BehaveRunner(
            Stream.of(args)
                  .map(name -> {
                      try {
                          return Class.forName(name);
                      } catch (ClassNotFoundException e) {
                          throw new IllegalArgumentException(e);
                      }
                  })
                  .collect(toList()));
        behaveRunner.runSpecifications();
        behaveRunner.printReport();
    }

    private final List<Class<?>> specifications;

    public BehaveRunner(List<Class<?>> specifications) {
        this.specifications = specifications;
        ReportFactory.init();
    }

    public void runSpecifications() {
        specifications.forEach(this::run);
    }

    public void run(Class<?> specification) {
        try {
            specification.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void printReport() {
        ReportFormatter formatter = new ConsoleFormatter();
        formatter.format(ReportFactory.getReport());
    }

}
