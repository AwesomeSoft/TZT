package com.awesomesoft.tzt.service.GoogleMapsApi.models;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "GRoutes",
    "status"
})
public class Example {

    @JsonProperty("GRoutes")
    private List<GRoute> GRoutes = new ArrayList<GRoute>();
    @JsonProperty("status")
    private String status;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("GRoutes")
    public List<GRoute> getGRoutes() {
        return GRoutes;
    }

    @JsonProperty("GRoutes")
    public void setGRoutes(List<GRoute> GRoutes) {
        this.GRoutes = GRoutes;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
