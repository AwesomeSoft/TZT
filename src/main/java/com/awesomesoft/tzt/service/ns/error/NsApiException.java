package com.awesomesoft.tzt.service.ns.error;

public class NsApiException extends RuntimeException {

    private static final long serialVersionUID = -4535400016864458414L;

    public NsApiException(String message) {
        super(message);
    }


    public NsApiException(String message, Throwable cause) {
        super(message, cause);
    }

}
