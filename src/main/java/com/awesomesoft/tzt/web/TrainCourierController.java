package com.awesomesoft.tzt.web;

import com.awesomesoft.tzt.service.TZTRepository;
import com.awesomesoft.tzt.service.domain.Person;
import com.awesomesoft.tzt.service.domain.Station;
import com.awesomesoft.tzt.service.domain.TrainCourier;
import com.awesomesoft.tzt.service.domain.TrainTraject;
import com.awesomesoft.tzt.service.exception.AuthenticationException;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gerben on 23-5-2014.
 */

 //zorgt ervoor dat de personcontroller in JSF beschikbaar is
@ManagedBean
@ViewScoped  //zorgt ervoor dat de personcontroller in JSF beschikbaar is
public class TrainCourierController{

    @Inject
    private TZTRepository repository;

    private List<Station> allStations = new ArrayList<>();

    private Station startPoint;
    private Station endPoint;

    private TrainTraject trainTraject;

    private TrainCourier trainCourier;

    /**
     * Initialise the TrainCourier object and the TrainTraject object for further use in the controller
     */
    @PostConstruct
    public void init() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();

        PersonController personController = (PersonController) externalContext.getSessionMap().get("personController");
        if(personController !=null){
            Person loggedInPerson = personController.getPerson();
            if(loggedInPerson.isActivated()){
                if(loggedInPerson.getRole() == 50){
                   trainCourier = repository.getTrainCourier(loggedInPerson.getId());
                }
            }
        }
        allStations = repository.getAllStations();
        if (trainTraject == null) {

            String stringId = externalContext.getRequestParameterMap().get("id");

            if (stringId != null) {
                stringId = stringId.replaceAll("[^0-9]","");
                Long id = stringId.length() > 0 ? Long.parseLong(stringId) : -1l;
                if (id != -1 && repository.checkTrainTrajectExist(id)) {
                    trainTraject = repository.getTrainTraject(id);
                   // this.startPoint = trainTraject.getStartPointStation();
                 //   this.endPoint = trainTraject.getEndPointStation();
                } else {
                    trainTraject = new TrainTraject();
                    ControllerHelper.redirect("No book found with id " + id);
                }
            }else {
                trainTraject = new TrainTraject();
            }
        }

    }

    public TrainTraject getTrainTraject() {
        return trainTraject;
    }

    public void setTrainTraject(TrainTraject trainTraject) {
        this.trainTraject = trainTraject;
    }

    /**
     * Auto completion function for the station list.
    */
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

    /**
     * Add a planedTraject to the TrainCourier with a start and end point given bu the user.
     * @throws AuthenticationException
     */
    public String addTraject() throws AuthenticationException{
        trainTraject.setStartPointStation(startPoint);
        trainTraject.setEndPointStation(endPoint);
        if(trainCourier==null){
            throw new AuthenticationException("Niet ingelogd als een treinkoerier");
        }
        trainTraject.planTraject(trainCourier);
        repository.updateTrainCourier(trainCourier);
        return "confirmation.xhtml";
    }

    /**
     * Deletes a trainTraject
     * @throws AuthenticationException
     */
    public String deleTraject() {
        if (this.trainCourier.isAuthenticated()) {
                    if(trainCourier.deletTraject(trainTraject)){
                        repository.updateTrainCourier(trainCourier);
                        return "/profile/index.xhtml";
                    }else{
                        ControllerHelper.redirect("Traject not found");
                    }
        } else {
            ControllerHelper.redirect("Person not authenticated");
        }
        return "";
    }

    /**
     * Change and update a trainTraject
     * @throws AuthenticationException
     */
    public String changeTraject() throws AuthenticationException{
        trainTraject.setStartPointStation(startPoint);
        trainTraject.setEndPointStation(endPoint);
        if(trainCourier==null){
            throw new AuthenticationException("Niet ingelogd als een treinkoerier");
        }
        trainTraject.planTraject(trainCourier);
        repository.updateTrainCourier(trainCourier);
        return "index.xhtml";
    }

    /**
     * Presents the current logged in trainCourier
     * @throws AuthenticationException
     */
    public TrainCourier getTrainCourier() throws AuthenticationException {
        if(trainCourier==null){
            throw new AuthenticationException("Niet ingelogd als een treinkoerier");
        }
        return trainCourier;
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