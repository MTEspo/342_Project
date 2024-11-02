package com.example.bookmylesson.repository.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookmylesson.model.user.Instructor;

import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor,Long> {
	Optional<Instructor> findByName(String name);
	Optional<Instructor> findByEmail(String email);
	Optional<Instructor> findByPhone(String phone);
}
