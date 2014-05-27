package com.awesomesoft.tzt.service.ns.model.reisadvies;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;


public class ReisStop {

    private final String naam;

    private final Date tijd;

    private final String spoor;

    private final boolean gewijzigdVertrekspoor;

    ReisStop(String naam, Date tijd, String spoor, boolean gewijzigdVertrekspoor) {
        super();
        this.naam = naam;
        this.tijd = tijd;
        this.spoor = spoor;
        this.gewijzigdVertrekspoor = gewijzigdVertrekspoor;
    }


    public String getNaam() {
        return naam;
    }


    public Date getTijd() {
        return tijd;
    }

    public String getSpoor() {
        return spoor;
    }


    public boolean isGewijzigdVertrekspoor() {
        return gewijzigdVertrekspoor;
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
