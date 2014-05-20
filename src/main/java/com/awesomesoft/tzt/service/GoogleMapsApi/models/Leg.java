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
    "distance",
    "duration",
    "end_address",
    "end_location",
    "start_address",
    "start_location",
    "steps",
    "via_waypoint"
})
public class Leg {

    @JsonProperty("distance")
    private Distance distance;
    @JsonProperty("duration")
    private Duration duration;
    @JsonProperty("end_address")
    private String end_address;
    @JsonProperty("end_location")
    private End_location end_location;
    @JsonProperty("start_address")
    private String start_address;
    @JsonProperty("start_location")
    private Start_location start_location;
    @JsonProperty("steps")
    private List<Step> steps = new ArrayList<Step>();
    @JsonProperty("via_waypoint")
    private List<Object> via_waypoint = new ArrayList<Object>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("distance")
    public Distance getDistance() {
        return distance;
    }

    @JsonProperty("distance")
    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    @JsonProperty("duration")
    public Duration getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    @JsonProperty("end_address")
    public String getEnd_address() {
        return end_address;
    }

    @JsonProperty("end_address")
    public void setEnd_address(String end_address) {
        this.end_address = end_address;
    }

    @JsonProperty("end_location")
    public End_location getEnd_location() {
        return end_location;
    }

    @JsonProperty("end_location")
    public void setEnd_location(End_location end_location) {
        this.end_location = end_location;
    }

    @JsonProperty("start_address")
    public String getStart_address() {
        return start_address;
    }

    @JsonProperty("start_address")
    public void setStart_address(String start_address) {
        this.start_address = start_address;
    }

    @JsonProperty("start_location")
    public Start_location getStart_location() {
        return start_location;
    }

    @JsonProperty("start_location")
    public void setStart_location(Start_location start_location) {
        this.start_location = start_location;
    }

    @JsonProperty("steps")
    public List<Step> getSteps() {
        return steps;
    }

    @JsonProperty("steps")
    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }

    @JsonProperty("via_waypoint")
    public List<Object> getVia_waypoint() {
        return via_waypoint;
    }

    @JsonProperty("via_waypoint")
    public void setVia_waypoint(List<Object> via_waypoint) {
        this.via_waypoint = via_waypoint;
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
