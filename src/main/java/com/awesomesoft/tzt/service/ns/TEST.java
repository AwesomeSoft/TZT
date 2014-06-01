package com.awesomesoft.tzt.service.ns;

import com.awesomesoft.tzt.service.TZTRepository;
import com.awesomesoft.tzt.service.ns.model.stations.Namen;
import com.awesomesoft.tzt.service.ns.model.stations.Station;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Erwin on 27-5-2014.
 */
public class TEST {

    TZTRepository repository;

    public static void main (String[] args) throws IOException {
        NsApi nsApi;
        nsApi = new NsApi("erwinvanzandvliet@gmail.com", "jEwxHwkBSQBOjsQH-64LqwxqNBwzQMaD-UvoIuBpcSKHk8MRz7j12w");
        List<Station> apiResponse;
        apiResponse = nsApi.getApiResponse(new StationsRequest());
        Iterator it=apiResponse.iterator();
        while(it.hasNext())
        {
            Station value = (Station) it.next();
            if (value.getLand().equals("NL")) {
                if ((value.getType().equals("knooppuntIntercitystation")) || (value.getType().equals("megastation"))) {
                    System.out.println("Type :" + value.getType());
                    System.out.println("Land :" + value.getLand());
                    Namen namen = value.getNamen();
                    System.out.println("Naam :" + namen.getMiddel());
                    System.out.println("Locatie :" + value.getLat() + " " + value.getLon());
                }
            }
        }
    }
}

