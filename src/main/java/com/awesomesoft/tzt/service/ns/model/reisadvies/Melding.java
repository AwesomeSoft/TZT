package com.awesomesoft.tzt.service.ns.model.reisadvies;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class Melding {

    private final String id;

    private final boolean ernstig;

    private final String text;

    Melding(String id, boolean ernstig, String text) {
        super();
        this.id = id;
        this.ernstig = ernstig;
        this.text = text;
    }

    public String getId() {
        return id;
    }

    public boolean isErnstig() {
        return ernstig;
    }

    public String getText() {
        return text;
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
