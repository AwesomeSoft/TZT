package com.awesomesoft.tzt.service.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.sql.Time;
import java.util.Date;

/**
 * Created by Erwin on 22-5-2014.
 */

@Entity
@DiscriminatorValue("TT")
public class TrainTraject extends Traject{

    @ManyToOne
    private TrainCourier trainCourier;

    public TrainTraject(){
       super.setDepartureTimeAsTime(new Time(new Date().getTime()));;
    }

    public TrainTraject(double distance, double totalCostPriceTraject, double fixedPrice, double pricePerKm, long duration, Location startPoint, Location endPoint) {
        super(distance, totalCostPriceTraject, fixedPrice, pricePerKm, duration, startPoint, endPoint);

    }



}
