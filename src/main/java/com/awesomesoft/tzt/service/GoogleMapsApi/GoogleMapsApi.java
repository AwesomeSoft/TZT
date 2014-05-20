package com.awesomesoft.tzt.service.GoogleMapsApi;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by student on 1/30/14.
 */
public abstract class GoogleMapsApi{


    static final String PUBLIC_API_KEY = "&key=AIzaSyA_ZLCYtmHm7J19eu41XhYOGWnDciEPJXU";
    static final String GOOGLE_DIRECTIONS_URL = " https://maps.googleapis.com/maps/api/directions/json?";

    public static void planRoute(String senderAddress, String receiverAddress){
        String route = "origin="+senderAddress+"&destination="+receiverAddress+"&sensor=false";
        try {
            URL url = new URL(GOOGLE_DIRECTIONS_URL+route+PUBLIC_API_KEY);
            String result = startHTTPSrequest(url);
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
                JacksonObjectMapper.getLocation(jsonData.toString());
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