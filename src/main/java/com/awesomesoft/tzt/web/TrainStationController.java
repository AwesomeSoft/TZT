package com.awesomesoft.tzt.web;

import com.awesomesoft.tzt.service.TZTRepository;
import com.awesomesoft.tzt.service.ns.NsApi;
import com.awesomesoft.tzt.service.ns.StationsRequest;
import com.awesomesoft.tzt.service.ns.model.stations.Namen;
import com.awesomesoft.tzt.service.ns.model.stations.Station;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
/**
 * Created by Erwin on 24-5-2014.
 */
@ManagedBean
    @SessionScoped
    public class TrainStationController {
    private static final Logger logger = LoggerFactory.getLogger(TrainStationController.class);
        private NsApi nsApi;
        @Inject
        public TZTRepository repository;
        @PostConstruct
        public void init() {
        }
        public String makeTrainStationsList() {
            nsApi = new NsApi("erwinvanzandvliet@gmail.com", "jEwxHwkBSQBOjsQH-64LqwxqNBwzQMaD-UvoIuBpcSKHk8MRz7j12w");
            List<Station> apiResponse = null;
            try {
                apiResponse = nsApi.getApiResponse(new StationsRequest());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Iterator it=apiResponse.iterator();
            while(it.hasNext()) {
                Station value = (Station) it.next();
                if (value.getLand().equals("NL")){
                    if((value.getType().equals("knooppuntIntercitystation"))||(value.getType().equals("megastation"))) {
                        Namen namen = value.getNamen();
                        com.awesomesoft.tzt.service.domain.Station station = new com.awesomesoft.tzt.service.domain.Station(namen.getMiddel(), value.getLon(), value.getLat());
                        try {
                            repository.insertTrainStation(station);
                        }catch (Exception e){
                            StringBuilder message = new StringBuilder();
                            message.append("Alreaddy added station: ");
                            message.append(namen.getMiddel());
                            logger.info(message.toString());
                        }
                    }
                }
            }
            return "confirmation.xhtml";
        }

}

