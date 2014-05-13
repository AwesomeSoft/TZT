package com.awesomesoft.tzt.service.exception;

/**
 * Created by student on 5/13/14.
 */
public class GoogleMapsApiException extends Exception {
    public GoogleMapsApiException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public GoogleMapsApiException() {
    }

    public GoogleMapsApiException(String message) {
        super(message);
    }

    public GoogleMapsApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public GoogleMapsApiException(Throwable cause) {
        super(cause);
    }
}
