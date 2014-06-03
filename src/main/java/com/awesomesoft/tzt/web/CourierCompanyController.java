package com.awesomesoft.tzt.web;

import com.awesomesoft.tzt.service.TZTRepository;
import com.awesomesoft.tzt.service.domain.CourierCompany;
import com.awesomesoft.tzt.service.domain.Person;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 * Created by Gerben de Heij on 6/2/14.
 */

@ManagedBean  //zorgt ervoor dat de personcontroller in JSF beschikbaar is
@ViewScoped
public class CourierCompanyController {

    @Inject
    TZTRepository repository;

    CourierCompany courierCompany;
    @PostConstruct
    public void init() {
        if(this.courierCompany == null){
            this.courierCompany = new CourierCompany();
        }
    }

    public CourierCompany getCourierCompany() {
        return courierCompany;
    }

    public void setCourierCompany(CourierCompany courierCompany) {
        this.courierCompany = courierCompany;
    }

    public String addCourier(Person person){
        if(person != null){
            if(person.isAuthenticated()){
                repository.insertCourier(courierCompany);
                return  "confirmation.xhtml";
            }
        }
        return "";
    }

}
