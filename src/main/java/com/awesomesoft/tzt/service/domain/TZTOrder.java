package com.awesomesoft.tzt.service.domain;

import javax.persistence.*;

/**
 * Created by Erwin on 21-5-2014.
 */
@Entity
public class TZTOrder {

    @Id
    @GeneratedValue
    private Long id;// een entiteit heeft een ID nodig met deze anotatiets @id en @generated value
    @ManyToOne(cascade={CascadeType.ALL})
    private Person customer;
    private int orderNumber;
    //  private Person customer;
    private double totalCostprice;
    private double custumerPrice;

    protected TZTOrder(){

    }
}
