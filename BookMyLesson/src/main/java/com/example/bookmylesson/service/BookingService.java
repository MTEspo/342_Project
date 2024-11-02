package com.example.bookmylesson.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookmylesson.model.user.Client;
import com.example.bookmylesson.model.user.Representative;
import com.example.bookmylesson.model.user.User;
import com.example.bookmylesson.enums.LessonType;
import com.example.bookmylesson.model.booking.Booking;
import com.example.bookmylesson.repository.BookingRepository;

@Service
public class BookingService {
	
	@Autowired
    private BookingRepository bookingRepository;
	
	@Autowired
	private UserService userService;
	
	public List<Booking> getBookings() {
    	User user = userService.getCurrentUser();
    	if (user instanceof Client || user instanceof Representative) {
    		return bookingRepository.findByClientId(user.getId());
		}
    	else {
    		throw new IllegalStateException("User is not Client/Representative");
    	}
    }
	
	public Booking createBooking(Booking booking) {
		validateDoubleBooking(booking);
		validatePrivateBooking(booking);
        return bookingRepository.save(booking);
    }
	
	private void validateDoubleBooking(Booking booking) {
		List<Booking> duplicateBooking = bookingRepository.findByOfferingIdAndClientId(
		        booking.getOffering().getId(), booking.getClient().getId());
		if (duplicateBooking.size() > 0) {
		    throw new IllegalStateException("Client already has a booking for this offering");
		}
	}
	
	private void validatePrivateBooking(Booking booking) {
		if (booking.getOffering().getLessonType() == LessonType.PRIVATE) {
			List<Booking> existingBooking = bookingRepository.findByOfferingId(booking.getOffering().getId());
			if (existingBooking.size() > 0) {
				throw new IllegalStateException("Client already has a booking for this offering");
			}
		}
	}

}
