package com.awesomesoft.tzt.service.ns.model.prijzen;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;


public class Product {

    private final String naam;

    private final List<Prijs> prijzen;

    Product(String naam, List<Prijs> prijzen) {
        super();
        this.naam = naam;
        this.prijzen = prijzen;
    }

    public String getNaam() {
        return naam;
    }

    public List<Prijs> getPrijzen() {
        return prijzen;
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
