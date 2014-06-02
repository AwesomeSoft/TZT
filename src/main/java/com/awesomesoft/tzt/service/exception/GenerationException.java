package com.awesomesoft.tzt.service.exception;

/**
 * Created by student on 5/13/14.
 */
public class GenerationException extends Exception {
    public GenerationException() {
    }

    public GenerationException(String s) {

    }

    public GenerationException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenerationException(Throwable cause) {
        super(cause);
    }

    public GenerationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
