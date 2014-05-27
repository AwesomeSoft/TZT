package com.awesomesoft.tzt.web;

/**
 * Created by student on 5/17/14.
 */

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
        /*
        Address senderAddress = new Address("Aquamarijnstraat","599","9743PP","Groningen");
        Address deliveryAddress =  new Address("Overtoom","71","1054HC","Amsterdam");

        try {
            System.out.println("Got the address");
            Station nearestSenderStations = repository.getNearestStations(senderAddress.getLocation()).get(0);
            Station nearestDeliveryStations = repository.getNearestStations(deliveryAddress.getLocation()).get(0);
            System.out.println("Calculate route");
            Route route = GoogleMapsApi.planRoute(nearestSenderStations.getLocation(),nearestDeliveryStations.getLocation(),"transit");
            List<Leg> legs = route.getLegs();
            System.out.println(legs.size());
            for (Leg leg : legs) {
                System.out.println(leg.getDistance());
                System.out.println(leg.getDuration());
                List<Step> steps = leg.getSteps();
                for (Step step : steps) {
                    System.out.println(step.getDistance().getText());
                    System.out.println(step.getDuration().getText());
                    System.out.println(step.getManeuver());
                    System.out.println(step.getTravel_mode());
                    System.out.println(step.getHtml_instructions());
                }

            }


        } catch (JPAException e) {
            throw new RuntimeException(e);

        }
        */
    }

    public Person getReceiver() {
        return receiver;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    public String createOrder(Person person){
        tztOrder.setReceiver(this.receiver);
        tztOrder.setCustomer(person);
        repository.updatePerson(person);
        Long id = repository.insertOrder(tztOrder);
        return "confirmation.xhtml";
    }

    /*
    private void calculateRoute(Address senderAddress,Address deliveryAddress){

        LinkedList<Station> nearestSenderStations = null;
        LinkedList<Station> nearestDeliveryStations = null;
        try {

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
    */


}
