package com.example.bookmylesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookmylesson.model.booking.Booking;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByOfferingId(Long offeringId);
    List<Booking> findByClientId(Long clientId);
    List<Booking> findByOfferingIdAndClientId(Long offeringId, Long clientId);
}