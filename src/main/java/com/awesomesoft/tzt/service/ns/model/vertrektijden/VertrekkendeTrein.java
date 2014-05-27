package com.awesomesoft.tzt.service.ns.model.vertrektijden;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collections;
import java.util.Date;
import java.util.List;


public class VertrekkendeTrein {

    private final int ritNummer;

    private final Date vertrekTijd;

    private final String vertrekVertraging;

    private final int vertrekVertragingMinuten;

    private final String vertrekVertragingTekst;

    private final String eindBestemming;

    private final String treinSoort;

    private final String routeTekst;

    private final String vervoerder;

    private final String vertrekSpoor;

    private final boolean gewijzigdVertrekspoor;

    private final String reisTip;

    private final List<String> opmerkingen;

    VertrekkendeTrein(int ritNummer, Date vertrekTijd, String vertrekVertraging, int vertrekVertragingMinuten,
            String vertrekVertragingTekst, String eindBestemming, String treinSoort, String routeTekst,
            String vervoerder, String vertrekSpoor, boolean gewijzigdVertrekspoor, String reisTip,
            List<String> opmerkingen) {
        super();
        this.ritNummer = ritNummer;
        this.vertrekTijd = vertrekTijd;
        this.vertrekVertraging = vertrekVertraging;
        this.vertrekVertragingMinuten = vertrekVertragingMinuten;
        this.vertrekVertragingTekst = vertrekVertragingTekst;
        this.eindBestemming = eindBestemming;
        this.treinSoort = treinSoort;
        this.routeTekst = routeTekst;
        this.vervoerder = vervoerder;
        this.vertrekSpoor = vertrekSpoor;
        this.gewijzigdVertrekspoor = gewijzigdVertrekspoor;
        this.reisTip = reisTip;
        this.opmerkingen = Collections.unmodifiableList(opmerkingen);
    }


    public int getRitNummer() {
        return ritNummer;
    }


    public Date getVertrekTijd() {
        return vertrekTijd;
    }


    public String getVertrekVertraging() {
        return vertrekVertraging;
    }


    public int getVertrekVertragingMinuten() {
        return vertrekVertragingMinuten;
    }


    public String getVertrekVertragingTekst() {
        return vertrekVertragingTekst;
    }


    public String getEindBestemming() {
        return eindBestemming;
    }


    public String getTreinSoort() {
        return treinSoort;
    }

    public String getRouteTekst() {
        return routeTekst;
    }

    public String getVervoerder() {
        return vervoerder;
    }

    public String getVertrekSpoor() {
        return vertrekSpoor;
    }

    public boolean isGewijzigdVertrekspoor() {
        return gewijzigdVertrekspoor;
    }


    public String getReisTip() {
        return reisTip;
    }


    public List<String> getOpmerkingen() {
        return opmerkingen;
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
