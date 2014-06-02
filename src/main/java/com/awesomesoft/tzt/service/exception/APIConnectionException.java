package com.awesomesoft.tzt.service.exception;

/**
 * Created by student on 6/2/14.
 */
public class APIConnectionException extends Exception{
    public APIConnectionException() {
    }

    public APIConnectionException(String message) {
        super(message);
    }

    public APIConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public APIConnectionException(Throwable cause) {
        super(cause);
    }

    public APIConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
