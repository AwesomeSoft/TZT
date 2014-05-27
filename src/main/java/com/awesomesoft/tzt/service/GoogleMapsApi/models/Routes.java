package com.awesomesoft.tzt.service.GoogleMapsApi.models;

import com.awesomesoft.tzt.service.GoogleMapsApi.exceptions.RouteNotFoundException;

/**
 * Created by student on 5/20/14.
 */
public class Routes {

    Route[] Routes;

    public Route[] getRoutes() {
        return Routes;
    }

    public void setRoutes(Route[] routes) {
        this.Routes = routes;
    }

    @Override
    public String toString() {
        return "Routes{" +
                "Routes=" + Routes[0].toString() +
                '}';
    }

    //Return the first route
    public Route getRoute() throws RouteNotFoundException{
        if(Routes.length>0){
            return Routes[0];
        }else{
            throw new RouteNotFoundException("Error: Your route is not found");
        }
    }

}
