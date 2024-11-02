package com.example.bookmylesson.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.ElementCollection;

import com.example.bookmylesson.enums.ActivityType;

import java.util.Set;

@Entity
public class Instructor extends User {

    @ElementCollection
    private Set<ActivityType> specialization;

    @ElementCollection
    private Set<String> cityAvailability;

    public Instructor() {}

    public Instructor(String email, String password, String name, String phone, 
                      Set<ActivityType> specialization, Set<String> cityAvailability) {
        super(email, password, name, phone);
        this.specialization = specialization;
        this.cityAvailability = cityAvailability;
    }

    public Set<ActivityType> getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Set<ActivityType> specialization) {
        this.specialization = specialization;
    }

    public Set<String> getCityAvailability() {
        return cityAvailability;
    }

    public void setCityAvailability(Set<String> cityAvailability) {
        this.cityAvailability = cityAvailability;
    }
}
