package com.example.bookmylesson.service;

import com.example.bookmylesson.model.admin.Admin;
import com.example.bookmylesson.model.offering.Location;
import com.example.bookmylesson.model.offering.Schedule;
import com.example.bookmylesson.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location createLocation(Location location, Schedule schedule) {
    	if (Admin.getInstance() == null) {
    		throw new IllegalStateException("Admin is not logged in");
    	}
    	
    	location.setSchedule(schedule);
    	schedule.setLocation(location);
    	
    	location.validateSchedule();
        validateDuplicateLocation(location);
        return locationRepository.save(location);
    }

    private void validateDuplicateLocation(Location newLocation) {
        List<Location> existingLocations = locationRepository.findAll();

        for (Location existing : existingLocations) {
            if (newLocation.isDuplicate(existing)) {
                throw new IllegalArgumentException("Duplicate location already exists.");
            }
        }
    }
}
