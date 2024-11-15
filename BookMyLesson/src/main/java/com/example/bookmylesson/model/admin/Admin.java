package com.example.bookmylesson.model.admin;

public class Admin {
    
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "adminpassword";
    
    private static Admin instance;

    // Private constructor to prevent instantiation
    private Admin() {}

    public static synchronized Admin login(String username, String password) {
    	if (instance != null) {
        	throw new IllegalStateException("Admin already logged in");
    	}
    	else if (USERNAME.equals(username) && PASSWORD.equals(password)) {
        	instance = new Admin();
        	return instance;
        }
        else {
        	throw new IllegalStateException("Invalid credentials");
        }
    }
    
    public static synchronized void logout() {
    	if (instance == null) {
    		throw new IllegalStateException("Admin is not logged in");
    	}
    	instance = null;
    }
    
    public static synchronized Admin getInstance() {
    	if (instance == null) {
    		throw new IllegalStateException("Admin is not logged in");
    	}
        return instance;
     }

}
