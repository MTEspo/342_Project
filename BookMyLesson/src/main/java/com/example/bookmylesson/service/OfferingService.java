package com.example.bookmylesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookmylesson.model.offering.Offering;
import com.example.bookmylesson.model.admin.Admin;
import com.example.bookmylesson.repository.OfferingRepository;
import com.example.bookmylesson.model.user.Instructor;

import java.util.List;

@Service
public class OfferingService {
	
	@Autowired
    private OfferingRepository offeringRepository;
	
	@Autowired
	private UserService userService; 
	
	public List<Offering> findAllOfferings() {
		if (userService.getCurrentUser() != null) {
			return offeringRepository.findAll();
		}
		return null;
    }
	
	public List<Offering> findAllOfferingsWithInstructor() {
	    return offeringRepository.findByInstructorIsNotNull();
	}

    public List<Offering> getOfferingsByInstructor(Long instructorId) {
    	if (userService.getCurrentUser() != null) {
    		return offeringRepository.findByInstructorId(instructorId);
		}
		return null;
    }
    
    public Offering takeOffering(Long offeringId) {
    	
    	userService.AuthenticateInstructor();
    	
        Offering offering = offeringRepository.findById(offeringId)
                .orElseThrow(() -> new IllegalArgumentException("Offering not found"));

        if (offering.getInstructor() != null) {
            throw new IllegalStateException("Offering already taken by another instructor");
        }

        offering.setInstructor((Instructor)userService.getCurrentUser());
        return offeringRepository.save(offering);
    }

    public Offering createOffering(Offering newOffering) {
    	
    	if (Admin.getInstance() == null) {
            throw new IllegalStateException("Admin not authenticated.");
        }
    	
        if (isConflict(newOffering)) {
            throw new IllegalArgumentException("Offering conflicts with an existing one at the same location and time.");
        }
        return offeringRepository.save(newOffering);
    }

    private boolean isConflict(Offering newOffering) {
        List<Offering> existingOfferings = offeringRepository.findByLocationId(newOffering.getLocation().getId());
        
        for (Offering existing : existingOfferings) {
            if (hasOverlap(existing, newOffering)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasOverlap(Offering existing, Offering newOffering) {
        return false; // IMPLEMENT THIS METHOD
    }
}
