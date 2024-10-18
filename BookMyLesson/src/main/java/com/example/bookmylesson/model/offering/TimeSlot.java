package com.example.bookmylesson.model.offering;

import jakarta.persistence.*;

import com.example.bookmylesson.enums.LessonType;

import java.time.LocalTime;

@Entity
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LessonType type;
    private LocalTime startTime;
    private LocalTime endTime;   

    public TimeSlot() {}  // Default constructor

    public TimeSlot(LessonType type, LocalTime startTime, LocalTime endTime) {
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LessonType getType() {
        return type;
    }

    public void setType(LessonType type) {
        this.type = type;
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
}

