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
    "bounds",
    "copyrights",
    "legs",
    "overview_polyline",
    "summary",
    "warnings",
    "waypoint_order"
})
public class Route {

    @JsonProperty("bounds")
    private Bounds bounds;
    @JsonProperty("copyrights")
    private String copyrights;
    @JsonProperty("legs")
    private List<Leg> legs = new ArrayList<Leg>();
    @JsonProperty("overview_polyline")
    private Overview_polyline overview_polyline;
    @JsonProperty("summary")
    private String summary;
    @JsonProperty("warnings")
    private List<Object> warnings = new ArrayList<Object>();
    @JsonProperty("waypoint_order")
    private List<Object> waypoint_order = new ArrayList<Object>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("bounds")
    public Bounds getBounds() {
        return bounds;
    }

    @JsonProperty("bounds")
    public void setBounds(Bounds bounds) {
        this.bounds = bounds;
    }

    @JsonProperty("copyrights")
    public String getCopyrights() {
        return copyrights;
    }

    @JsonProperty("copyrights")
    public void setCopyrights(String copyrights) {
        this.copyrights = copyrights;
    }

    @JsonProperty("legs")
    public List<Leg> getLegs() {
        return legs;
    }

    @JsonProperty("legs")
    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }

    @JsonProperty("overview_polyline")
    public Overview_polyline getOverview_polyline() {
        return overview_polyline;
    }

    @JsonProperty("overview_polyline")
    public void setOverview_polyline(Overview_polyline overview_polyline) {
        this.overview_polyline = overview_polyline;
    }

    @JsonProperty("summary")
    public String getSummary() {
        return summary;
    }

    @JsonProperty("summary")
    public void setSummary(String summary) {
        this.summary = summary;
    }

    @JsonProperty("warnings")
    public List<Object> getWarnings() {
        return warnings;
    }

    @JsonProperty("warnings")
    public void setWarnings(List<Object> warnings) {
        this.warnings = warnings;
    }

    @JsonProperty("waypoint_order")
    public List<Object> getWaypoint_order() {
        return waypoint_order;
    }

    @JsonProperty("waypoint_order")
    public void setWaypoint_order(List<Object> waypoint_order) {
        this.waypoint_order = waypoint_order;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "Route{" +
                ", copyrights='" + copyrights + '\'' +
                ", legs=" + legs.toString()+"}";

    }
}
