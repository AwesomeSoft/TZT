package com.awesomesoft.tzt.service.GoogleMapsApi.exceptions;

/**
 * Created by student on 5/20/14.
 */
public class RouteNotFoundException extends Exception {
    public RouteNotFoundException() {
    }

    public RouteNotFoundException(String message) {
        super(message);
    }

    public RouteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RouteNotFoundException(Throwable cause) {
        super(cause);
    }

    public RouteNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
