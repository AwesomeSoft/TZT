/*
 * Copyright (c) 2014.
 *
 * This software is copyrighted by the AwesomeSoft group.
 * Misuse of this software will be with concesquences
 */

package com.awesomesoft.tzt.service.domain;

import javax.persistence.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by Gerben de Heij
 */

@Entity// Dit zorgt ervoor dat het een entiteit word binnen de database
public class Person {

    @Id
    @GeneratedValue
    private Long id;// een entiteit heeft een ID nodig met deze anotatiets @id en @generated value

    private String firstName;
    private String lastName;

    private String emailAddress;

    private String password;

    @Transient
    private String confirmedPassword;

    private Date dateCreated;

    @Transient
    private URL activationUrl;

    private boolean activated;

    private Date lastLogin;
    private Date lockedOut;
    private int failedAttempts;
    private boolean authenticated;
    private boolean admin;


    public Person() {
        this.activated = false;
        this.admin = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmedPassword() {
        return confirmedPassword;
    }

    public void setConfirmedPassword(String confirmedPassword) {
        this.confirmedPassword = confirmedPassword;
    }

    public String getDateCreated() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm").format(dateCreated);
    }

    
    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getActivationUrl() {
        return activationUrl.toString();
    }

    public void setActivationUrl(URL activationUrl) {
        this.activationUrl = activationUrl;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        return id.equals(person.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public int getFailedAttempts() {
        return failedAttempts;
    }

    public void addFailedAttempt() {
        this.failedAttempts++;
    }
    
    public Date getLockedOut() {
        return lockedOut == null ? new Date() : lockedOut;
    }
    
    public void setLockedOut(Date lockedOut) {
        this.lockedOut = lockedOut;
    }

    public boolean isAuthenticated() {
        return authenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    public boolean isAdmin() {
        return admin;
    }
}
