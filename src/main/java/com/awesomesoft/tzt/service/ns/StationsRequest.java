package com.awesomesoft.tzt.service.ns;

import com.awesomesoft.tzt.service.ns.model.stations.Station;

import java.util.List;

public class StationsRequest extends ApiRequest<List<Station>> {

    public StationsRequest() {
    }

    @Override
    String getPath() {
        return "ns-api-stations-v2";
    }

    @Override
    String getRequestString() {
        return "";
    }
}
