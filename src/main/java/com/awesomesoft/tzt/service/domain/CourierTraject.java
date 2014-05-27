package com.awesomesoft.tzt.service.domain;

import javax.persistence.*;

/**
 * Created by student on 5/27/14.
 */

@Entity
public class CourierTraject extends Traject{

    @ManyToOne  //Dit defineerd de relatie met de Trajecten.
    private  CourierCompany courierCompany;

    // de rest laten we leeg want dit ben jij nu niet nodig.
    protected CourierTraject() {
    }

    public CourierTraject(double distance, double totalCostPriceTraject, double fixedPrice, double pricePerKm, long duration, Location startPoint, Location endPoint) {
        super(distance, totalCostPriceTraject, fixedPrice, pricePerKm, duration, startPoint,endPoint);

    }



}
