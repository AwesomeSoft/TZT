package com.awesomesoft.tzt.service.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by student on 5/26/14.
 */

@Entity
public class Traject {

    @Id
    @GeneratedValue
    private Long id;

    private double distance;

    private double costPrice;

    private long duration;

    protected Traject(){

    }

    public Traject(double distance, double costPrice, long duration) {
        this.distance = distance;
        this.costPrice = costPrice;
        this.duration = duration;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
