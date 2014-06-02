/*
 * Copyright (c) 2014.
 *
 * This software is copyrighted by the AwesomeSoft group.
 * Misuse of this software will be with concesquences
 */

package com.awesomesoft.tzt.service.domain;

import com.awesomesoft.tzt.web.PersonInfo;

import javax.persistence.*;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Entity// Dit zorgt ervoor dat het een entiteit word binnen de database
@Inheritance(strategy=InheritanceType.JOINED)
@DiscriminatorColumn(name = "PERSON_TYPE")
@DiscriminatorValue("P")
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade={CascadeType.ALL})
    private Address address;


    private String firstName;
    private String lastName;

    private String emailAddress;

    private String password;

    @Transient
    private String confirmedPassword;

    private Date dateCreated;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<TZTOrder> orderHistory = new LinkedList<>();


    @Transient
    private String salutation;

    @Transient
    private URL activationUrl;

    private boolean activated;

    private Date lastLogin;
    private Date lockedOut;
    private int failedAttempts;
    private boolean authenticated;
    private boolean admin;

    //Addded by Erwin
    private String telePhone;
    private int role;
    private Date dateofBirth;
    private String iban;
    private String tenaamstelling;
    /* Afzender */
    //Addded by Erwin

    public Person() {
        this.activated = false;
        this.admin = false;
        if(this.address == null){
            this.address = new Address();
        }


    }

    public Person(PersonInfo personInfo){
         this.address = new Address(personInfo.getAddressInfo());
        this.firstName = personInfo.getFirstName();
        this.lastName = personInfo.getLastName();
        this.emailAddress = personInfo.getEmailAddress();
        this.password = personInfo.getPassword();
        this.confirmedPassword = personInfo.getConfirmedPassword();
        this.dateCreated = new Date();

        this.telePhone = personInfo.getTelePhone();
        this.role = personInfo.getRole();
        setDateofBirth(personInfo.getDateofBirth());
        this.iban = personInfo.getIban();
        this.tenaamstelling = personInfo.getTenaamstelling();
    }
    public String getSalutation() {
        return salutation;
    }

    public void setSalutation(String salutation) {
        this.salutation = salutation;
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

    //Addded by Gerben
    public Address getAddress() {
        return address;
    }

    public void setAddress(String street, String houseNumber, String postalCode, String town ){
        this.address = new Address(street,houseNumber, postalCode,town);
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }

    public int getRole() {
        if(isAuthenticated())
            return role;
        return 0;
    }


    public int getRole(boolean admin) {
        if(admin) {
            return role;
        }else {
            return 0;
        }
    }


    public void setRole(int role) {
        this.role = role;
    }

    public String getDateofBirth() {
        if(dateofBirth!=null){
            DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
            return df.format(dateofBirth);
        }
        return "";
    }

    public void setDateofBirth(String dateofBirth) {
        if(dateofBirth != null){
            try {
                this.dateofBirth = new SimpleDateFormat("dd-mm-yyyy", Locale.ENGLISH).parse(dateofBirth);
            } catch (ParseException e) {
                throw new RuntimeException(e);

            }
        }
    }

    public String getIban(){
        return iban;
    };
    public void setIban(String iban){
        this.iban = iban;
    };
    public String getTenaamstelling(){
        return tenaamstelling;
    }
    public void setTenaamstelling(String tenaamstelling){
        this.tenaamstelling = tenaamstelling;
    }
    //Addded by Erwin


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;

        Person person = (Person) o;

        if (emailAddress != null ? !emailAddress.equals(person.emailAddress) : person.emailAddress != null)
            return false;
        if (firstName != null ? !firstName.equals(person.firstName) : person.firstName != null) return false;
        if (lastName != null ? !lastName.equals(person.lastName) : person.lastName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        return result;
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

    public void _own(TZTOrder order) {
        orderHistory.add(order);
    }

    @OneToOne(mappedBy = "receiver")
    private TZTOrder tztOrder;



}