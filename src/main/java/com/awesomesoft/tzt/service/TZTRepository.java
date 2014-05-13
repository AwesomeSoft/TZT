package com.awesomesoft.tzt.service;

import com.awesomesoft.tzt.service.domain.Person;

/**
 * Created by Gerben de Heij on 24/04/14.
 *
 * Interface to the repository for persistence of objects
 */

public interface TZTRepository {

    public Long insert(Person p);

    public Person getPersonById(Long id);

    public boolean checkPersonExistsById(Long id);

    public Person getPersonByEmailAddress(String emailAddress);

    public boolean checkPersonExistsByEmailAddress(String emailAddress);

    public void update(Person p);
}
