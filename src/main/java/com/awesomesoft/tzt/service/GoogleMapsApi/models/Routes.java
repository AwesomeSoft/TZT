package com.awesomesoft.tzt.service.GoogleMapsApi.models;

import com.awesomesoft.tzt.service.GoogleMapsApi.exceptions.RouteNotFoundException;

/**
 * Created by student on 5/20/14.
 */
public class Routes {

    GRoute[] GRoutes;

    public GRoute[] getGRoutes() {
        return GRoutes;
    }

    public void setGRoutes(GRoute[] GRoutes) {
        this.GRoutes = GRoutes;
    }

    @Override
    public String toString() {
        return "Routes{" +
                "GRoutes=" + GRoutes[0].toString() +
                '}';
    }

    //Return the first route
    public GRoute getRoute() throws RouteNotFoundException{
        if(GRoutes.length>0){
            return GRoutes[0];
        }else{
            throw new RouteNotFoundException("Error: Your route is not found");
        }
    }

}
