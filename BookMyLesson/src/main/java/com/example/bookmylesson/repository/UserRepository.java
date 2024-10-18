package com.example.bookmylesson.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookmylesson.model.user.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	Optional<User> findByName(String name);
	Optional<User> findByEmail(String email);
	Optional<User> findByPhone(String phone);
}