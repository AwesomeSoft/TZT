package com.awesomesoft.tzt.service.domain;

import com.awesomesoft.tzt.service.GoogleMapsApi.GoogleMapsApi;
import com.awesomesoft.tzt.service.GoogleMapsApi.models.GLocation;
import com.awesomesoft.tzt.service.exception.APIConnectionException;
import com.awesomesoft.tzt.service.exception.GoogleMapsApiException;
import com.awesomesoft.tzt.service.exception.LocationUknownException;
import com.awesomesoft.tzt.web.AddressInfo;

import javax.persistence.*;

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


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Location location;


    public Address(){

    }

    public Address(String street, String houseNumber, String postalCode, String town) throws LocationUknownException, APIConnectionException {
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.town = town;
        this.location = setLocation();
    }

    public Address(AddressInfo addressInfo) throws LocationUknownException, APIConnectionException {
        this.street = addressInfo.getStreet();
        this.houseNumber = addressInfo.getHouseNumber();
        this.postalCode = addressInfo.getPostalCode();
        this.town = addressInfo.getTown();
        this.location = setLocation();
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

    public Location getLocation() throws LocationUknownException, APIConnectionException {
        if(location == null){
            try {
                GLocation googleLocation = null;
                googleLocation = GoogleMapsApi.getLocation(street + houseNumber + town);
                this.location = new Location(googleLocation.getLng(),googleLocation.getLat());
                location.setAddress(this);
                return this.location;
            } catch (GoogleMapsApiException e) {
                throw new LocationUknownException("Het adres is niet gevonden ");
            }
        }else{
          return location;
        }
    }

    private Location setLocation() throws LocationUknownException, APIConnectionException {
        try {
            GLocation googleLocation = null;
            googleLocation = GoogleMapsApi.getLocation(street + houseNumber + town);
            this.location = new Location(googleLocation.getLng(),googleLocation.getLat());
            location.setAddress(this);
            return this.location;
        } catch (GoogleMapsApiException e) {
            throw new LocationUknownException("Het adres is niet gevonden ");
        }
    }
}
