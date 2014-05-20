package com.awesomesoft.tzt.service.GoogleMapsApi;

import com.awesomesoft.tzt.service.GoogleMapsApi.models.Location;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by student on 5/20/14.
 */
public abstract class JacksonObjectMapper {

    public static void getLocation(String jsonResult){
         //create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        //convert json string to object
        try {
             Location loc = objectMapper.readValue(jsonResult,Location.class);
             System.out.println("Employee Object\n"+loc);
        } catch (IOException e) {
             throw new RuntimeException(e);
        }
    }
}