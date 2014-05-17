package com.awesomesoft.tzt.service.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by student on 5/13/14.
 */
@Entity
public class Location {

    @Id
    @GeneratedValue
    private long id;

    public Location() {
    }
}
