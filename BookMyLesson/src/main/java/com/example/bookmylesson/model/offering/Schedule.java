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

    @ElementCollection(targetClass = Days.class)
    private Set<Days> days;

    private LocalDate startDate;
    private LocalDate endDate;

    private LocalTime startTime;
    private LocalTime endTime;
    
    @OneToOne(mappedBy = "schedule", cascade = CascadeType.ALL, optional = false)
    private Location location;

    // Constructor
    public Schedule() {}
    
    public Schedule(Set<Days> days, LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime) {
    	this.days = days;
    	this.startDate = startDate;
    	this.endDate = endDate;
    	this.startTime = startTime;
    	this.endTime = endTime;
    }

    // Getters and Setters
    public Long getId() {
        return id;
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
    
    public Location getLocation() {
    	return location;
    }
    
    public void setLocation(Location location) {
    	this.location = location;
    }

    public boolean isActive() {
    	LocalDate currentDate = LocalDate.now();
        return (currentDate.isEqual(startDate) || currentDate.isAfter(startDate)) &&
               (currentDate.isEqual(endDate) || currentDate.isBefore(endDate));
    }
    
    public void validateDate() {
    	if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before end date.");
        }
    }
    
    public void validateTime() {
    	if (startTime.isAfter(endTime)) {
            throw new IllegalArgumentException("Start time must be before end time.");
        }
    }
    
    @Override
    public String toString() {
        return "Schedule{" +
               "id=" + id +
               ", days=" + days +
               ", startDate=" + startDate +
               ", endDate=" + endDate +
               ", startTime=" + startTime +
               ", endTime=" + endTime +
               '}';
    }
    
}
