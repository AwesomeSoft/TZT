package com.awesomesoft.tzt.service;

import com.awesomesoft.tzt.service.domain.Location;
import com.awesomesoft.tzt.service.domain.Person;
import com.awesomesoft.tzt.service.domain.Station;
import com.awesomesoft.tzt.service.domain.TZTOrder;
import com.awesomesoft.tzt.service.impl.JPAException;

import java.util.LinkedList;

/**
 * Created by Gerben de Heij on 24/04/14.
 *
 * Interface to the repository for persistence of objects
 */

public interface TZTRepository {

    public Long insertPerson(Person p);

    public Person getPersonById(Long id);

    public boolean checkPersonExistsById(Long id);

    public Person getPersonByEmailAddress(String emailAddress);

    public boolean checkPersonExistsByEmailAddress(String emailAddress);

    public void updatePerson(Person p);

    public LinkedList<Station> getNearestStations(Location loc) throws JPAException;

    public Long insertOrder(TZTOrder o);

    public TZTOrder getOrderById(Long id);
}
