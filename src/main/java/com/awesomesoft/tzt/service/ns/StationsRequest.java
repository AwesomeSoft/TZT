package com.awesomesoft.tzt.service.ns;

import com.awesomesoft.tzt.service.ns.model.stations.Station;

import java.util.List;

class StationsRequest extends ApiRequest<List<Station>> {

    StationsRequest() {
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
