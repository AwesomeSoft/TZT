package com.awesomesoft.tzt.service.domain;

import javax.persistence.*;

/**
 * Created by student on 5/26/14.
 */
@Entity
public class CourierCompany {

    @Id
    @GeneratedValue
    private Long id;

    private double pricePerKm;

    private double fixedPrice;

    private String name;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CourierTraject asignedTraject;

    public CourierCompany(){

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public double getFixedPrice() {
        return fixedPrice;
    }

    public void setFixedPrice(double fixedPrice) {
        this.fixedPrice = fixedPrice;
    }

    public CourierCompany(double pricePerKm, String name){
        this.name = name;
        this.pricePerKm = pricePerKm;
    }

    public void planTraject(CourierTraject courierTraject) {
        asignedTraject = courierTraject;
        courierTraject.asignCourierCompany(this);
    }

    public double calcTotalTrajectPrice(Traject traject){
        double distance = traject.getDistance();
        return distance*pricePerKm+fixedPrice;
    }
}
