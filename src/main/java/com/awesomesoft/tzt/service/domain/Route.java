package com.awesomesoft.tzt.service.domain;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Erwin on 24-5-2014.
 */
@Entity
public class Route {
    @javax.persistence.Id
    @GeneratedValue
    private Long id;

    private double totalTrajectCosts;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Traject> trajects = new LinkedList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private TZTOrder tztOrder;


    public Route(){

    }


    private void increaseTotalTrajectCosts(double amount){
        this.totalTrajectCosts += amount;
    }

    public double getTotalTrajectCosts() {
        return totalTrajectCosts;
    }

    public void addTraject(Traject traject){
        trajects.add(traject);
        increaseTotalTrajectCosts(traject.getTotalCostPrice());
    }

    public void addOrder(TZTOrder tztOrder){
        this.tztOrder = tztOrder;
    }


}
