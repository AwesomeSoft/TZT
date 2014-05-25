package com.awesomesoft.tzt.service.impl;

/**
 * Created by student on 5/24/14.
 */
public class JPAException extends Exception {
    public JPAException() {
    }

    public JPAException(String message) {
        super(message);
    }

    public JPAException(String message, Throwable cause) {
        super(message, cause);
    }

    public JPAException(Throwable cause) {
        super(cause);
    }

    public JPAException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
