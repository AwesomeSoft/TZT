package com.awesomesoft.tzt.service.domain;

import javax.persistence.*;


/**
 * Created by student on 5/15/14.
 */
@Entity
public class Station {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Location location;

    public String getName() {
        return name;
    }

    @Column(name = "location_id")
    private Long locationId;

    public Long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Station)) return false;

        Station station = (Station) o;

        if (location != null ? !location.equals(station.location) : station.location != null) return false;
        if (locationId != null ? !locationId.equals(station.locationId) : station.locationId != null) return false;
        if (name != null ? !name.equals(station.name) : station.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (locationId != null ? locationId.hashCode() : 0);
        return result;
    }
}
