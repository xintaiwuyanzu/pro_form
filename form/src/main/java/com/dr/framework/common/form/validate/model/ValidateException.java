package com.dr.framework.common.form.validate.model;

public class ValidateException extends Exception {

    private ValidateResults results;

    public ValidateException(ValidateResults results) {
        this.results = results;
    }

    public ValidateException(String message, ValidateResults results) {
        super(message);
        this.results = results;
    }

    public ValidateException(String message, Throwable cause, ValidateResults results) {
        super(message, cause);
        this.results = results;
    }

    public ValidateException(Throwable cause, ValidateResults results) {
        super(cause);
        this.results = results;
    }

    public ValidateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, ValidateResults results) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.results = results;
    }
}
