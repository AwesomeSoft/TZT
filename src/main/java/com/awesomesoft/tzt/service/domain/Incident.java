package com.awesomesoft.tzt.service.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by Erwin on 24-5-2014.
 */
@Entity
public class Incident {
    @Id
    @GeneratedValue
    private Long id;// een entiteit heeft een ID nodig met deze anotatiets @id en @generated value

    private int priority;
    private int status;
    private Date creationDate;
    private Time creationTime;
    private String title;
    private String subject;

    protected Incident(){

    }
}
