package com.awesomesoft.tzt.service.ns.model.reisadvies;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collections;
import java.util.List;

public class ReisDeel {

    private final String reisSoort;

    private final String vervoerder;

    private final String vervoerType;

    private final int ritNummer;

    private final String status;

    private final List<ReisStop> reisStops;

    private final String ongeplandeStoringId;

    private final String geplandeStoringId;

    private final List<String> reisDetails;

    ReisDeel(String reisSoort, String vervoerder, String vervoerType, int ritNummer, String status,
            List<ReisStop> reisStops, String ongeplandeStoringId, String geplandeStoringId, List<String> reisDetails) {
        super();
        this.reisSoort = reisSoort;
        this.vervoerder = vervoerder;
        this.vervoerType = vervoerType;
        this.ritNummer = ritNummer;
        this.status = status;
        this.reisStops = Collections.unmodifiableList(reisStops);
        this.ongeplandeStoringId = ongeplandeStoringId;
        this.geplandeStoringId = geplandeStoringId;
        this.reisDetails = Collections.unmodifiableList(reisDetails);
    }


    public String getReisSoort() {
        return reisSoort;
    }


    public String getVervoerder() {
        return vervoerder;
    }


    public String getVervoerType() {
        return vervoerType;
    }

    public int getRitNummer() {
        return ritNummer;
    }


    public String getStatus() {
        return status;
    }


    public List<ReisStop> getReisStops() {
        return reisStops;
    }


    public String getOngeplandeStoringId() {
        return ongeplandeStoringId;
    }


    public String getGeplandeStoringId() {
        return geplandeStoringId;
    }


    public List<String> getReisDetails() {
        return reisDetails;
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
