package com.awesomesoft.tzt.service.ns.model.storingen;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Collections;
import java.util.List;

public class Storingen {

    private final List<Storing> ongeplandeStoringen;

    private final List<Storing> geplandeStoringen;

    Storingen(List<Storing> ongeplandeStoringen, List<Storing> geplandeStoringen) {
        this.ongeplandeStoringen = Collections.unmodifiableList(ongeplandeStoringen);
        this.geplandeStoringen = Collections.unmodifiableList(geplandeStoringen);
    }

    public List<Storing> getOngeplandeStoringen() {
        return ongeplandeStoringen;
    }

    public List<Storing> getGeplandeStoringen() {
        return geplandeStoringen;
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
