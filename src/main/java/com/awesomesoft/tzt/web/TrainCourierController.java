package com.awesomesoft.tzt.web;

import com.awesomesoft.tzt.service.TZTRepository;
import com.awesomesoft.tzt.service.domain.Person;
import com.awesomesoft.tzt.service.domain.Station;
import com.awesomesoft.tzt.service.domain.TrainCourier;
import com.awesomesoft.tzt.service.domain.TrainTraject;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Erwin on 22-5-2014.
 */

 //zorgt ervoor dat de personcontroller in JSF beschikbaar is
@ManagedBean
@SessionScoped  //zorgt ervoor dat de personcontroller in JSF beschikbaar is
public class TrainCourierController{

    @Inject
    private TZTRepository repository;

    private List<Station> allStations = new ArrayList<>();

    private Station startPoint;
    private Station endPoint;

    private TrainTraject trainTraject;

    private TrainCourier trainCourier;


    @PostConstruct
    public void init(FacesContext fc) {
        allStations = repository.getAllStations();
        trainTraject = new TrainTraject();
    }

    public TrainTraject getTrainTraject() {
        return trainTraject;
    }

    public void setTrainTraject(TrainTraject trainTraject) {
        this.trainTraject = trainTraject;
    }

    public List<Station> completeStation(String query) {
        List<Station> filteredStations = new ArrayList<Station>();

        for (int i = 0; i < allStations.size(); i++) {
            Station station = allStations.get(i);
            if(station.getName().toLowerCase().startsWith(query.toLowerCase())) {
                filteredStations.add(station);
            }
        }

        return filteredStations;
    }

    public String addTraject(Person person){
        trainTraject.setStartPoint(startPoint.getLocation());
        trainTraject.setEndPoint(endPoint.getLocation());
        if(this.trainCourier == null){
            this.trainCourier = repository.getTrainCourier(person.getId());
        }
        this.trainCourier.addTraject(trainTraject);
        repository.updateTrainCourier(trainCourier);
        return "confirmation.xhtml";
    }

    public TrainCourier getTrainCourier(Person person){
        if(this.trainCourier == null){

        }
        return this.trainCourier;
    }
    public Station getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Station startPoint) {
        this.startPoint = startPoint;
    }

    public Station getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Station endPoint) {
        this.endPoint = endPoint;
    }

    public List<Station> getAllStatins() {
        return allStations;
    }


}