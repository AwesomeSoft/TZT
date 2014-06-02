package com.awesomesoft.tzt.service;

import com.awesomesoft.tzt.service.domain.*;
import com.awesomesoft.tzt.service.domain.Package;
import com.awesomesoft.tzt.service.exception.LocationUknownException;
import com.awesomesoft.tzt.service.impl.JPAException;

import javax.persistence.NoResultException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Gerben de Heij on 24/04/14.
 *
 * Interface to the repository for persistence of objects
 */

public interface TZTRepository {

    Long insertPerson(Person p);

    Person getPersonById(Long id);

    boolean checkPersonExistsById(Long id);

    Person getPersonByEmailAddress(String emailAddress);

    boolean checkPersonExistsByEmailAddress(String emailAddress);

    void updatePerson(Person p);

    LinkedList<Station> getNearestStations(Location loc) throws JPAException, LocationUknownException;

    Long insertOrder(TZTOrder o);

    TZTOrder getOrderById(Long id);

    TZTOrder getOrderByPostalCodeOrdernr(Long orderNumber, String postalCode) throws JPAException, NoResultException;

    Long insertPackage(Package p);

    Long insertAddress(Address a);

    Address getAddressById(Long id);

    Long insertTrainCourier(TrainCourier trainCourier);


    TZTOrder findOrder(Long id);

    void updateTZTOrder(TZTOrder tztOrder);

    List<Station> getAllStations();


    TrainCourier getTrainCourier(Long id);

    void updateTrainCourier(TrainCourier trainCourier);

    Long insertTrainStation(Station station);

    boolean checkTrainTrajectExist(Long id);

    TrainTraject getTrainTraject(Long id);

    List<TrainCourier> getTrainCouriersWithPlanedRoutes();

    List<Person> listUsersAdminView();

}
