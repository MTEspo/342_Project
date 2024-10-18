package com.example.bookmylesson.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.ElementCollection;

import com.example.bookmylesson.model.offering.Offering;
import com.example.bookmylesson.enums.ActivityType;

import java.util.Set;
import java.util.List;

@Entity
public class Instructor extends User {

    @ElementCollection
    private Set<ActivityType> specialization;

    @OneToMany(mappedBy = "instructor")
    private List<Offering> offerings;

    @ElementCollection
    private Set<String> cityAvailability;

    public Instructor() {}

    public Instructor(String username, String email, String name, String phone, 
                      Set<ActivityType> specialization, Set<String> cityAvailability) {
        super(username, email, name, phone);
        this.specialization = specialization;
        this.cityAvailability = cityAvailability;
    }

    public Set<ActivityType> getSpecialization() {
        return specialization;
    }

    public void setSpecialization(Set<ActivityType> specialization) {
        this.specialization = specialization;
    }

    public List<Offering> getOfferings() {
        return offerings;
    }

    public void setOfferings(List<Offering> offerings) {
        this.offerings = offerings;
    }

    public Set<String> getCityAvailability() {
        return cityAvailability;
    }

    public void setCityAvailability(Set<String> cityAvailability) {
        this.cityAvailability = cityAvailability;
    }
}
