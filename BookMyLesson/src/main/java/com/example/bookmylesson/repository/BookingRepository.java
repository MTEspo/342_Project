package com.example.bookmylesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookmylesson.model.booking.Booking;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByOffering_Id(Long offeringId);
    List<Booking> findByClient_Id(Long clientId);
    List<Booking> findByLesson_Offering_IdAndClient_Id(Long offeringId, Long clientId);
}