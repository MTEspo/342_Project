package com.example.bookmylesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookmylesson.model.offering.Offering;
import com.example.bookmylesson.model.offering.Schedule;
import com.example.bookmylesson.repository.OfferingRepository;
import com.example.bookmylesson.model.user.Instructor;
import com.example.bookmylesson.model.user.User;

import java.time.LocalTime;
import java.util.List;

@Service
public class OfferingService {
	
	@Autowired
    private OfferingRepository offeringRepository;
	
	@Autowired
	private UserService userService; 
	
	public List<Offering> findAllOfferings() {
		return offeringRepository.findAll();
    }

    public List<Offering> getOfferingsByInstructor() {
    	User user = userService.getCurrentUser();
    	if (user instanceof Instructor ) {
    		return offeringRepository.findByInstructorId(user.getId());
		}
    	else {
    		throw new IllegalStateException("User is not instructor");
    	}
    }

    public Offering createOffering(Offering offering) {
    	validateInstructor(offering);
    	validateOfferingTime(offering);
        validateNoOverlap(offering);
        return offeringRepository.save(offering);
    }
    
    private void validateInstructor(Offering offering) {
    	userService.AuthenticateInstructor();
    	offering.setInstructor((Instructor)userService.getCurrentUser());
    }

    private void validateOfferingTime(Offering offering) {
        Schedule schedule = offering.getLocation().getSchedule();
        LocalTime scheduleStart = schedule.getStartTime();
        LocalTime scheduleEnd = schedule.getEndTime();

        if (offering.getStartTime().isBefore(scheduleStart) || 
            offering.getEndTime().isAfter(scheduleEnd)) {
            throw new IllegalArgumentException("Offering times must be within the schedule's time range.");
        }
    }

    private void validateNoOverlap(Offering offering) {
        List<Offering> existingOfferings = offeringRepository.findByLocationId(offering.getLocation().getId());

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
    
}
