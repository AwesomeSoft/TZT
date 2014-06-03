package com.awesomesoft.tzt.service.domain;

import javax.persistence.*;
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

    @OneToOne(cascade = CascadeType.ALL)
    private Station startPointStation;

    @OneToOne(cascade = CascadeType.ALL)
    private Station endPointStation;

    public TrainTraject(){
       super();
       super.setDepartureTimeAsTime(new Time(new Date().getTime()));;
    }

    public TrainTraject(double distance, long duration, Location startPoint, Location endPoint) {
        super(distance, duration, startPoint, endPoint);

    }

    public void asignTrajectToCourier(TrainCourier trainCourier){
        super.increaseCostPrice(trainCourier.calcTotalTrajectPrice(this));
        super.asignTrainCourier(trainCourier);
    }

    public Station getEndPointStation() {
        return endPointStation;
    }

    public void planTraject(TrainCourier trainCourier){
        this.trainCourier = trainCourier;
        trainCourier._ownTraject(this);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrainTraject)) return false;
        if (!super.equals(o)) return false;

        TrainTraject that = (TrainTraject) o;

        if (endPointStation != null ? !endPointStation.equals(that.endPointStation) : that.endPointStation != null)
            return false;
        if (startPointStation != null ? !startPointStation.equals(that.startPointStation) : that.startPointStation != null)
           return false;

      return true;
    }

    @Override
    public int hashCode() {
       int result = super.hashCode();
       result = 31 * result + (startPointStation != null ? startPointStation.hashCode() : 0);
       result = 31 * result + (endPointStation != null ? endPointStation.hashCode() : 0);
       return result;
    }
}
