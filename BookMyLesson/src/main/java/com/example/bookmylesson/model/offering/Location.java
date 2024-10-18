package com.example.bookmylesson.model.offering;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import com.example.bookmylesson.enums.Days;

import jakarta.persistence.*;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String street;
    private String city;
    private String province;
    private String postalCode;
    
    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Schedule> schedules;
    
    // Constructor
    public Location() {}

    public Location(String name, String street, String city, String province, String postalCode) {
        this.name = name;
        this.street = street;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

}

