package com.example.bookmylesson.model.admin;

public class Admin {
    
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "adminpassword";
    
    private static Admin instance;

    // Private constructor to prevent instantiation
    private Admin() {}

    public static Admin login(String username, String password) {
        if (USERNAME.equals(username) && PASSWORD.equals(password)) {
        	instance = new Admin();
        	return instance;
        }
        else {
        	throw new IllegalStateException("Invalid credentials");
        }
        
    }
    
    public static Admin getInstance() {
    	if (instance == null) {
    		throw new IllegalStateException("Admin is not logged in");
    	}
        return instance;
     }

}
