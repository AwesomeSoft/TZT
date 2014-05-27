package com.awesomesoft.tzt.service.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by student on 5/26/14.
 */
@Entity
public class CourierCompany {

    @Id
    @GeneratedValue
    private Long id;

    private double pricePerKm;

    private String name;

    protected CourierCompany(){

    }

    public CourierCompany(double pricePerKm, String name){
        this.name = name;
        this.pricePerKm = pricePerKm;
    }
}
