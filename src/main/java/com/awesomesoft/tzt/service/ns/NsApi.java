package com.awesomesoft.tzt.service.ns;

import com.awesomesoft.tzt.service.ns.error.NsApiException;
import com.awesomesoft.tzt.service.ns.handle.Handle;
import com.awesomesoft.tzt.service.ns.model.prijzen.ProductenHandle;
import com.awesomesoft.tzt.service.ns.model.reisadvies.ReisadviesHandle;
import com.awesomesoft.tzt.service.ns.model.stations.StationsHandle;
import com.awesomesoft.tzt.service.ns.model.storingen.StoringenHandle;
import com.awesomesoft.tzt.service.ns.model.vertrektijden.ActueleVertrekTijdenHandle;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class NsApi {

    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";

    private HttpConnection httpConnection;

    private static final String BASE_URL = "http://webservices.ns.nl/";

    private final Map<Class<?>, Handle<?>> handleMap = new HashMap<Class<?>, Handle<?>>();

    public NsApi(String username, String password) {
        if (username == null || password == null) {
            throw new NullPointerException("Username or password cannot be null");
        }
        if (username.trim().length() == 0 || password.trim().length() == 0) {
            throw new IllegalArgumentException("Username or password cannot be empty");
        }
        httpConnection = new HttpConnection(username, password);
        handleMap.put(ActueleVertrekTijdenRequest.class, new ActueleVertrekTijdenHandle());
        handleMap.put(StationsRequest.class, new StationsHandle());
        handleMap.put(StoringenEnWerkzaamhedenRequest.class, new StoringenHandle());
        handleMap.put(ReisadviesRequest.class, new ReisadviesHandle());
        handleMap.put(PrijzenRequest.class, new ProductenHandle());
    }

    public <T> T getApiResponse(ApiRequest<T> request) throws IOException, NsApiException {
        InputStream stream = null;
        try {
            stream = httpConnection.getContent(NsApi.BASE_URL + request.getPath() + "?" + request.getRequestString());
            @SuppressWarnings("unchecked")
            Handle<T> handle = (Handle<T>) handleMap.get(request.getClass());
            if (handle == null) {
                throw new NsApiException("Unknown request type " + request.getClass());
            }
            return handle.getModel(stream);
        }
        finally {
            IOUtils.closeQuietly(stream);
        }
    }

}
