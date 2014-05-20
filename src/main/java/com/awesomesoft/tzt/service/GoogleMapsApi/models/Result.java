
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
    "address_components",
    "formatted_address",
    "geometry",
    "types"
})
public class Result {

    @JsonProperty("address_components")
    private List<Address_component> address_components = new ArrayList<Address_component>();
    @JsonProperty("formatted_address")
    private String formatted_address;
    @JsonProperty("geometry")
    private Geometry geometry;
    @JsonProperty("types")
    private List<String> types = new ArrayList<String>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("address_components")
    public List<Address_component> getAddress_components() {
        return address_components;
    }

    @JsonProperty("address_components")
    public void setAddress_components(List<Address_component> address_components) {
        this.address_components = address_components;
    }

    @JsonProperty("formatted_address")
    public String getFormatted_address() {
        return formatted_address;
    }

    @JsonProperty("formatted_address")
    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    @JsonProperty("geometry")
    public Geometry getGeometry() {
        return geometry;
    }

    @JsonProperty("geometry")
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
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
