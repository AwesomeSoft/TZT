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

   private String streetAndHouseNumber;
   private String zipdode;
   private String placeName;


   protected Address(){

   }

   public Address(String streetAndHouseNumber, String zipdode, String placeName) {
        this.streetAndHouseNumber = streetAndHouseNumber;
        this.zipdode = zipdode;
        this.placeName = placeName;
   }
}
