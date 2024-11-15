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
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    // Constructors
    public Offering() {}
    
    public Offering(ActivityType activityType, LessonType lessonType, LocalTime startTime, LocalTime endTime) {
    	this.activityType = activityType;
    	this.lessonType = lessonType;
    	this.startTime = startTime;
    	this.endTime = endTime;
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
    
    @Override
    public String toString() {
        return "Offering {" +
               "id=" + id +
               ", activityType=" + activityType +
               ", lessonType=" + lessonType +
               ", startTime=" + startTime +
               ", endTime=" + endTime +
               ", location=" + (location != null ? location : "null") +
               ", instructor=" + (instructor != null ? instructor.getName() : "null") +
               '}';
    }

}