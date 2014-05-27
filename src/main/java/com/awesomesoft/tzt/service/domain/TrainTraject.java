package com.awesomesoft.tzt.service.domain;

import javax.persistence.*;

/**
 * Created by Erwin on 22-5-2014.
 */

@Entity
public class TrainTraject extends Traject{


    @ManyToOne  //Dit defineerd de relatie met de Trajecten.
    private  TrainCourier trainCourier;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Station startPoint;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Station endPoint;
    // de rest laten we leeg want dit ben jij nu niet nodig.

    protected TrainTraject(){

    }

    public TrainTraject(double distance, double totalCostPriceTraject, double fixedPrice, double pricePerKm, long duration, Location startPoint, Location endPoint) {
        super(distance, totalCostPriceTraject, fixedPrice, pricePerKm, duration, startPoint, endPoint);
    }
}
