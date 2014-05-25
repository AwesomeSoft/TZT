package com.awesomesoft.tzt.service.GoogleMapsApi;

import com.awesomesoft.tzt.service.GoogleMapsApi.models.GLocation;
import com.awesomesoft.tzt.service.GoogleMapsApi.models.Leg;
import com.awesomesoft.tzt.service.GoogleMapsApi.models.Route;
import com.awesomesoft.tzt.service.domain.Station;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * Created by student on 1/30/14.
 */
public abstract class GoogleMapsApi{

    static final String PUBLIC_API_KEY = "&key=AIzaSyA_ZLCYtmHm7J19eu41XhYOGWnDciEPJXU";
    static final String GOOGLE_DIRECTIONS_URL = "https://maps.googleapis.com/maps/api/directions/json?";
    static final String GOOGLE_GEOLOCATION_URL = "https://maps.googleapis.com/maps/api/geocode/json?";

    public static Route planRoute(String senderAddress, String receiverAddress){
        String routeString = "origin="+senderAddress+"&destination="+receiverAddress+"&sensor=false";
        try {
            URL url = new URL(GOOGLE_DIRECTIONS_URL+routeString+PUBLIC_API_KEY);
            String result = startHTTPSrequest(url);
            Route route = JacksonObjectMapper.getRoute(result);
            List<Leg> legs = route.getLegs();
            System.out.println(legs.size());
            for (Leg leg : legs) {
                System.out.println(leg.getDistance().toString());
            }

        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public static Route getTrainRoute(Station senderStation, Station receiverStation,Date departureTime){
        String routeString = "origin="+senderStation.getLocation().getLat()+","+senderStation.getLocation().getLng()+"&destination="+receiverStation.getLocation().getLat()+","+receiverStation.getLocation().getLng()+"&sensor=false&mode=transit";
        try {
            String mode = "&mode=transit";
            String departureTimeString = "&departure_time="+departureTime.getTime()/10000;
            URL url = new URL(GOOGLE_DIRECTIONS_URL+routeString+PUBLIC_API_KEY+departureTimeString+mode);
            System.out.println(GOOGLE_DIRECTIONS_URL+routeString+PUBLIC_API_KEY+departureTimeString+mode);
            String result = startHTTPSrequest(url);
            Route route = JacksonObjectMapper.getRoute(result);
            return route;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

    }

    public static GLocation getLocation(String address){
        String requestString = "address="+address;
        try {
            URL url = new URL(GOOGLE_GEOLOCATION_URL+requestString+PUBLIC_API_KEY);
            System.out.println("Used request: "+GOOGLE_GEOLOCATION_URL+requestString+PUBLIC_API_KEY);
            String result = startHTTPSrequest(url);
            result = result.replace(" ","");
            GLocation GLocation = JacksonObjectMapper.getLocation(result);

            return GLocation;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static String startHTTPSrequest(URL url){
        HttpsURLConnection con = openHTTPSconnection(url);
        if(con!=null){
            try {
                System.out.println("****** Content of the URL ********");
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
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return"";
    }

    public static HttpsURLConnection openHTTPSconnection(URL url){
        HttpsURLConnection con = null;
        try {
             con = (HttpsURLConnection)url.openConnection();
            return con;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //never gets executed
        return  con;
    }

}