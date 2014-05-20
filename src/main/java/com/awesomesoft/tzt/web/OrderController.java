package com.awesomesoft.tzt.web;

/**
 * Created by student on 5/17/14.
 */

import com.awesomesoft.tzt.service.GoogleMapsApi.GoogleMapsApi;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean  //zorgt ervoor dat de personcontroller in JSF beschikbaar is
@SessionScoped
public class OrderController {

   public void aptTest(){
          GoogleMapsApi.planRoute("Zwolle","Groningen");


   }

}
