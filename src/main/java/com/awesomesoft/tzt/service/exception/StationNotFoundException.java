package com.awesomesoft.tzt.service.exception;

/**
 * Created by student on 5/31/14.
 */
public class StationNotFoundException extends Exception {
    public StationNotFoundException(String s) {

    }

    public StationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StationNotFoundException(Throwable cause) {
        super(cause);
    }

    public StationNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public StationNotFoundException() {
    }
}
