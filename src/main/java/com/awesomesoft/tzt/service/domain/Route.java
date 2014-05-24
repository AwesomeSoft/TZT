package com.awesomesoft.tzt.service.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;

/**
 * Created by Erwin on 24-5-2014.
 */
@Entity
public class Route {
    @javax.persistence.Id
    @GeneratedValue
    private Long id;
    @OneToOne
    private Package packAge;
    @OneToOne
    private Location senderLocation;
    @OneToOne
    private Location deliveryLocation;
    private int distance;
    protected Route(){
    }

    //public void calculateRoute()

}
