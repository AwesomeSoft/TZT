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


    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Traject> trajects = new LinkedList<>();

    @OneToOne(cascade = CascadeType.ALL)
    private TZTOrder tztOrder;


    public Route(){

    }

    public void addTraject(Traject traject){
        trajects.add(traject);
    }

    public void addOrder(TZTOrder tztOrder){
        this.tztOrder = tztOrder;
    }

    //public void calculateRoute()

}
