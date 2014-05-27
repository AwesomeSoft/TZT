package com.awesomesoft.tzt.service.ns.model.prijzen;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class Prijs {

    private final String korting;

    private final int klasse;

    private final int prijs;

    Prijs(String korting, int klasse, int prijs) {
        super();
        this.korting = korting;
        this.klasse = klasse;
        this.prijs = prijs;
    }

    public String getKorting() {
        return korting;
    }

    public int getKlasse() {
        return klasse;
    }

    public int getPrijs() {
        return prijs;
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
