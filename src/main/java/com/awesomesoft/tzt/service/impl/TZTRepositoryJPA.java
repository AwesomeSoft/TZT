package com.awesomesoft.tzt.service.impl;

import com.awesomesoft.tzt.service.TZTRepository;
import com.awesomesoft.tzt.service.domain.*;
import com.awesomesoft.tzt.service.domain.Package;
import com.awesomesoft.tzt.service.exception.LocationUknownException;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Gerben de Heij on 24/04/14.
 */
@Singleton
public class TZTRepositoryJPA implements TZTRepository {

    public void updatePerson(Person p) {
        em.merge(p);
    }

    @PersistenceContext(name = "TZTDataManagement")
    private EntityManager em; // Dit is je entity manager. Deze bewaakt je entiteiten en slaat ze op
    //Insert User object

    public Long insertPerson(Person p) {
        em.persist(p); // de persist functie van de entity manager zorgt ervoor dat deze bestaat.
        return p.getId();
    }

    public Person getPersonById(Long id) {
        return em.find(Person.class, id);
    }

    public boolean checkPersonExistsById(Long id) {
        String jpql = "select count(p) from Person p where id = ?1";
        TypedQuery<Long> q = em.createQuery(jpql, Long.class);
        q.setParameter(1, id);
        return q.getSingleResult() > 0;
    }

    public Person getPersonByEmailAddress(String emailAddress) {
        String jpql = "select p from Person p where emailAddress = ?1";
        TypedQuery<Person> q = em.createQuery(jpql, Person.class);
        q.setParameter(1, emailAddress);
        return q.getSingleResult();
    }


    public List<Station> getAllStations() {
        String jpql = "select S from Station s";
        TypedQuery<Station> q = em.createQuery(jpql, Station.class);
        return q.getResultList();
    }

    @Override
    public TrainCourier getTrainCourier(Long id) {
        return em.find(TrainCourier.class,id);
    }

    @Override
    public void updateTrainCourier(TrainCourier trainCourier) {
        em.merge(trainCourier);
    }

    public Long insertTrainStation(Station station) {
        em.merge(station);
        return station.getId();
    }

    @Override
    public boolean checkTrainTrajectExist(Long id) {
        String jpql = "select count(t) from TrainTraject t where id = ?1";
        TypedQuery<Long> q = em.createQuery(jpql, Long.class);
        q.setParameter(1, id);
        return q.getSingleResult() > 0;
    }

    @Override
    public TrainTraject getTrainTraject(Long id) {
        return em.find(TrainTraject.class,id);
    }



    public boolean checkPersonExistsByEmailAddress(String emailAddress) {
        String jpql = "select count(p) from Person p where emailAddress = ?1";
        TypedQuery<Long> q = em.createQuery(jpql, Long.class);
        q.setParameter(1, emailAddress);
        return q.getSingleResult() > 0;
    }

    public List<TrainCourier> getTrainCouriersWithPlanedRoutes() {
        String jpql = "select t from TrainCourier t where size(t.planedTrajects) > 0";
        TypedQuery<TrainCourier> q = em.createQuery(jpql, TrainCourier.class);
        return q.getResultList();
    }


    public LinkedList<Station> getNearestStations(Location loc) throws JPAException, LocationUknownException {
        if(loc == null){
            throw new LocationUknownException("There is no known location");
        }
        String jpql = "select ((((acos(sin(("+loc.getLat()+"*pi()/180)) * sin((l.lat*pi()/180))+cos(("+loc.getLat()+"*pi()/180))" +
                "   * cos((l.lat*pi()/180)) * cos((("+loc.getLng()+" - l.lng)*pi()/180))))*180/pi())*60*1.1515)*1.609344)" +
                " AS kilometer, s from Station s JOIN s.location l ORDER BY kilometer ASC ";

        TypedQuery<Object[]> q = em.createQuery(jpql,Object[].class);
        @SuppressWarnings("Query results are Objec[] its safe to use")
        List<Object[]> result1 = q.getResultList();

        LinkedList<Station> stationList = new LinkedList<Station>();
                for(Object[] resultElement : result1) {
                        if(resultElement[1] instanceof Station) {
                            //Now we no it is a station and it is save to cast the class
                            stationList.add((Station) resultElement[1]);
                            return stationList;
                        }else{
                            throw new JPAException("Not a station at all");
                        }
                }
        return stationList;
    }

    public Long insertOrder(TZTOrder o) {
        em.persist(o); // de persist functie van de entity manager zorgt ervoor dat deze bestaat.
        return o.getId();
    }

    public TZTOrder getOrderById(Long id) {
        return em.find(TZTOrder.class,id);
    }

    /* Zoek een order op basis van Postcode en Ordernr*/
    public TZTOrder getOrderByPostalCodeOrdernr(Long orderNumber, String postalCode) throws JPAException, NoResultException {
        String jpql = "select O from TZTOrder O JOIN O.receiver person JOIN person.address address WHERE O.orderNumber  = :orderNumber AND address.postalCode = :postalcode";
        TypedQuery<TZTOrder> q = em.createQuery(jpql, TZTOrder.class);
        q.setParameter("orderNumber", orderNumber);
        q.setParameter("postalcode", postalCode);
        if(q.getResultList().isEmpty()){
            throw new NoResultException("Geen order gevonden!");
        }
        return q.getSingleResult();
    }

    public Long insertPackage(Package p) {
        em.persist(p); // de persist functie van de entity manager zorgt ervoor dat deze bestaat.
        return p.getId();
    }

    public Long insertAddress(Address a) {
        em.persist(a);
        return a.getId();
    }

    public Address getAddressById(Long id) {
        return em.find(Address.class,id);
    }

    @Override
    public Long insertTrainCourier(TrainCourier t) {
        em.persist(t);
        return t.getId();
    }

    @Override
    public TZTOrder findOrder(Long id) {
        return em.find(TZTOrder.class,id);
    }

    @Override
    public void updateTZTOrder(TZTOrder tztOrder) {
        em.merge(tztOrder);
    }

    /* Lijst met gebruikers ophalen*/
    @Override
    public List<Person> listUsersAdminView(){
            String jpql = "select p from Person p ORDER BY id ASC";
            TypedQuery<Person> q = em.createQuery(jpql, Person.class);
            return q.getResultList();
    }
}