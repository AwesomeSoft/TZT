package com.awesomesoft.tzt.service.ns;

import com.awesomesoft.tzt.service.ns.model.vertrektijden.VertrekkendeTrein;

import java.util.List;

class ActueleVertrekTijdenRequest extends ApiRequest<List<VertrekkendeTrein>> {

    private final String station;

    ActueleVertrekTijdenRequest(String station) {
        this.station = station;
    }

    @Override
    String getPath() {
        return "ns-api-avt";
    }

    @Override
    String getRequestString() {
        return "station=" + station;
    }
}
