package com.awesomesoft.tzt.service.domain;

import javax.persistence.*;

/**
 * Created by student on 5/27/14.
 */

@Entity
@DiscriminatorValue("CT")
public class CourierTraject extends Traject{


    // de rest laten we leeg want dit ben jij nu niet nodig.
    protected CourierTraject() {
    }

    public CourierTraject(double distance, long duration, Location startPoint, Location endPoint) {
        super(distance, duration, startPoint,endPoint);
    }

    public void asignTrajectToCourier(CourierCompany courierCompany){
        super.increaseCostPrice(courierCompany.calcTotalTrajectPrice(this));
        super.asignCourierCompany(courierCompany);
    }
}

