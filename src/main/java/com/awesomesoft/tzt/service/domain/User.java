package com.awesomesoft.tzt.service.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Gerben de Heij on 24/04/14.
 */

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;
    private String email;
    private String password;
    private int role;

    public User(String username, String email, String password, int role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    protected User() {
    }


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() { return password; }

    public int getRole() { return role; }

    public Long getId() {
        return id;
    }
}