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
        }
        return instance;
    }
    
    public static Admin getInstance() {
        return instance;
     }

}
