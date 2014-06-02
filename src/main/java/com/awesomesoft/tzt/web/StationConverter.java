package com.awesomesoft.tzt.web;

import com.awesomesoft.tzt.service.domain.Station;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.util.List;
/**
 * Created by Gerben on 24-5-2014.
 */
@FacesConverter("stationConverter")
public class StationConverter implements Converter{


    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            TrainCourierController trainCourierController = (TrainCourierController) fc.getApplication().evaluateExpressionGet(fc,"#{trainCourierController}",TrainCourierController.class);
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
