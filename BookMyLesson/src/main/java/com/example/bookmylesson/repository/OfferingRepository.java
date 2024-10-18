package com.example.bookmylesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookmylesson.model.offering.Offering;

import java.util.List;

public interface OfferingRepository extends JpaRepository<Offering, Long> {
	List<Offering> findByInstructorIsNotNull();
    List<Offering> findByInstructorId(Long instructorId);
    List<Offering> findByLocationId(Long locationId);
}
