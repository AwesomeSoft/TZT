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

    private String name;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private Location location;

    public String getName() {
        return name;
    }

    @Column(name = "location_id")
    private Long locationId;

    public void setName(String name) {
        this.name = name;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }



}
