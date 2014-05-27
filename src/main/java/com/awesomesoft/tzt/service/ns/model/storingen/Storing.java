package com.awesomesoft.tzt.service.ns.model.storingen;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class Storing {

    private final String id;

    private final String traject;

    private final String periode;

    private final String reden;

    private final String advies;

    private final String bericht;

    private final Date datum;

    Storing(String id, String traject, String periode, String reden, String advies, String bericht, Date datum) {
        super();
        this.id = id;
        this.traject = traject;
        this.periode = periode;
        this.reden = reden;
        this.advies = advies;
        this.bericht = bericht;
        this.datum = datum;
    }

    public String getId() {
        return id;
    }

    public String getTraject() {
        return traject;
    }

    public String getPeriode() {
        return periode;
    }

    public String getReden() {
        return reden;
    }

    public String getAdvies() {
        return advies;
    }

    public String getBericht() {
        return bericht;
    }

    public Date getDatum() {
        return datum;
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
