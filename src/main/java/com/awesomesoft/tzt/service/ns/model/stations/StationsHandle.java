package com.awesomesoft.tzt.service.ns.model.stations;

import com.awesomesoft.tzt.service.ns.handle.Handle;
import com.awesomesoft.tzt.service.ns.xml.Xml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class StationsHandle implements Handle<List<Station>> {

    @Override
    public List<Station> getModel(InputStream stream) {
        List<Station> stations = new LinkedList<Station>();
        Xml xml = Xml.getXml(stream, "Stations");
        for (Xml stationXml : xml.children("Station")) {
            String code = stationXml.child("Code").content();
            String type = stationXml.child("Type").content();
            Namen namen = new Namen(stationXml.child("Namen").child("Kort").content(), stationXml.child("Namen")
                    .child("Middel").content(), stationXml.child("Namen").child("Lang").content());
            String land = stationXml.child("Land").content();
            int uicCode = Integer.parseInt(stationXml.child("UICCode").content());
            double lat = Double.parseDouble(stationXml.child("Lat").content());
            double lon = Double.parseDouble(stationXml.child("Lon").content());
            List<String> synoniemen = new ArrayList<String>(stationXml.child("Synoniemen").children("Synoniem").size());
            for (Xml synomiemXml : stationXml.child("Synoniemen").children("Synoniem")) {
                synoniemen.add(synomiemXml.content());
            }
            stations.add(new Station(code, type, namen, land, uicCode, lat, lon, synoniemen));
        }
        return Collections.unmodifiableList(stations);
    }
}
