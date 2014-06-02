package com.awesomesoft.tzt.web;
/**
 * Created by Gerben on 17-5-2014.
 */

import com.awesomesoft.tzt.service.GoogleMapsApi.GoogleMapsApi;
import com.awesomesoft.tzt.service.GoogleMapsApi.models.Leg;
import com.awesomesoft.tzt.service.GoogleMapsApi.models.Step;
import com.awesomesoft.tzt.service.TZTRepository;
import com.awesomesoft.tzt.service.domain.*;
import com.awesomesoft.tzt.service.exception.APIConnectionException;
import com.awesomesoft.tzt.service.exception.CalculateRouteException;
import com.awesomesoft.tzt.service.exception.GoogleMapsApiException;
import com.awesomesoft.tzt.service.exception.LocationUknownException;
import com.awesomesoft.tzt.service.impl.JPAException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import java.util.LinkedList;
import java.util.List;

import static com.awesomesoft.tzt.web.ControllerHelper.message;


@ManagedBean  //zorgt ervoor dat de personcontroller in JSF beschikbaar is
@SessionScoped
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Inject
    TZTRepository repository;


    private Person receiver;

    private Long orderNumberInfo;
    private String postalCodeInfo;
    private TZTOrder tztOrderInfo;

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

    public TZTOrder getTztOrderInfo(){
        return tztOrderInfo;
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
        tztOrder.setReceiver(this.receiver);
        tztOrder.setCustomer(person);
        repository.updatePerson(person);
        Route finalRoute = new Route();
        try {
            Address senderAddress = person.getAddress();
            Address deliveryAddress = receiver.getAddress();
            Station nearestSenderStations = repository.getNearestStations(senderAddress.getLocation()).get(0);
            Station nearestDeliveryStations = repository.getNearestStations(deliveryAddress.getLocation()).get(0);
            if (foundTrainCourier(nearestSenderStations, nearestDeliveryStations)) {
            }
            Long id = Long.parseLong("1150");
            TZTOrder tztOrder = repository.findOrder(id);
            tztOrder.addRoute(finalRoute);
            repository.updateTZTOrder(tztOrder);

        } catch (JPAException e) {
            throw new RuntimeException(e);

        } catch (LocationUknownException e) {
            message(e.getMessage(), "sendPackageForm:submitOrder", "ERROR");
            return "";
        } catch (APIConnectionException e) {
            message(e.getMessage(), "sendPackageForm:submitOrder", "ERROR");
            return "";
        }

        Long id = repository.insertOrder(tztOrder);
        return "confirmation.xhtml";
    }

    private List<TrainTraject> calculateTrainCourierRoute(Location startPoint, Location endPoint) throws CalculateRouteException {
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
                        TrainTraject trainTraject = new TrainTraject(120.0, 33.0, 2.0, 10, 123123, startPoint, endPoint);
                        trainTrajects.add(trainTraject);
                    }
                }
            }
            if (trainTrajects.size() > 0) {
                return trainTrajects;
            } else {
                throw new CalculateRouteException("No route found ");
            }
        } catch (GoogleMapsApiException e) {
            throw new RuntimeException(e);
        } catch (APIConnectionException e) {
            throw new RuntimeException(e);

        }
    }

    private CourierTraject calculateCourierCompanyRoute(Location startPoint, Location endPoint) throws CalculateRouteException {
        try {
            com.awesomesoft.tzt.service.GoogleMapsApi.models.Route RouteFromStation = GoogleMapsApi.planRoute(startPoint, endPoint, "driving");
            List<Leg> routeFromStationLegs = RouteFromStation.getLegs();
            for (Leg routeFromStationLeg : routeFromStationLegs) {
                CourierTraject courierTraject = new CourierTraject(routeFromStationLeg.getDistance().getValue() / 1.609344, 12, 12, 12, 12, startPoint, endPoint);
                return courierTraject;
            }
            throw new CalculateRouteException("No route found");
        } catch (APIConnectionException e) {
            throw new CalculateRouteException("Connection failed");
        } catch (GoogleMapsApiException e) {
            throw new CalculateRouteException("No route found");
        }
    }

/* Controleer het ordernummer+postcode en geef bij resultaat een pagina terug met de resultaten */
 public String StatusVerzending() {
     try {
         this.tztOrderInfo = repository.getOrderByPostalCodeOrdernr(orderNumberInfo, postalCodeInfo);
         return "ResultStatusVerzending.xhtml";
     }catch (NoResultException e) {
         ControllerHelper.message(e.getMessage(), "statuszendingForm:submitStatusOpenstaandeZending", "ERROR");
         return "";
     }catch (Exception e) {
         ControllerHelper.message(e.getMessage(), "statuszendingForm:submitStatusOpenstaandeZending", "ERROR");
             return "";
     }
     }


    private boolean foundTrainCourier(Station nearestSenderStations, Station nearestDeliveryStations) {
        List<TrainCourier> trainCouriers = repository.getTrainCouriersWithPlanedRoutes();
        for (TrainCourier trainCourier : trainCouriers) {
            List<TrainTraject> trainTrajects = trainCourier.getPlanedTrajects();
            for (TrainTraject trainTraject : trainTrajects) {
                if(trainTraject.getStartPointStation().equals(nearestSenderStations)&&trainTraject.getEndPointStation().equals(nearestDeliveryStations)){
                    return true;
                }
            }
        }
        return false;
    }

    public String getPostalCodeInfo() {
        return postalCodeInfo;
    }

    public void setPostalCodeInfo(String postalCodeInfo) {
        this.postalCodeInfo = postalCodeInfo;
    }

    public Long getOrderNumberInfo() {
        return orderNumberInfo;
    }

    public void setOrderNumberInfo(Long orderNumberInfo) {
        this.orderNumberInfo = orderNumberInfo;
    }
}
