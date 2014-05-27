package com.awesomesoft.tzt.service.ns.model.prijzen;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;


public class Producten {

    private final int tariefEenheden;

    private final List<Product> producten;

    Producten(int tariefEenheden, List<Product> producten) {
        super();
        this.tariefEenheden = tariefEenheden;
        this.producten = producten;
    }

    public int getTariefEenheden() {
        return tariefEenheden;
    }


    public List<Product> getProducten() {
        return producten;
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
