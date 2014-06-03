package com.awesomesoft.tzt.service.GoogleMapsApi;

import com.awesomesoft.tzt.service.GoogleMapsApi.exceptions.RouteNotFoundException;
import com.awesomesoft.tzt.service.GoogleMapsApi.models.*;

import com.awesomesoft.tzt.service.exception.LocationUknownException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * Created by student on 5/20/14.
 */
public abstract class JacksonObjectMapper {

    public static Route getRoute(String jsonResult) throws LocationUknownException {
         //create ObjectMapper instance
        if(jsonResult.contains("ZERO_RESULTS")){
            throw new LocationUknownException("Het adres is onbekend");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //convert json string to object
        try {
             Routes routes = objectMapper.readValue(jsonResult,Routes.class);
            try {
                routes.getRoute().toString();
                return routes.getRoute();

            } catch (RouteNotFoundException e) {
                throw new RuntimeException(e);

            }
        } catch (IOException e) {
             throw new RuntimeException(e);
        }
    }

    public static GLocation getLocation(String jsonResult) throws LocationUknownException{
        //create ObjectMapper instance
        if(jsonResult.contains("ZERO_RESULTS")){
            throw new LocationUknownException("Het adres is onbekend");
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //convert json string to object
        try {

            GeoLocation geoLocation = objectMapper.readValue(jsonResult,GeoLocation.class);
            List<Result> results = geoLocation.getResults();

            return results.get(0).getGeometry().getGLocation();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}