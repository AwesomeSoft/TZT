package com.awesomesoft.tzt.service.ns.model.storingen;

import com.awesomesoft.tzt.service.ns.NsApi;
import com.awesomesoft.tzt.service.ns.error.NsApiException;
import com.awesomesoft.tzt.service.ns.handle.Handle;
import com.awesomesoft.tzt.service.ns.xml.Xml;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class StoringenHandle implements Handle<Storingen> {

    @Override
    public Storingen getModel(InputStream stream) {
        SimpleDateFormat format = new SimpleDateFormat(NsApi.DATETIME_FORMAT);
        try {
            List<Storing> geplandeStoringen = new LinkedList<Storing>();
            List<Storing> ongeplandeStoringen = new LinkedList<Storing>();

            Xml xml = Xml.getXml(stream, "Storingen");
            if (xml.isPresent("Ongepland")) {
                Xml ongeplandeStoringenXml = xml.child("Ongepland");
                for (Xml storingXml : ongeplandeStoringenXml.children("Storing")) {
                    ongeplandeStoringen.add(getStoring(storingXml, format));
                }
            }
            if (xml.isPresent("Gepland")) {
                Xml geplandeStoringenXml = xml.child("Gepland");
                for (Xml storingXml : geplandeStoringenXml.children("Storing")) {
                    geplandeStoringen.add(getStoring(storingXml, format));
                }
            }
            return new Storingen(ongeplandeStoringen, geplandeStoringen);
        }
        catch (ParseException e) {
            throw new NsApiException("Error parsing stream to actuele vertrektijden", e);
        }
    }

    private Storing getStoring(Xml storingXml, SimpleDateFormat format) throws ParseException {
        String id = storingXml.child("id").content();
        String traject = storingXml.child("Traject").content();
        String periode = storingXml.child("Periode").content();
        String reden = storingXml.child("Reden").content();
        String advies = storingXml.child("Advies").content();
        String bericht = storingXml.child("Bericht").content();
        Date datum = null;
        if (storingXml.isPresent("Datum")) {
            datum = format.parse(storingXml.child("Datum").content());
        }
        return new Storing(id, traject, periode, reden, advies, bericht, datum);
    }

}
