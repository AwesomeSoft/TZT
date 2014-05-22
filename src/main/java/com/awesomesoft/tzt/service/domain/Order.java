package com.awesomesoft.tzt.service.domain;

import javax.persistence.*;

/**
 * Created by Erwin on 21-5-2014.
 */
@Entity
public class Order {
    protected long id;
    @Id
    @GeneratedValue
    private int orderNumber;
    private int customernr;
    private double totalCostprice;
    private double custumerPrice;

    public void setCustomernr() {

    }
}
