package com.example.bookmylesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookmylesson.enums.ActivityType;
import com.example.bookmylesson.model.admin.Admin;
import com.example.bookmylesson.model.offering.Location;
import com.example.bookmylesson.model.offering.Offering;
import com.example.bookmylesson.model.offering.Schedule;
import com.example.bookmylesson.repository.OfferingRepository;
import com.example.bookmylesson.model.user.Instructor;
import com.example.bookmylesson.model.user.User;

import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OfferingService {
	
	@Autowired
    private OfferingRepository offeringRepository;
	
	@Autowired
	private UserService userService; 
	
	public List<Offering> findAllOfferings() {
		return offeringRepository.findAll();
    }
	
	public List<Offering> findAllTakenOfferings() {
		return offeringRepository.findByInstructorIsNotNull();
    }
	
	public List<Offering> findAllOpenOfferings() {
		return offeringRepository.findByInstructorIsNull();
    }

    public List<Offering> getOfferingsByInstructor() {
    	authenticateInstructor();
        return offeringRepository.findByInstructorId(userService.getCurrentUser().getId());
    }

    public synchronized Offering createOffering(Offering offering, Location location) {
    	if (Admin.getInstance() == null) {
    		throw new IllegalStateException("Admin is not logged in");
    	}
    	
    	validateOfferingTime(offering, location);
        validateNoOverlap(offering, location);
        offering.setLocation(location);
        return offeringRepository.save(offering);
    }
    
    public synchronized Offering takeOffering(Offering offering) {
    	authenticateInstructor();
    	validateOpenOffering(offering);
    	validateSpecialization(offering);
    	validateCity(offering);
    	offering.setInstructor((Instructor)userService.getCurrentUser());
    	return offeringRepository.save(offering);
    }

    private void validateOfferingTime(Offering offering, Location location) {
        Schedule schedule = location.getSchedule();
        LocalTime scheduleStart = schedule.getStartTime();
        LocalTime scheduleEnd = schedule.getEndTime();

        if (offering.getStartTime().isBefore(scheduleStart) || 
            offering.getEndTime().isAfter(scheduleEnd)) {
            throw new IllegalArgumentException("Offering times must be within the schedule's time range.");
        }
    }

    private void validateNoOverlap(Offering offering, Location location) {
        List<Offering> existingOfferings = offeringRepository.findByLocationId(location.getId());

        for (Offering existing : existingOfferings) {
            if (timesOverlap(offering.getStartTime(), offering.getEndTime(),
                             existing.getStartTime(), existing.getEndTime())) {
                throw new IllegalArgumentException("Offering times overlap with an existing offering at the same location.");
            }
        }
    }

    private boolean timesOverlap(LocalTime start1, LocalTime end1, LocalTime start2, LocalTime end2) {
        return (start1.isBefore(end2) && end1.isAfter(start2));
    }
    
    private void authenticateInstructor() {
    	User user = userService.getCurrentUser();
    	if (!(user instanceof Instructor)) {
    		throw new IllegalStateException("User is not instructor");
    	}
    }
    
    private void validateOpenOffering(Offering offering) {
    	if (offering.getInstructor() != null) {
    		throw new IllegalArgumentException("Offering already has an instructor.");
    	}
    }
    
    private void validateSpecialization(Offering offering) {
    	Instructor instructor = (Instructor)userService.getCurrentUser();
    	ActivityType offeringActivity = offering.getActivityType();
    	Set<ActivityType> instructorSpecs = instructor.getSpecialization();
    	
    	if (!instructorSpecs.contains(offeringActivity)) {
            throw new IllegalArgumentException("Instructor is not specialized in the activity type of the offering.");
        }
    }
    
    private void validateCity(Offering offering) {
    	Instructor instructor = (Instructor)userService.getCurrentUser();
    	String city = offering.getLocation().getCity().toLowerCase();
    	Set<String> instructorCities = instructor.getCityAvailability();
    	
    	Set<String> lowerInstructorCities = new HashSet<>();
        instructorCities.forEach(c -> lowerInstructorCities.add(c.toLowerCase()));
    	
    	if (!lowerInstructorCities.contains(city)) {
            throw new IllegalArgumentException("Instructor is not available in the city of the offering.");
        }
    }
    
}
