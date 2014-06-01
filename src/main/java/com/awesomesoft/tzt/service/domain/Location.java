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

    @OneToOne(mappedBy = "location")
    private Station station;

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

    @Override
    public String toString() {
        return lat+","+lng;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Location)) return false;

        Location location = (Location) o;

        if (Double.compare(location.lat, lat) != 0) return false;
        if (Double.compare(location.lng, lng) != 0) return false;
        if (address != null ? !address.equals(location.address) : location.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(lng);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lat);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
