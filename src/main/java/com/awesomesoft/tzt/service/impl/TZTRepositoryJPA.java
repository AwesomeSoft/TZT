package com.awesomesoft.tzt.service.impl;

import com.awesomesoft.tzt.service.TZTRepository;
import com.awesomesoft.tzt.service.domain.User;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Gerben de Heij on 24/04/14.
 */
@Singleton
public class TZTRepositoryJPA implements TZTRepository {

    @PersistenceContext(name = "StudentManagement")
    private EntityManager em;


    @Override
    public long insert(User user) {
        em.persist(user);
        return user.getId();
    }

}
