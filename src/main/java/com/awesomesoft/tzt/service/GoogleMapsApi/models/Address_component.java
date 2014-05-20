
package com.awesomesoft.tzt.service.GoogleMapsApi.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "long_name",
    "short_name",
    "types"
})
public class Address_component {

    @JsonProperty("long_name")
    private String long_name;
    @JsonProperty("short_name")
    private String short_name;
    @JsonProperty("types")
    private List<String> types = new ArrayList<String>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("long_name")
    public String getLong_name() {
        return long_name;
    }

    @JsonProperty("long_name")
    public void setLong_name(String long_name) {
        this.long_name = long_name;
    }

    @JsonProperty("short_name")
    public String getShort_name() {
        return short_name;
    }

    @JsonProperty("short_name")
    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    @JsonProperty("types")
    public List<String> getTypes() {
        return types;
    }

    @JsonProperty("types")
    public void setTypes(List<String> types) {
        this.types = types;
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
