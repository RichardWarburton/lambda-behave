package com.insightfullogic.lambdabehave.impl.reports;

import java.util.Objects;

public final class SpecificationReport {

    private final String description;
    private final Result result;
    private final String message;


    public static SpecificationReport success(String specification) {
        return new SpecificationReport(specification);
    }

    public static SpecificationReport failure(String specification, AssertionError cause) {
        return new SpecificationReport(specification, Result.FAILURE, cause.getMessage());
    }

    public static SpecificationReport error(String specification, Throwable cause) {
        //cause.printStackTrace();
        return new SpecificationReport(specification, Result.ERROR, cause.getMessage());
    }

    public SpecificationReport(String description, Result result, String message) {
        Objects.requireNonNull(description);
        Objects.requireNonNull(result);
        this.description = description;
        this.result = result;
        this.message = message;
    }

    public SpecificationReport(String specification) {
        this(specification, Result.SUCCESS, null);
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

    @Override
    public boolean equals(Object o) {
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
