package com.awesomesoft.tzt.service.ns;

import com.awesomesoft.tzt.service.ns.error.NsApiException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;
import java.io.InputStream;

class HttpConnection {

    private final HttpClient httpclient;

    HttpConnection(String username, String password) {
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(new AuthScope("webservices.ns.nl", 80), new UsernamePasswordCredentials(
                username, password));
        httpclient = HttpClientBuilder.create().setDefaultCredentialsProvider(credentialsProvider).build();
    }

    InputStream getContent(String url) throws IOException, NsApiException {
        HttpGet httpget = new HttpGet(url);
        try {
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return entity.getContent();
            }
            throw new NsApiException("Error while calling the webservice, entity is null");
        }
        catch (IOException e) {
            throw e;
        }
    }
}
