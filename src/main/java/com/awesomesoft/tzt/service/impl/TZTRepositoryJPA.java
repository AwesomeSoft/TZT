package com.awesomesoft.tzt.service.impl;

import com.awesomesoft.tzt.service.TZTRepository;
import com.awesomesoft.tzt.service.domain.Person;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 * Created by Gerben de Heij on 24/04/14.
 */
@Singleton
public class TZTRepositoryJPA implements TZTRepository {

    public void update(Person p) {
        em.merge(p);
    }

    @PersistenceContext(name = "TZTDataManagement")
    private EntityManager em; // Dit is je entity manager. Deze bewaakt je entiteiten en slaat ze op
    //Insert User object

    public Long insert(Person p) {
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

    

}
