package com.awesomesoft.tzt.service.ns;

import com.awesomesoft.tzt.service.ns.model.prijzen.Producten;
import com.awesomesoft.tzt.service.ns.model.stations.Station;
import com.awesomesoft.tzt.service.ns.model.storingen.Storingen;
import com.awesomesoft.tzt.service.ns.model.vertrektijden.VertrekkendeTrein;

import java.util.Date;
import java.util.List;

public class RequestBuilder {

    private static final ApiRequest INSTANCE = new StationsRequest(); /*StationRequest*/

    private RequestBuilder() {
        super();
    }

    public static ApiRequest<List<VertrekkendeTrein>> getActueleVertrektijden(String station) {
        return new ActueleVertrekTijdenRequest(station);
    }

    public static ApiRequest<List<Station>> getStations() {
        return RequestBuilder.INSTANCE;
    }

    public static ApiRequest<Storingen> getActueleStoringen() {
        return new StoringenEnWerkzaamhedenRequest(null, Boolean.TRUE, null);
    }

    public static ApiRequest<Storingen> getGeplandeWerkzaamheden() {
        return new StoringenEnWerkzaamhedenRequest(null, null, Boolean.TRUE);
    }

    public static ApiRequest<Storingen> getActueleStoringen(String station) {
        return new StoringenEnWerkzaamhedenRequest(station, null, null);
    }

    public static ApiRequest<Producten> getPrijzen(String fromStation, String toStation) {
        return RequestBuilder.getPrijzen(fromStation, toStation, null, null);
    }

    public static ApiRequest<Producten> getPrijzen(String fromStation, String toStation, String viaStation) {
        return RequestBuilder.getPrijzen(fromStation, toStation, viaStation, null);
    }

    public static ApiRequest<Producten> getPrijzen(String fromStation, String toStation, Date dateTime) {
        return RequestBuilder.getPrijzen(fromStation, toStation, null, dateTime);
    }

    public static ApiRequest<Producten> getPrijzen(String fromStation, String toStation, String viaStation,
            Date dateTime) {
        return new PrijzenRequest(fromStation, toStation, viaStation, dateTime);
    }

    public static ReisadviesRequestBuilder getReisadviesRequestBuilder(String fromStation, String toStation) {
        return new ReisadviesRequestBuilder(fromStation, toStation);
    }

    public static class ReisadviesRequestBuilder {

        private final String fromStation;

        private final String toStation;

        private String viaStation;

        private Integer previousAdvices;

        private Integer nextAdvices;

        private Date dateTime;

        private Boolean departure;

        private Boolean hslAllowed;

        private Boolean yearCard;

        ReisadviesRequestBuilder(String fromStation, String toStation) {
            this.fromStation = fromStation;
            this.toStation = toStation;
        }

        public ReisadviesRequestBuilder viaStation(String station) {
            viaStation = station;
            return this;
        }

        public ReisadviesRequestBuilder forDepartureTime(Date dateTime) {
            if (this.dateTime != null) {
                throw new IllegalArgumentException("Cannot set departure time, arival time already set");
            }
            this.dateTime = dateTime;
            departure = true;
            return this;
        }

        public ReisadviesRequestBuilder forArrivalTime(Date dateTime) {
            if (this.dateTime != null) {
                throw new IllegalArgumentException("Cannot set arival time, departure time already set");
            }
            this.dateTime = dateTime;
            departure = false;
            return this;
        }

        public ReisadviesRequestBuilder includePastAdvices(int previousAdvices) {
            this.previousAdvices = previousAdvices;
            return this;
        }

        public ReisadviesRequestBuilder includeFutureAdvices(int nextAdvices) {
            this.nextAdvices = nextAdvices;
            return this;
        }

        public ReisadviesRequestBuilder withHsl() {
            hslAllowed = Boolean.TRUE;
            return this;
        }

        public ReisadviesRequestBuilder withoutHsl() {
            hslAllowed = Boolean.FALSE;
            return this;
        }

        public ReisadviesRequestBuilder userHasYearCard() {
            yearCard = Boolean.TRUE;
            return this;
        }

        public ReisadviesRequestBuilder userHasNoYearCard() {
            yearCard = Boolean.FALSE;
            return this;
        }

        public ReisadviesRequest build() {
            return new ReisadviesRequest(fromStation, toStation, viaStation, previousAdvices, nextAdvices, dateTime,
                    departure, hslAllowed, yearCard);
        }
    }

}
