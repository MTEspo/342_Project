package com.example.bookmylesson.model.offering;

import jakarta.persistence.*;

import com.example.bookmylesson.enums.ActivityType;
import com.example.bookmylesson.model.user.Instructor;

import java.util.List;

@Entity
public class Offering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ActivityType type;
    private String description;
    
    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
    
    @ManyToOne
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "offering_id")
    private List<TimeSlot> subTimeSlots;

    public Offering() {}  // Default constructor

    // Getters and setters
    public Long getId() {
        return id;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }
    
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    
    public List<TimeSlot> getSubTimeSlots() {
        return subTimeSlots;
    }

    public void setSubTimeSlots(List<TimeSlot> subTimeSlots) {
        this.subTimeSlots = subTimeSlots;
    }

}