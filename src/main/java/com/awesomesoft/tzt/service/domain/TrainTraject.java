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

}
