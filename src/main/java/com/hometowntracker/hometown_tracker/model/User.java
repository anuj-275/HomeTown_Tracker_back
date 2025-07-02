package com.hometowntracker.hometown_tracker.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")  // <-- safer table name
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private LocationStatus status;

    private String groupCode;
    private String city = "Gaya"; // Default to Gaya

public String getCity() {
    return city;
}

public void setCity(String city) {
    this.city = city;
}


    // 👉 Required: No-argument constructor
    public User() {
    }

    // 👉 Optional: All-argument constructor
    public User(Long id, String name, String email, String password, LocationStatus status) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.status = status;
    }

    // 👉 Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocationStatus getStatus() {
        return status;
    }

    public void setStatus(LocationStatus status) {
        this.status = status;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }
}
