package com.example.bookmylesson.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bookmylesson.model.user.*;
import com.example.bookmylesson.repository.UserRepository;

@Service
public class UserService {
	
	// Current logged-in user
	private User currentUser;
	
    @Autowired
    private UserRepository userRepository;

    public void registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User already exists");
        }
        this.currentUser = user;
        userRepository.save(user);
    }
    
    public void login(String email, String password) {
    	User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email"));
    	if (!password.equals(user.getPassword())) {
    		throw new IllegalArgumentException("Invalid password");
    	}
    	this.currentUser = user;
    }
    
    public void logout() {
    	this.currentUser = null;
    }
    
    public User getCurrentUser() {
        return currentUser;
    }
    
    public void AuthenticateInstructor() {
    	if (currentUser instanceof Instructor) {
    		throw new IllegalStateException("User is not instructor");
    	}
    }
    
}
