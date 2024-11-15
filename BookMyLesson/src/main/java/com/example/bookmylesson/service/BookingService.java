package com.example.bookmylesson.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookmylesson.model.user.Client;
import com.example.bookmylesson.model.user.Representative;
import com.example.bookmylesson.model.user.User;
import com.example.bookmylesson.enums.LessonType;
import com.example.bookmylesson.model.admin.Admin;
import com.example.bookmylesson.model.booking.Booking;
import com.example.bookmylesson.model.offering.Offering;
import com.example.bookmylesson.repository.BookingRepository;

@Service
public class BookingService {
	
	@Autowired
    private BookingRepository bookingRepository;
	
	@Autowired
	private UserService userService;
	
	public List<Booking> findBookings() {
		if (Admin.getInstance() == null) {
    		throw new IllegalStateException("Admin is not logged in");
    	}
		return bookingRepository.findAll();
	}
	
	public List<Booking> findUserBookings() {
    	User user = userService.getCurrentUser();
    	if (user instanceof Client || user instanceof Representative) {
    		return bookingRepository.findByClientId(user.getId());
		}
    	else {
    		throw new IllegalStateException("User is not Client/Representative");
    	}
    }
	
	public void cancelBooking(Booking booking) {
		authenticateClient();
		validateCancelBooking(booking);
        bookingRepository.delete(booking);
    }
	
	public synchronized Booking createBooking(Booking booking, Offering offering) {
		authenticateClient();
		validateDoubleBooking(booking, offering);
		validatePrivateBooking(booking, offering);
		booking.setBookingDate(LocalDateTime.now());
		booking.setClient((Client)userService.getCurrentUser());
		booking.setOffering(offering);
        return bookingRepository.save(booking);
    }
	
	private void validateDoubleBooking(Booking booking, Offering offering) {
		List<Booking> duplicateBooking = bookingRepository.findByOfferingIdAndClientId(
		        offering.getId(), userService.getCurrentUser().getId());
		if (duplicateBooking.size() > 0) {
		    throw new IllegalStateException("Client already has a booking for this offering");
		}
	}
	
	private void validatePrivateBooking(Booking booking, Offering offering) {
		if (offering.getLessonType() == LessonType.PRIVATE) {
			List<Booking> existingBooking = bookingRepository.findByOfferingId(offering.getId());
			if (existingBooking.size() > 0) {
				throw new IllegalStateException("Client already has a booking for this offering");
			}
		}
	}
	
	private void authenticateClient() {
		User user = userService.getCurrentUser();
    	if (!(user instanceof Client || user instanceof Representative)) {
    		throw new IllegalStateException("User is not Client/Representative");
    	}
    }
	
	private void validateCancelBooking(Booking booking) {
		if (booking.getClient().getId() != userService.getCurrentUser().getId()) {
			throw new IllegalStateException("Logged in Client did not do this booking");
		}
	}

}
