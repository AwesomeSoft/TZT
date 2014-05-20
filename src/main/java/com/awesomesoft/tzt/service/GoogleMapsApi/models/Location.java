package com.awesomesoft.tzt.service.GoogleMapsApi.models;

/**
 * Created by student on 5/20/14.
 */
public class Location {
    private double lat;
    private double lng;

    public double getLat() {
        return lat;
    }



    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
    @Override
    public String toString() {
        return "Location{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
