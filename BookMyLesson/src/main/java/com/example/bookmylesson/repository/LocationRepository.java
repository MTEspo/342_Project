package com.example.bookmylesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bookmylesson.model.offering.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
	
}