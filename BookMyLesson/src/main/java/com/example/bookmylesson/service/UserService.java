package com.example.bookmylesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookmylesson.model.user.*;
import com.example.bookmylesson.repository.UserRepository.*;

@Service
public class UserService {
	
	private final ThreadLocal<User> currentUser = new ThreadLocal<>();
	
    @Autowired
    private InstructorRepository instructorRepository;
    
    @Autowired
    private ClientRepository clientRepository;
    
    @Autowired
    private RepresentativeRepository representativeRepository;

    public void registerInstructor(Instructor instructor) {
        if (instructorRepository.findByEmail(instructor.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Instructor already exists");
        }
        this.currentUser.set(instructor);
        instructorRepository.save(instructor);
    }
    
    public void registerClient(Client client) {
        if (instructorRepository.findByEmail(client.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Client already exists");
        }
        if (client.getAge() < 18) {
        	throw new IllegalStateException("Underage clients must register with representative");
        }
        this.currentUser.set(client);
        clientRepository.save(client);
    }
    
    public void registerRepresentative(Representative representative) {
        if (representativeRepository.findByEmail(representative.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Representative already exists");
        }
        if (representative.getRepAge() < 18) {
        	throw new IllegalStateException("Representative must be adult");
        }
        if (representative.getAge() >= 18) {
        	throw new IllegalStateException("Client does not need a Representative");
        }
        this.currentUser.set(representative);
        representativeRepository.save(representative);
    }
    
    public void loginAsInstructor(String email, String password) {
    	User user = instructorRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email"));
    	if (!password.equals(user.getPassword())) {
    		throw new IllegalArgumentException("Invalid password");
    	}
    	this.currentUser.set(user);
    }
    
    public void loginAsClient(String email, String password) {
    	User user = clientRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email"));
    	if (!password.equals(user.getPassword())) {
    		throw new IllegalArgumentException("Invalid password");
    	}
    	this.currentUser.set(user);
    }
    
    public void loginAsRepresentative(String email, String password) {
    	User user = representativeRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email"));
    	if (!password.equals(user.getPassword())) {
    		throw new IllegalArgumentException("Invalid password");
    	}
    	this.currentUser.set(user);
    }
    
    public void logout() {
    	if (currentUser.get() == null) {
    		throw new IllegalStateException("No user logged in");
    	}
    	this.currentUser.remove();
    }
    
    public User getCurrentUser() {
    	if (currentUser.get() == null) {
    		throw new IllegalStateException("No user logged in");
    	}
        return currentUser.get();
    }
    
}
