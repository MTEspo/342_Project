package com.example.bookmylesson.model.offering;

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
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    private Schedule schedule;
    
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
    
    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
    
    public boolean isDuplicate(Location other) {
        return this.name.equalsIgnoreCase(other.getName()) &&
               this.street.equalsIgnoreCase(other.getStreet()) &&
               this.city.equalsIgnoreCase(other.getCity()) &&
               this.province.equalsIgnoreCase(other.getProvince()) &&
               this.postalCode.equalsIgnoreCase(other.getPostalCode());
    }
    
    public void validateSchedule() {
    	schedule.validateDate();
    	schedule.validateTime();
    }

}

