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
public class Runner {

    public static void main(String[] args) {
        Runner runner = new Runner(
            Stream.of(args)
                  .map(name -> {
                      try {
                          return Class.forName(name);
                      } catch (ClassNotFoundException e) {
                          throw new IllegalArgumentException(e);
                      }
                  })
                  .collect(toList()));
        runner.runSpecifications();
        runner.printReport();
    }

    private final List<Class<?>> specifications;

    public Runner(List<Class<?>> specifications) {
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
