package com.awesomesoft.tzt.service.GoogleMapsApi.models;

import com.fasterxml.jackson.annotation.*;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "distance",
    "duration",
    "end_location",
    "html_instructions",
    "polyline",
    "start_location",
    "travel_mode",
    "maneuver"
})
public class Step {

    @JsonProperty("distance")
    private Distance_ distance;
    @JsonProperty("duration")
    private Duration_ duration;
    @JsonProperty("end_location")
    private End_location_ end_location;
    @JsonProperty("html_instructions")
    private String html_instructions;
    @JsonProperty("polyline")
    private Polyline polyline;
    @JsonProperty("start_location")
    private Start_location_ start_location;
    @JsonProperty("travel_mode")
    private String travel_mode;
    @JsonProperty("maneuver")
    private String maneuver;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("distance")
    public Distance_ getDistance() {
        return distance;
    }

    @JsonProperty("distance")
    public void setDistance(Distance_ distance) {
        this.distance = distance;
    }

    @JsonProperty("duration")
    public Duration_ getDuration() {
        return duration;
    }

    @JsonProperty("duration")
    public void setDuration(Duration_ duration) {
        this.duration = duration;
    }

    @JsonProperty("end_location")
    public End_location_ getEnd_location() {
        return end_location;
    }

    @JsonProperty("end_location")
    public void setEnd_location(End_location_ end_location) {
        this.end_location = end_location;
    }

    @JsonProperty("html_instructions")
    public String getHtml_instructions() {
        return html_instructions;
    }

    @JsonProperty("html_instructions")
    public void setHtml_instructions(String html_instructions) {
        this.html_instructions = html_instructions;
    }

    @JsonProperty("polyline")
    public Polyline getPolyline() {
        return polyline;
    }

    @JsonProperty("polyline")
    public void setPolyline(Polyline polyline) {
        this.polyline = polyline;
    }

    @JsonProperty("start_location")
    public Start_location_ getStart_location() {
        return start_location;
    }

    @JsonProperty("start_location")
    public void setStart_location(Start_location_ start_location) {
        this.start_location = start_location;
    }

    @JsonProperty("travel_mode")
    public String getTravel_mode() {
        return travel_mode;
    }

    @JsonProperty("travel_mode")
    public void setTravel_mode(String travel_mode) {
        this.travel_mode = travel_mode;
    }

    @JsonProperty("maneuver")
    public String getManeuver() {
        return maneuver;
    }

    @JsonProperty("maneuver")
    public void setManeuver(String maneuver) {
        this.maneuver = maneuver;
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
