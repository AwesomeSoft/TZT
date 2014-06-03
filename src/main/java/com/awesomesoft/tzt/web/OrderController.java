package com.awesomesoft.tzt.web;
/**
 * Created by Gerben on 17-5-2014.
 */

import com.awesomesoft.tzt.service.GoogleMapsApi.GoogleMapsApi;
import com.awesomesoft.tzt.service.GoogleMapsApi.models.Leg;
import com.awesomesoft.tzt.service.GoogleMapsApi.models.Step;
import com.awesomesoft.tzt.service.TZTRepository;
import com.awesomesoft.tzt.service.domain.*;
import com.awesomesoft.tzt.service.exception.*;
import com.awesomesoft.tzt.service.impl.JPAException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


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

        if (tztOrder == null) {
            tztOrder = new TZTOrder();
        }
        if (receiver == null) {
            receiver = new Person();
        }
    }

    public Person getReceiver() {
        return receiver;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }

    /**
     * Creates an order and plans the route.
     * <p/>
     * Searches start and en point in planedTrajects of the TrainCourier.
     * If it founds a planed traject it is calculating the route duration and cost using GoogleMaps Api.
     * Then it calculates the same route for a car by using the GoogleMaps Api
     * Using the distance and duration it calculates the best courier to use.
     */
    public String createOrder(Person person) {
        try {
            verifyPersonLogIn(person);
            tztOrder.setReceiver(this.receiver);
            tztOrder.setCustomer(person);
            repository.updatePerson(person);
            Route finalRoute = new Route();
            try {
                Address senderAddress = person.getAddress();
                Address deliveryAddress = receiver.getAddress();
                repository.insertPerson(receiver);
                Station nearestSenderStations = repository.getNearestStations(senderAddress.getLocation()).get(0);
                Station nearestDeliveryStations = repository.getNearestStations(deliveryAddress.getLocation()).get(0);
                Map<TrainCourier, TrainTraject> trainCouriersForTraject = getTrainCouriersForRoute(nearestSenderStations, nearestDeliveryStations);
                for (Map.Entry<TrainCourier, TrainTraject> trainCourierTrainTrajectEntry : trainCouriersForTraject.entrySet()) {
                    TrainTraject tempTraject = trainCourierTrainTrajectEntry.getValue();
                    List<TrainTraject> newFilledTrainTrajects = calculateTrainCourierRoute(tempTraject.getStartPointStation().getLocation(),tempTraject.getEndPointStation().getLocation());
                    if(newFilledTrainTrajects.size() < 2){
                        Route tempTrainRoute = new Route();
                        Route tempCourierCompanyRoute = new Route();
                        //Direct Train route found
                        TrainTraject trainTraject = newFilledTrainTrajects.get(0);
                        //Assign courier to route.
                        trainTraject.asignTrajectToCourier(trainCourierTrainTrajectEntry.getKey());

                        CourierTraject toStation = calculateCourierCompanyRoute(senderAddress.getLocation(),nearestSenderStations.getLocation());
                        CourierTraject fromStation = calculateCourierCompanyRoute(deliveryAddress.getLocation(),nearestDeliveryStations.getLocation());
                        CourierTraject wholeTraject = calculateCourierCompanyRoute(senderAddress.getLocation(),deliveryAddress.getLocation());
                        List<CourierCompany> courierCompanies = repository.getAllCourierCompanies();
                        CourierCompany cheapestToStationCourier = new CourierCompany(99999,"temp");
                        CourierCompany cheapestFromStationCourier = new CourierCompany(99999,"temp");
                        CourierCompany cheapestwholeTrajectCourier = new CourierCompany(99999,"temp");
                        for (CourierCompany courierCompany : courierCompanies) {
                            if(courierCompany.calcTotalTrajectPrice(toStation) < cheapestToStationCourier.calcTotalTrajectPrice(toStation)){
                                cheapestToStationCourier = courierCompany;
                            }
                            if(courierCompany.calcTotalTrajectPrice(fromStation) < cheapestFromStationCourier.calcTotalTrajectPrice(fromStation)){
                                cheapestFromStationCourier = courierCompany;
                            }
                            if(courierCompany.calcTotalTrajectPrice(wholeTraject) < cheapestwholeTrajectCourier.calcTotalTrajectPrice(wholeTraject)){
                                cheapestwholeTrajectCourier = courierCompany;
                            }
                        }
                        toStation.asignTrajectToCourier(cheapestToStationCourier);
                        fromStation.asignTrajectToCourier(cheapestFromStationCourier);
                        tempTrainRoute.addTraject(toStation);
                        tempTrainRoute.addTraject(tempTraject);
                        tempTrainRoute.addTraject(fromStation);
                        tempCourierCompanyRoute.addTraject(wholeTraject);
                        Route bestRoute = (tempTrainRoute.getTotalTrajectCosts() > tempCourierCompanyRoute.getTotalTrajectCosts()) ? tempTrainRoute : tempCourierCompanyRoute;
                        if(finalizeOrder(bestRoute)){
                            return "confirmation.xhtml";
                        }
                    }else{
                        CourierTraject wholeTraject = calculateCourierCompanyRoute(senderAddress.getLocation(),deliveryAddress.getLocation());
                        List<CourierCompany> courierCompanies = repository.getAllCourierCompanies();
                        CourierCompany cheapestwholeTrajectCourier = new CourierCompany(99999,"temp");
                        for (CourierCompany courierCompany : courierCompanies) {
                            if(courierCompany.calcTotalTrajectPrice(wholeTraject) < cheapestwholeTrajectCourier.calcTotalTrajectPrice(wholeTraject)){
                                cheapestwholeTrajectCourier = courierCompany;
                            }
                        }
                        Route tempCourierCompanyRoute = new Route();
                        tempCourierCompanyRoute.addTraject(wholeTraject);
                        Route bestRoute = tempCourierCompanyRoute;
                        if(finalizeOrder(bestRoute)){
                            return "confirmation.xhtml";
                        }
                    }
                }

            } catch (JPAException e) {
                throw new RuntimeException(e);

            } catch (LocationUknownException e) {
                ControllerHelper.message(e.getMessage(), "sendPackageForm:submitOrder", "ERROR");
                return "";
            } catch (APIConnectionException e) {
                ControllerHelper.message(e.getMessage(), "sendPackageForm:submitOrder", "ERROR");
                return "";
            } catch (CalculateRouteException e) {
                ControllerHelper.message(e.getMessage(), "sendPackageForm:submitOrder", "ERROR");
                return "";
            }
        } catch (ValidationException e) {
            ControllerHelper.message(e.getMessage(), "sendPackageForm:submitOrder", "ERROR");
            return "";
        }
        return "";
    }

    private boolean finalizeOrder(Route calculatedRoute){
        tztOrder.addRoute(calculatedRoute);
        repository.insertRoute(calculatedRoute);

        repository.insertOrder(tztOrder);
        return  true;
    }

    private List<TrainTraject> calculateTrainCourierRoute(Location startPoint, Location endPoint) throws CalculateRouteException, LocationUknownException {
        List<TrainTraject> trainTrajects = new LinkedList<>();
        try {
            com.awesomesoft.tzt.service.GoogleMapsApi.models.Route Route = GoogleMapsApi.planRoute(startPoint, endPoint, "transit");
            List<Leg> legs = Route.getLegs();
            System.out.println(legs.size());
            List<CourierTraject> courierTrajects = new LinkedList<>();
            for (Leg leg : legs) {
                List<Step> steps = leg.getSteps();
                for (Step step : steps) {
                    if (step.getHtml_instructions().startsWith("Train")) {
                        TrainTraject trainTraject = new TrainTraject(step.getDistance().getValue()/1000,step.getDuration().getValue()/60,startPoint,endPoint);
                        trainTrajects.add(trainTraject);
                    }
                }
            }
            if (trainTrajects.size() > 0) {
                return trainTrajects;
            } else {
                return null;
            }
        } catch (GoogleMapsApiException e) {
            throw new RuntimeException(e);
        } catch (APIConnectionException e) {
            throw new RuntimeException(e);

        }
    }

    private CourierTraject calculateCourierCompanyRoute(Location startPoint, Location endPoint) throws CalculateRouteException, LocationUknownException {
        try {
            com.awesomesoft.tzt.service.GoogleMapsApi.models.Route route = GoogleMapsApi.planRoute(startPoint, endPoint, "driving");
            List<Leg> routeLegs = route.getLegs();
            for (Leg routeLeg : routeLegs) {
                CourierTraject courierTraject = new CourierTraject(routeLeg.getDistance().getValue() / 1000,routeLeg.getDuration().getValue()/60, startPoint, endPoint);
                return courierTraject;
            }
            throw new CalculateRouteException("No route found");
        } catch (APIConnectionException e) {
            throw new CalculateRouteException("Connection failed");
        } catch (GoogleMapsApiException e) {
            throw new CalculateRouteException("No route found");
        }
    }


    public String StatusVerzending() {
        return "test";

    }


    private Map<TrainCourier,TrainTraject> getTrainCouriersForRoute(Station nearestSenderStations, Station nearestDeliveryStations) {
        List<TrainCourier> trainCouriers = repository.getTrainCouriersWithPlanedRoutes();
        Map<TrainCourier,TrainTraject> trainCourierAndRouteMap = new LinkedHashMap<>();
        for (TrainCourier trainCourier : trainCouriers) {
            List<TrainTraject> trainTrajects = trainCourier.getPlanedTrajects();
            for (TrainTraject trainTraject : trainTrajects) {
                if(trainTraject.getStartPointStation().equals(nearestSenderStations)&&trainTraject.getEndPointStation().equals(nearestDeliveryStations)){
                    trainCourierAndRouteMap.put(trainCourier,trainTraject);
                }
            }
        }
        return trainCourierAndRouteMap;
    }


    public void verifyPersonLogIn(Person person) throws ValidationException {
        if(person == null)
            throw new ValidationException("Je bent niet ingelogt");
        if(!person.isAuthenticated()){
            throw new ValidationException("Je bent niet ingelogt");
        }
    }

}
