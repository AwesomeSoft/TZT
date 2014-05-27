package com.awesomesoft.tzt.service.ns.model.stations;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public class Namen {

    private final String kort;

    private final String middel;

    private final String lang;

    Namen(String kort, String middel, String lang) {
        super();
        this.kort = kort;
        this.middel = middel;
        this.lang = lang;
    }


    public String getKort() {
        return kort;
    }


    public String getMiddel() {
        return middel;
    }


    public String getLang() {
        return lang;
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
