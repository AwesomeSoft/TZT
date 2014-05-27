package com.awesomesoft.tzt.service.ns.model.reisadvies;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collections;
import java.util.Date;
import java.util.List;


public class ReisMogelijkheid {

    private final List<Melding> meldingen;

    private final int aantalOverstappen;

    private final int geplandeReisTijdMinuten;

    private final int actueleReisTijdMinuten;

    private final String aankomstVertraging;

    private final boolean optimaal;

    private final Date geplandeVertrekTijd;

    private final Date actueleVertrekTijd;

    private final Date geplandeAankomstTijd;

    private final Date actueleAankomstTijd;

    private final String status;

    private final List<ReisDeel> reisDelen;

    ReisMogelijkheid(List<Melding> meldingen, int aantalOverstappen, int geplandeReisTijdMinuten,
            int actueleReisTijdMinuten, String aankomstVertraging, boolean optimaal, Date geplandeVertrekTijd,
            Date actueleVertrekTijd, Date geplandeAankomstTijd, Date actueleAankomstTijd, String status,
            List<ReisDeel> reisDelen) {
        super();
        this.meldingen = Collections.unmodifiableList(meldingen);
        this.aantalOverstappen = aantalOverstappen;
        this.geplandeReisTijdMinuten = geplandeReisTijdMinuten;
        this.actueleReisTijdMinuten = actueleReisTijdMinuten;
        this.aankomstVertraging = aankomstVertraging;
        this.optimaal = optimaal;
        this.geplandeVertrekTijd = geplandeVertrekTijd;
        this.actueleVertrekTijd = actueleVertrekTijd;
        this.geplandeAankomstTijd = geplandeAankomstTijd;
        this.actueleAankomstTijd = actueleAankomstTijd;
        this.status = status;
        this.reisDelen = Collections.unmodifiableList(reisDelen);
    }


    public List<Melding> getMeldingen() {
        return meldingen;
    }


    public int getAantalOverstappen() {
        return aantalOverstappen;
    }


    public int getGeplandeReisTijdMinuten() {
        return geplandeReisTijdMinuten;
    }


    public int getActueleReisTijdMinuten() {
        return actueleReisTijdMinuten;
    }


    public String getAankomstVertraging() {
        return aankomstVertraging;
    }


    public boolean isOptimaal() {
        return optimaal;
    }

    public Date getGeplandeVertrekTijd() {
        return geplandeVertrekTijd;
    }


    public Date getActueleVertrekTijd() {
        return actueleVertrekTijd;
    }

    public Date getGeplandeAankomstTijd() {
        return geplandeAankomstTijd;
    }


    public Date getActueleAankomstTijd() {
        return actueleAankomstTijd;
    }


    public String getStatus() {
        return status;
    }


    public List<ReisDeel> getReisDelen() {
        return reisDelen;
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
