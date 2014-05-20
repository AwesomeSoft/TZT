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
    "routes",
    "status"
})
public class Example {

    @JsonProperty("routes")
    private List<Route> routes = new ArrayList<Route>();
    @JsonProperty("status")
    private String status;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("routes")
    public List<Route> getRoutes() {
        return routes;
    }

    @JsonProperty("routes")
    public void setRoutes(List<Route> routes) {
        this.routes = routes;
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
