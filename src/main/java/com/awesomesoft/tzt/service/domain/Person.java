/*
 * Copyright (c) 2014.
 *
 * This software is copyrighted by the AwesomeSoft group.
 * Misuse of this software will be with concesquences
 */

package com.awesomesoft.tzt.service.domain;

import org.hibernate.type.DateType;

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
    //Addded by Erwin
    private String address;
    private String houseNumber;
    private String postalCode;
    private String town;
    private String telePhone;
    private int role;
    private String dateofBirth;
    //Addded by Erwin


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

    //Addded by Erwin
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getDateofBirth() {
        return dateofBirth;
    }

    public void setDateofBirth(String dateofBirth) {
        this.dateofBirth = dateofBirth;
    }


    //Addded by Erwin


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
