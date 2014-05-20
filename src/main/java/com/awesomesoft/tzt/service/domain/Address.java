package com.awesomesoft.tzt.service.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

/**
 * Created by student on 5/15/14.
 */
@Entity
public class Address {

   @javax.persistence.Id
   @GeneratedValue
   private Long id;

   private String street;
   private String houseNumber;
   private String postalCode;
   private String town;

   public Address(){

   }

   public Address(String street, String houseNumber, String postalCode, String town){
       this.street = street;
       this.houseNumber = houseNumber;
       this.postalCode = postalCode;
       this.town = town;
   }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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

    public Long getId() {
        return id;
    }

    public void setId(long id) {
       this.id = id;
    }
}
