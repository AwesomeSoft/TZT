package com.awesomesoft.tzt.service.ns;

/**
 * Api request type. This type is the basis for all responses from the NS api.
 * 
 * @author Paul van Assen
 * 
 * @param <T> Result type
 */
public abstract class ApiRequest<T> {

    /**
     * Allow only instantiation in this package
     */
    ApiRequest() {
        super();
    }

    /**
     * @return The path part of the request to make to the NS webservices
     */
    abstract String getPath();

    /**
     * @return The querystring part of the request to make to the NS webservices
     */
    abstract String getRequestString();
}
