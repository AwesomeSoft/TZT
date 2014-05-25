package com.awesomesoft.tzt.service.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by Erwin on 22-5-2014.
 */

@Entity
public class TrainTraject {


    @Id
    @GeneratedValue
    private long id;

    @ManyToOne  //Dit defineerd de relatie met de Trajecten.
    private  TrainCourier trainCourier;

    // de rest laten we leeg want dit ben jij nu niet nodig.

}
