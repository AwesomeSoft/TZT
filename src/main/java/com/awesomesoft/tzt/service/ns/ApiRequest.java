package com.awesomesoft.tzt.service.ns;

public abstract class ApiRequest<T> {

    ApiRequest() {
        super();
    }


    abstract String getPath();

    abstract String getRequestString();
}
