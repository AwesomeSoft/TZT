package com.awesomesoft.tzt.service.GoogleMapsApi;

import com.awesomesoft.tzt.service.GoogleMapsApi.models.GLocation;
import com.awesomesoft.tzt.service.GoogleMapsApi.models.Route;
import com.awesomesoft.tzt.service.domain.Location;
import com.awesomesoft.tzt.service.domain.Station;
import com.awesomesoft.tzt.service.exception.APIConnectionException;
import com.awesomesoft.tzt.service.exception.GoogleMapsApiException;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

/**
 * Created by student on 1/30/14.
 */
public abstract class GoogleMapsApi{

    static final String PUBLIC_API_KEY = "&key=AIzaSyA_ZLCYtmHm7J19eu41XhYOGWnDciEPJXU";
    static final String GOOGLE_DIRECTIONS_URL = "https://maps.googleapis.com/maps/api/directions/json?";
    static final String GOOGLE_GEOLOCATION_URL = "https://maps.googleapis.com/maps/api/geocode/json?";

    public static Route planRoute(Location startLocation, Location endLocation,String mode) throws APIConnectionException, GoogleMapsApiException {
        String routeOption = "&mode="+mode;
        String departureTime = "&departure_time="+ new Date().getTime()/1000;
        String routeString = "origin="+startLocation.toString()+"&destination="+endLocation.toString()+"&sensor=false";
        StringBuilder urlString = new StringBuilder();
        urlString.append(GOOGLE_DIRECTIONS_URL);
        urlString.append(routeString);
        urlString.append(PUBLIC_API_KEY);
        urlString.append(departureTime);
        urlString.append(routeOption);
        try {
                URL url = new URL(urlString.toString());
                String result = startHTTPSrequest(url);
                Route Route = JacksonObjectMapper.getRoute(result);
                return Route;
            } catch (MalformedURLException e) {
                StringBuilder errorMessage = new StringBuilder();
                errorMessage.append("Fatal Error in connecting to the URL: ");
                errorMessage.append(urlString.toString());
                throw new GoogleMapsApiException("Bad URL request");
            }
    }

    public static Route getTrainRoute(Station senderStation, Station receiverStation,Date departureTime) throws GoogleMapsApiException, APIConnectionException {

        String routeString = "origin="+senderStation.getLocation().getLat()+","+senderStation.getLocation().getLng()+"&destination="+receiverStation.getLocation().getLat()+","+receiverStation.getLocation().getLng()+"&sensor=false&mode=transit";
        StringBuilder urlString = new StringBuilder();
        String mode = "&mode=transit";
        String departureTimeString = "&departure_time="+departureTime.getTime()/10000;
        urlString.append(GOOGLE_DIRECTIONS_URL);
        urlString.append(routeString);
        urlString.append(PUBLIC_API_KEY);
        urlString.append(departureTimeString);
        urlString.append(mode);
        try {
            URL url = new URL(urlString.toString());
            String result = startHTTPSrequest(url);
            Route Route = JacksonObjectMapper.getRoute(result);
            return Route;
        } catch (MalformedURLException e) {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("Fatal Error in connecting to the URL: ");
            errorMessage.append(urlString.toString());
            throw new GoogleMapsApiException("Bad URL request");
        }

    }

    public static GLocation getLocation(String address) throws GoogleMapsApiException, APIConnectionException {
        String requestString = "address="+address;
        StringBuilder urlString = new StringBuilder();
        urlString.append(GOOGLE_GEOLOCATION_URL);
        urlString.append(requestString);
        urlString.append(PUBLIC_API_KEY);
        try {
            URL url = new URL(urlString.toString());
            String result = startHTTPSrequest(url);
            result = result.replace(" ","");
            GLocation GLocation = JacksonObjectMapper.getLocation(result);
            return GLocation;
        } catch (MalformedURLException e) {
            StringBuilder errorMessage = new StringBuilder();
            errorMessage.append("Fatal Error in connecting to the URL: ");
            errorMessage.append(urlString.toString());
            throw new GoogleMapsApiException("Bad URL request");
        }
    }

    public static String startHTTPSrequest(URL url) throws APIConnectionException {
        try{
              HttpsURLConnection con = openHTTPSconnection(url);
              if(con!=null){
                    BufferedReader br =
                            new BufferedReader(
                                    new InputStreamReader(con.getInputStream()));
                    String input;
                    StringBuilder jsonData = new StringBuilder();
                    while ((input = br.readLine()) != null){
                        jsonData.append(input);
                    }
                    br.close();
                    return jsonData.toString();
              }else{
                  throw new APIConnectionException("No connection");
              }
            } catch (IOException e) {
                throw new APIConnectionException("Connection error");
            }
    }

    public static HttpsURLConnection openHTTPSconnection(URL url) throws APIConnectionException {
        HttpsURLConnection con = null;
        try {
            con =(HttpsURLConnection)url.openConnection();
            return con;
        } catch (MalformedURLException e) {
            throw new APIConnectionException("Connection error");
        } catch (IOException e) {
            throw new APIConnectionException("Connection error");
        }
    }

}