package com.awesomesoft.tzt.web;

import java.util.Date;

/**
 * Created by Gerben on 26-5-2014.
 */
public class PersonInfo {

    private AddressInfo addressInfo;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String password;
    private String confirmedPassword;
    private Date dateCreated;

    private String telePhone;
    private int role;
    private String dateofBirth;
    private String iban;
    private String tenaamstelling;

    public PersonInfo() {
        if(this.addressInfo == null){
            this.addressInfo = new AddressInfo();
        }

    }

    public AddressInfo getAddressInfo() {
        return addressInfo;
    }

    public void setAddressInfo(AddressInfo addressInfo) {
        this.addressInfo = addressInfo;
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

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
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

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getTenaamstelling() {
        return tenaamstelling;
    }

    public void setTenaamstelling(String tenaamstelling) {
        this.tenaamstelling = tenaamstelling;
    }
}
