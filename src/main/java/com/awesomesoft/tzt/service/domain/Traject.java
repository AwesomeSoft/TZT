package com.awesomesoft.tzt.service.domain;

import javax.persistence.*;

/**
 * Created by student on 5/26/14.
 */

@Entity
public class Traject {

    @Id
    @GeneratedValue
    private Long id;

    private double distance;

    private double totalCostPriceTraject;

    private double fixedPrice;

    private double pricePerKm;

    private long duration;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Location startPoint;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Location endPoint;

    @ManyToOne(cascade=CascadeType.ALL)
    private Route route;

    protected Traject(){

    }

    public Traject(double distance, double totalCostPriceTraject, double fixedPrice, double pricePerKm, long duration, Location startPoint, Location endPoint) {
        this.distance = distance;
        this.totalCostPriceTraject = totalCostPriceTraject;
        this.duration = duration;
        this.pricePerKm = pricePerKm;
        this.fixedPrice = fixedPrice;
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getCostPrice() {
        return totalCostPriceTraject;
    }

    public void setCostPrice(double totalCostPriceTraject) {
        this.totalCostPriceTraject = totalCostPriceTraject;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public double getFixedPrice() {
        return fixedPrice;
    }

    public void setFixedPrice(double fixedPrice) {
        this.fixedPrice = fixedPrice;
    }

    public double getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public double getTotalCostPriceTraject() {
        return totalCostPriceTraject;
    }

    public void setTotalCostPriceTraject(double totalCostPriceTraject) {
        this.totalCostPriceTraject = totalCostPriceTraject;
    }
}
