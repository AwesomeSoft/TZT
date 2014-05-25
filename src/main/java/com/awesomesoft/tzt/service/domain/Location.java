package com.awesomesoft.tzt.service.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Created by student on 5/13/14.
 */
@Entity
public class Location {

    @Id
    @GeneratedValue
    private Long id;

    private double lng;
    private double lat;

    @OneToOne(mappedBy = "location")
    private Address address;

    protected Location(){

    }

    public Location(double lng, double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }
}
