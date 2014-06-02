package com.awesomesoft.tzt.service.exception;

/**
 * Created by student on 6/1/14.
 */
public class LocationUknownException extends Exception {
    public LocationUknownException(String s) {
    }

    public LocationUknownException(String message, Throwable cause) {
        super(message, cause);
    }

    public LocationUknownException(Throwable cause) {
        super(cause);
    }

    public LocationUknownException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public LocationUknownException() {
    }
}
