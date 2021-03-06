package com.awesomesoft.tzt.service.ns.model.stations;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collections;
import java.util.List;

public class Station {

    private final String code;

    private final String type;

    private final Namen namen;

    private final String land;

    private final int uicCode;

    private final double lat;

    private final double lon;

    private final List<String> synoniemen;

    Station(String code, String type, Namen namen, String land, int uicCode, double lat, double lon,
            List<String> synoniemen) {
        super();
        this.code = code;
        this.type = type;
        this.namen = namen;
        this.land = land;
        this.uicCode = uicCode;
        this.lat = lat;
        this.lon = lon;
        this.synoniemen = Collections.unmodifiableList(synoniemen);
    }

    public String getCode() {
        return code;
    }


    public String getType() {
        return type;
    }


    public Namen getNamen() {
        return namen;
    }

    public String getLand() {
        return land;
    }

    public int getUicCode() {
        return uicCode;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public List<String> getSynoniemen() {
        return synoniemen;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}