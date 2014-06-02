package com.awesomesoft.tzt.service.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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

    @OneToOne
    private Station startPointStation;

    @OneToOne
    private Station endPointStation;

    public TrainTraject(){
       super.setDepartureTimeAsTime(new Time(new Date().getTime()));;
    }

    public TrainTraject(double distance, double totalCostPriceTraject, double fixedPrice, double pricePerKm, long duration, Location startPoint, Location endPoint) {
        super(distance, totalCostPriceTraject, fixedPrice, pricePerKm, duration, startPoint, endPoint);

    }

    public void planTraject(TrainCourier trainCourier){
        this.trainCourier = trainCourier;
        trainCourier._ownTraject(this);
    }
    public Station getEndPointStation() {
        return endPointStation;
    }

    public void setEndPointStation(Station endPointStation) {
        this.endPointStation = endPointStation;
    }

    public Station getStartPointStation() {
        return startPointStation;
    }

    public void setStartPointStation(Station startPointStationName) {
        this.startPointStation = startPointStationName;
    }
}
