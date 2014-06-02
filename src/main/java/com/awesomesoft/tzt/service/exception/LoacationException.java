package com.awesomesoft.tzt.service.exception;

/**
 * Created by student on 6/2/14.
 */
public class LoacationException  extends Exception{
    public LoacationException() {
    }

    public LoacationException(String message) {
        super(message);
    }

    public LoacationException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoacationException(Throwable cause) {
        super(cause);
    }

    public LoacationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
