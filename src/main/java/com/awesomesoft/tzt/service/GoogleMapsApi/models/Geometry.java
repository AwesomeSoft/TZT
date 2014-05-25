
package com.awesomesoft.tzt.service.GoogleMapsApi.models;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "bounds",
    "GLocation",
    "location_type",
    "viewport"
})
public class Geometry {

    @JsonProperty("bounds")
    private Bounds bounds;
    @JsonProperty("location")
    private GLocation GLocation;
    @JsonProperty("location_type")
    private String location_type;
    @JsonProperty("viewport")
    private Viewport viewport;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bounds")
    public Bounds getBounds() {
        return bounds;
    }

    @JsonProperty("bounds")
    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    @JsonProperty("location")
    public GLocation getGLocation() {
        return GLocation;
    }

    @JsonProperty("location")
    public void setGLocation(GLocation GLocation) {
        this.GLocation = GLocation;
    }

    @JsonProperty("location_type")
    public String getLocation_type() {
        return location_type;
    }

    @JsonProperty("location_type")
    public void setLocation_type(String location_type) {
        this.location_type = location_type;
    }

    @JsonProperty("viewport")
    public Viewport getViewport() {
        return viewport;
    }

    @JsonProperty("viewport")
    public void setViewport(Viewport viewport) {
        this.viewport = viewport;
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
