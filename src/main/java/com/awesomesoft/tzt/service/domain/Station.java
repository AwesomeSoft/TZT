package com.awesomesoft.tzt.service.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToOne;


/**
 * Created by student on 5/15/14.
 */
@Entity
public class Station {

    @javax.persistence.Id
    @GeneratedValue
    private Long id;

    private String name;


    @OneToOne
    private Location location;

    protected Station(){

    }

    public Station(String name, Location location) {
        this.name = name;
        this.location = location;
    }
}
