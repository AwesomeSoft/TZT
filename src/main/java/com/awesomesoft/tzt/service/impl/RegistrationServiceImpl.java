package com.awesomesoft.tzt.service.impl;

import com.awesomesoft.tzt.service.RegistrationService;
import com.awesomesoft.tzt.service.TZTRepository;
import com.awesomesoft.tzt.service.domain.User;

import javax.ejb.Singleton;
import javax.inject.Inject;


@Singleton
public class RegistrationServiceImpl implements RegistrationService {

    @Inject
    TZTRepository repository;

    public void registerSoul(String username, String email, String password) {
        User user = new User(username, email, password);
        repository.insert(user);
    }


}
