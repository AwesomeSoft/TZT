package com.awesomesoft.tzt.service.GoogleMapsApi.models;

import com.awesomesoft.tzt.service.GoogleMapsApi.exceptions.RouteNotFoundException;

/**
 * Created by student on 5/20/14.
 */
public class Routes {

    Route[] routes;

    public Route[] getRoutes() {
        return routes;
    }

    public void setRoutes(Route[] routes) {
        this.routes = routes;
    }

    @Override
    public String toString() {
        return "Routes{" +
                "routes=" + routes[0].toString() +
                '}';
    }

    //Return the first route
    public Route getRoute() throws RouteNotFoundException{
        if(routes.length>0){
            return routes[0];
        }else{
            throw new RouteNotFoundException("Error: Your route is not found");
        }
    }

}
