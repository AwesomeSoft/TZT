package com.awesomesoft.tzt.service;

import com.awesomesoft.tzt.service.domain.User;

/**
 * Created by Gerben de Heij on 24/04/14.
 *
 * Interface to the repository for persistence of objects
 */

public interface TZTRepository {

    //Insert User object
    long insert(User user);

}
