package com.example.bookmylesson.repository.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bookmylesson.model.user.Client;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> {
	Optional<Client> findByName(String name);
	Optional<Client> findByEmail(String email);
	Optional<Client> findByPhone(String phone);
}
