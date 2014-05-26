package com.awesomesoft.tzt.web;

/**
 * Created by student on 5/17/14.
 */

import com.awesomesoft.tzt.service.GoogleMapsApi.GoogleMapsApi;
import com.awesomesoft.tzt.service.GoogleMapsApi.models.Leg;
import com.awesomesoft.tzt.service.GoogleMapsApi.models.Route;
import com.awesomesoft.tzt.service.TZTRepository;
import com.awesomesoft.tzt.service.domain.Address;
import com.awesomesoft.tzt.service.domain.Person;
import com.awesomesoft.tzt.service.domain.Station;
import com.awesomesoft.tzt.service.domain.TZTOrder;
import com.awesomesoft.tzt.service.impl.JPAException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


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
        tztOrder.setReceiver(this.receiver);
        tztOrder.setCustomer(person);
        repository.updatePerson(person);
        Long id = repository.insertOrder(tztOrder);
        return "confirmation.xhtml";
    }

    private void calculateRoute(Address senderAddress,Address deliveryAddress){

        LinkedList<Station> nearestSenderStations = null;
        LinkedList<Station> nearestDeliveryStations = null;
        try {
            nearestSenderStations = repository.getNearestStations(senderAddress.getLocation());
            nearestDeliveryStations = repository.getNearestStations(deliveryAddress.getLocation());
            Route groute = GoogleMapsApi.getTrainRoute(nearestSenderStations.get(0), nearestDeliveryStations.get(0), new Date());
            List<Leg> routeLeg = groute.getLegs();
            for (Leg leg : routeLeg) {

                //Todo overstap vinden
                System.out.println(leg.getSteps().size());
                System.out.println(leg.getDistance().getText());
                System.out.println(leg.getDuration().getText());
                System.out.println(leg.getVia_waypoint().size());
            }
            //Todo route plannen van senderlocatie tot station
            //Todo route plannen van station tot ontvangers
        } catch (JPAException e) {
            throw new RuntimeException(e);

        }
    }



}
