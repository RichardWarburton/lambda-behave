package com.insightfullogic.lambdabehave.impl.reports;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public final class SpecificationReport {

    private static final Logger log = LoggerFactory.getLogger(SpecificationReport.class);

    private final String description;
    private final Result result;
    private final String message;
    private final Throwable cause;

    public static SpecificationReport success(final String description) {
        // TODO: remove this logging - causes coupling
        log.info(description + " has succeeded");
        return new SpecificationReport(description, Result.SUCCESS, null, null);
    }

    public static SpecificationReport failure(final String description, final AssertionError cause) {
        log.warn(description + " has failed");
        return new SpecificationReport(description, Result.FAILURE, cause.getMessage(), cause);
    }

    public static SpecificationReport error(final String specification, final Throwable cause) {
        log.warn(specification + " has finished in error");
        return new SpecificationReport(specification, Result.ERROR, cause.getMessage(), cause);
    }

    public SpecificationReport(final String description, final Result result, final String message, final Throwable cause) {
        Objects.requireNonNull(description);
        Objects.requireNonNull(result);

        this.description = description;
        this.result = result;
        this.message = message;
        this.cause = cause;
    }

    public String getDescription() {
        return description;
    }

    public Result getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

    public Throwable getCause() {
        return cause;
    }

    public boolean isSuccess() {
        return result == Result.SUCCESS;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpecificationReport that = (SpecificationReport) o;

        if (!description.equals(that.description)) return false;
        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (result != that.result) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result1 = description.hashCode();
        result1 = 31 * result1 + result.hashCode();
        result1 = 31 * result1 + (message != null ? message.hashCode() : 0);
        return result1;
    }

    @Override
    public String toString() {
        return "SpecificationReport{" +
                "description='" + description + '\'' +
                ", result=" + result +
                ", message='" + message + '\'' +
                '}';
    }
}
