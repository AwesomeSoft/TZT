package com.awesomesoft.tzt.web;

/**
 * Created by student on 5/17/14.
 */

import com.awesomesoft.tzt.service.GoogleMapsApi.GoogleMapsApi;
import com.awesomesoft.tzt.service.GoogleMapsApi.models.*;
import com.awesomesoft.tzt.service.TZTRepository;
import com.awesomesoft.tzt.service.domain.*;
import com.awesomesoft.tzt.service.impl.JPAException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
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

        Address senderAddress = new Address("Aquamarijnstraat","599","9743PP","Groningen");
        Address deliveryAddress =  new Address("Overtoom","71","1054HC","Amsterdam");

        try {
            System.out.println("Got the address");
            Station nearestSenderStations = repository.getNearestStations(senderAddress.getLocation()).get(0);
            Station nearestDeliveryStations = repository.getNearestStations(deliveryAddress.getLocation()).get(0);
            System.out.println("Calculate Route");
            com.awesomesoft.tzt.service.GoogleMapsApi.models.Route Route = GoogleMapsApi.planRoute(nearestSenderStations.getLocation(), nearestDeliveryStations.getLocation(), "transit");
            List<Leg> legs = Route.getLegs();
            System.out.println(legs.size());
            List<TrainTraject> trainTrajects = new LinkedList<>();
            List<CourierTraject> courierTrajects = new LinkedList<>();
            for (Leg leg : legs) {
                System.out.println(leg.getDistance().getValue());
                System.out.println(leg.getDuration().getValue());
                List<Step> steps = leg.getSteps();
                for (Step step : steps) {
                    System.out.println(step.getDistance().getText());
                    System.out.println(step.getDuration().getText());
                    System.out.println(step.getManeuver());
                    System.out.println(step.getTravel_mode());
                    System.out.println(step.getHtml_instructions());
                    if(step.getHtml_instructions().startsWith("Train")){
                        TrainTraject trainTraject = new TrainTraject(120.0,33.0,2.0,10,123123,nearestSenderStations.getLocation(),nearestDeliveryStations.getLocation());
                        trainTrajects.add(trainTraject);
                    }
                }
            }
            System.out.println("Total traintrajects: "+trainTrajects.size());
            com.awesomesoft.tzt.service.GoogleMapsApi.models.Route RouteToStation = GoogleMapsApi.planRoute(senderAddress.getLocation(), nearestSenderStations.getLocation(), "driving");

            List<Leg> routeToStationLegs = RouteToStation.getLegs();
            for (Leg routeToStationLeg : routeToStationLegs) {
                System.out.println(routeToStationLeg.getDistance().getValue());
                System.out.println(routeToStationLeg.getDuration().getValue());
                CourierTraject courierTraject = new CourierTraject(routeToStationLeg.getDistance().getValue()/1.609344,12,12,12,12,senderAddress.getLocation(),nearestSenderStations.getLocation());
                courierTrajects.add(courierTraject);
                List<Step> routeToSteps = routeToStationLeg.getSteps();
                for (Step routeToStep : routeToSteps) {
                    System.out.println(routeToStep.getDistance().getText());
                    System.out.println(routeToStep.getDuration().getText());
                    System.out.println(routeToStep.getManeuver());
                    System.out.println(routeToStep.getTravel_mode());
                    System.out.println(routeToStep.getHtml_instructions());
                }
            }
            com.awesomesoft.tzt.service.GoogleMapsApi.models.Route RouteFromStation = GoogleMapsApi.planRoute(deliveryAddress.getLocation(), nearestDeliveryStations.getLocation(), "driving");

            List<Leg> routeFromStationLegs = RouteFromStation.getLegs();
            for (Leg routeFromStationLeg : routeFromStationLegs) {
                System.out.println(routeFromStationLeg.getDistance().getValue());
                System.out.println(routeFromStationLeg.getDuration().getValue());
                CourierTraject courierTraject = new CourierTraject(routeFromStationLeg.getDistance().getValue()/1.609344,12,12,12,12,deliveryAddress.getLocation(),nearestDeliveryStations.getLocation());
                courierTrajects.add(courierTraject);

                List<Step> routeFromSteps = routeFromStationLeg.getSteps();
                for (Step routeFromStep : routeFromSteps) {
                    System.out.println(routeFromStep.getDistance().getText());
                    System.out.println(routeFromStep.getDuration().getText());
                    System.out.println(routeFromStep.getManeuver());
                    System.out.println(routeFromStep.getTravel_mode());
                    System.out.println(routeFromStep.getHtml_instructions());
                }
            }

            com.awesomesoft.tzt.service.domain.Route finalRoute = new com.awesomesoft.tzt.service.domain.Route();
  //          finalRoute.addTraject(courierTrajects.get(0));
   //         finalRoute.addTraject(trainTrajects.get(0));
   //         finalRoute.addTraject(courierTrajects.get(1));
            Long id = Long.parseLong("1150");
            TZTOrder tztOrder = repository.findOrder(id);
            tztOrder.addRoute(finalRoute);
            repository.updateTZTOrder(tztOrder);

        } catch (JPAException e) {
            throw new RuntimeException(e);

        }

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
