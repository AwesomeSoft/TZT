package com.awesomesoft.tzt.web;

import com.awesomesoft.tzt.service.domain.Station;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.List;

/**
 * Created by student on 5/30/14.
 */
@FacesConverter("stationConverter")
public class StationConverter implements Converter{


    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            TrainCourierController trainCourierController = (TrainCourierController) fc.getExternalContext().getSessionMap().get("trainCourierController");
            List<Station> allStations = trainCourierController.getAllStatins();
            for (Station station : allStations) {
                if(station.getId() == Long.parseLong(value))
                    return station;
            }
            return null;
        }
        else {
            return null;
        }
    }

    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Station) object).getId());
        }
        else {
            return null;
        }
    }

}
