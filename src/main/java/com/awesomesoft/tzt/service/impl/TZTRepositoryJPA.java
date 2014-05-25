package com.awesomesoft.tzt.service.impl;

import com.awesomesoft.tzt.service.TZTRepository;
import com.awesomesoft.tzt.service.domain.*;
import com.awesomesoft.tzt.service.domain.Package;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
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

    public boolean checkPersonExistsByEmailAddress(String emailAddress) {
        String jpql = "select count(p) from Person p where emailAddress = ?1";
        TypedQuery<Long> q = em.createQuery(jpql, Long.class);
        q.setParameter(1, emailAddress);
        return q.getSingleResult() > 0;
    }

    public LinkedList<Station> getNearestStations(Location loc) throws JPAException{

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

    public Long insertPackage(Package p) {
        em.persist(p); // de persist functie van de entity manager zorgt ervoor dat deze bestaat.
        return p.getId();
    }


}