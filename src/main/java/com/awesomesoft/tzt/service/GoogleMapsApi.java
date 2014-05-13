package com.awesomesoft.tzt.service;


import com.awesomesoft.tzt.service.domain.Location;
import com.awesomesoft.tzt.service.exception.GoogleMapsApiException;
import com.google.api.client.json.JsonFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * Created by student on 1/30/14.
 */
public abstract class GoogleMapsApi {

    private static final Logger logger = LoggerFactory.getLogger(GoogleMapsApi.class);

    public static Location getLocation(JsonFactory jsonFactory, String adress) throws GoogleMapsApiException {
      //todo fix location
        return new Location();
    }
 }