package com.example.bookmylesson.model.offering;

import jakarta.persistence.*;

import com.example.bookmylesson.enums.ActivityType;
import com.example.bookmylesson.enums.LessonType;
import com.example.bookmylesson.model.user.Instructor;

import java.time.LocalTime;

@Entity
public class Offering {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ActivityType activityType;
    private LessonType lessonType;
    
    private LocalTime startTime;
    private LocalTime endTime;
    
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;
    
    @ManyToOne
    @JoinColumn(name = "instructor_id", nullable = false)
    private Instructor instructor;

    // Constructors
    public Offering() {}
    
    public Offering(ActivityType activityType, LessonType lessonType, LocalTime startTime, LocalTime endTime, Location location, Instructor instructor) {
    	this.activityType = activityType;
    	this.lessonType = lessonType;
    	this.startTime = startTime;
    	this.endTime = endTime;
    	this.location = location;
    	this.instructor = instructor;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

    public void setActivityType(ActivityType activityType) {
        this.activityType = activityType;
    }
    
    public LessonType getLessonType() {
        return lessonType;
    }

    public void setLessonType(LessonType lessonType) {
        this.lessonType = lessonType;
    }
    
    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
    
    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    
    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

}