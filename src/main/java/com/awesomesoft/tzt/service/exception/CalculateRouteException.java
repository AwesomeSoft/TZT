package com.awesomesoft.tzt.service.exception;

/**
 * Created by student on 6/2/14.
 */
public class CalculateRouteException extends Exception{
    public CalculateRouteException() {
    }

    public CalculateRouteException(String message) {
        super(message);
    }

    public CalculateRouteException(String message, Throwable cause) {
        super(message, cause);
    }

    public CalculateRouteException(Throwable cause) {
        super(cause);
    }

    public CalculateRouteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
