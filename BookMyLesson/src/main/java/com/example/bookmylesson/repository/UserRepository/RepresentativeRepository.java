package com.example.bookmylesson.repository.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookmylesson.model.user.Representative;

import java.util.Optional;

@Repository
public interface RepresentativeRepository extends JpaRepository<Representative,Long> {
	Optional<Representative> findByName(String name);
	Optional<Representative> findByEmail(String email);
	Optional<Representative> findByPhone(String phone);
}