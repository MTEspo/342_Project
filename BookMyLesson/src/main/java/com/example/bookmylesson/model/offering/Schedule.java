package com.example.bookmylesson.model.offering;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import com.example.bookmylesson.enums.Days;

import jakarta.persistence.*;

@Entity
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @ElementCollection(targetClass = Days.class)
    private Set<Days> days;

    private LocalDate startDate;
    private LocalDate endDate;

    private LocalTime startTime;
    private LocalTime endTime;

    // Constructor
    public Schedule() {}

    public Schedule(Location location) {
        this.location = location;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public Location getLocation() {
    	return location;
    }
    
    public void setLocation(Location location) {
    	this.location = location;
    }

    public Set<Days> getDays() {
        return days;
    }

    public void setDays(Set<Days> days) {
        this.days = days;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

    // Additional method to determine if this schedule is active
    public boolean isActive() {
    	LocalDate currentDate = LocalDate.now();
        return (currentDate.isEqual(startDate) || currentDate.isAfter(startDate)) &&
               (currentDate.isEqual(endDate) || currentDate.isBefore(endDate));
    }
}
