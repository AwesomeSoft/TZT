package com.awesomesoft.tzt.web;

/**
 * Created by student on 5/17/14.
 */

import com.awesomesoft.tzt.service.GoogleMapsApi.GoogleMapsApi;
import com.awesomesoft.tzt.service.TZTRepository;
import com.awesomesoft.tzt.service.domain.Person;
import com.awesomesoft.tzt.service.domain.TZTOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;


@ManagedBean  //zorgt ervoor dat de personcontroller in JSF beschikbaar is
@SessionScoped
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Inject
    TZTRepository repository;



    private Person receiver;

    public TZTOrder getTztOrder() {
        return tztOrder;
    }

    private TZTOrder tztOrder;

    @PostConstruct
    public void init() {
        if(tztOrder == null){
            tztOrder = new TZTOrder();
        }
        if(receiver == null){
            receiver = new Person();
        }
    }

    public Person getReceiver() {
        return receiver;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    public void aptTest(){
              GoogleMapsApi.planRoute("Zwolle","Groningen");
    }

    public String createOrder(Person person){
        logger.info("Registering: the order");
        tztOrder.setCustomer(person);
        Long id = repository.insertPerson(person);  // Hier insertPerson hij de person in de database. Dit levert een ID op.
        return "confirmation.xhtml";

    }

}
