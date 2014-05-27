package com.awesomesoft.tzt.service.ns.model.vertrektijden;

import com.awesomesoft.tzt.service.ns.NsApi;
import com.awesomesoft.tzt.service.ns.error.NsApiException;
import com.awesomesoft.tzt.service.ns.handle.Handle;
import com.awesomesoft.tzt.service.ns.xml.Xml;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


public class ActueleVertrekTijdenHandle implements Handle<List<VertrekkendeTrein>> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public List<VertrekkendeTrein> getModel(InputStream stream) {
        SimpleDateFormat format = new SimpleDateFormat(NsApi.DATETIME_FORMAT);
        try {
            List<VertrekkendeTrein> vertrekkendeTreinen = new LinkedList<VertrekkendeTrein>();
            Xml xml = Xml.getXml(stream, "ActueleVertrekTijden");
            for (Xml vertrekkendeTreinXml : xml.children("VertrekkendeTrein")) {
                int ritNummer = Integer.parseInt(vertrekkendeTreinXml.child("RitNummer").content());
                Date vertrekTijd = format.parse(vertrekkendeTreinXml.child("VertrekTijd").content());
                String vertrekVertraging = vertrekkendeTreinXml.child("VertrekVertraging").content();
                int vertrekVertragingMinuten = 0;
                if (vertrekVertraging != null && !vertrekVertraging.isEmpty()) {
                    try {
                        vertrekVertragingMinuten = Integer.parseInt(vertrekVertraging.replace("PT", "")
                                .replace("M", ""));
                    }
                    catch (NumberFormatException e) {
                        logger.warn("Error parsing vertrek vertraging minuten into minutes", e);
                    }
                }
                String vertrekVertragingTekst = vertrekkendeTreinXml.child("VertrekVertragingTekst").content();
                String eindBestemming = vertrekkendeTreinXml.child("EindBestemming").content();
                String treinSoort = vertrekkendeTreinXml.child("TreinSoort").content();
                String routeTekst = vertrekkendeTreinXml.child("RouteTekst").content();
                String vervoerder = vertrekkendeTreinXml.child("Vervoerder").content();
                String vertrekSpoor = vertrekkendeTreinXml.child("VertrekSpoor").content();
                boolean gewijzigdVertrekspoor = Boolean.valueOf(vertrekkendeTreinXml.child("VertrekSpoor").attr(
                        "wijziging"));
                List<String> opmerkingen = new LinkedList<String>();
                for (Xml opm : vertrekkendeTreinXml.children("Opmerkingen")) {
                    opmerkingen.add(opm.child("Opmerking").content());
                }
                String reisTip = vertrekkendeTreinXml.child("ReisTip").content();
                vertrekkendeTreinen.add(new VertrekkendeTrein(ritNummer, vertrekTijd, vertrekVertraging,
                        vertrekVertragingMinuten, vertrekVertragingTekst, eindBestemming, treinSoort, routeTekst,
                        vervoerder, vertrekSpoor, gewijzigdVertrekspoor, reisTip, opmerkingen));
            }
            return Collections.unmodifiableList(vertrekkendeTreinen);
        }
        catch (ParseException e) {
            logger.error("Error parsing stream to actuele vertrektijden", e);
            throw new NsApiException("Error parsing stream to actuele vertrektijden", e);
        }
    }

}
