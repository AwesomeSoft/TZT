package com.awesomesoft.tzt.service.ns;

import com.awesomesoft.tzt.service.ns.model.storingen.Storingen;

class StoringenEnWerkzaamhedenRequest extends ApiRequest<Storingen> {

    private final String station;

    private final Boolean actual;

    private final Boolean unplanned;

    StoringenEnWerkzaamhedenRequest(String station, Boolean actual, Boolean unplanned) {
        this.station = station;
        this.actual = actual;
        this.unplanned = unplanned;
    }


    @Override
    String getPath() {
        return "ns-api-storingen";
    }

    @Override
    String getRequestString() {
        StringBuilder requestString = new StringBuilder();
        if (station != null && station.trim().length() != 0) {
            requestString.append("station=").append(station).append('&');
        }
        if (actual != null) {
            requestString.append("actual=").append(actual).append('&');
        }
        if (unplanned != null) {
            requestString.append("unplanned=").append(unplanned).append('&');
        }
        return requestString.toString();
    }
}
