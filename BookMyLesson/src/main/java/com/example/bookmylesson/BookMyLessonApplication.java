package com.example.bookmylesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.bookmylesson.model.admin.Admin;
import com.example.bookmylesson.model.offering.Offering;
import com.example.bookmylesson.model.user.Instructor;
import com.example.bookmylesson.service.OfferingService;
import com.example.bookmylesson.service.UserService;
import com.example.bookmylesson.enums.ActivityType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@SpringBootApplication
public class BookMyLessonApplication implements CommandLineRunner {

    @Autowired
    private OfferingService offeringService;
    
    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(BookMyLessonApplication.class, args);
    }

    @Override
    public void run(String... args) {
    	//Scanner scanner = new Scanner(System.in);
    	//publicUserMenu(scanner);
    	//scanner.close(); 
    }
    
    private void publicUserMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            displayPublicUserMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    displayAllOfferings(true);
                    break;
                case 2:
                    register(scanner);
                    break;
                case 3:
                	login(scanner);
                	break;
                case 0:
                    System.out.println("Exiting the application...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayPublicUserMenu() {
        System.out.println("\n--- Book My Lesson Menu ---");
        System.out.println("1) View All Offerings");
        System.out.println("2) Register");
        System.out.println("3) Login");
        System.out.println("0) Exit");
        System.out.print("Enter your choice: ");
    }
    
    private void adminMenu(Scanner scanner) {
    	boolean running = true;
        while (running) {
            displayAdminMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    displayAllOfferings(false);
                    break;
                case 2:
                    createOffering(scanner);
                    break;
                case 0:
                    System.out.println("Logging out as admin...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
    
    private void displayAdminMenu() {
        System.out.println("\n--- Admin Menu ---");
        System.out.println("1) View All Offerings");
        System.out.println("2) Create Offering");
        System.out.println("0) Logout");
        System.out.print("Enter your choice: ");
    }
    
    private void instructorMenu(Scanner scanner) {
    	boolean running = true;
        while (running) {
            displayInstructorMenu();
            int choice = getUserChoice(scanner);

            switch (choice) {
                case 1:
                    displayAllOfferings(false);
                    break;
                case 2:
                    takeOffering(scanner);
                    break;
                case 0:
                    System.out.println("Logging out as user...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }
      
    private void displayInstructorMenu() {
        System.out.println("\n--- Instructor Menu ---");
        System.out.println("1) View All Offerings");
        System.out.println("2) Take Offering");
        System.out.println("0) Logout");
        System.out.print("Enter your choice: ");
    }

    private int getUserChoice(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.next();  // Clear invalid input
        }
        return scanner.nextInt();
    }

    private void displayAllOfferings(boolean withInstructor) {
    	
    	List<Offering> offerings = offeringService.findAllOfferings();
    	if (offerings == null) {
    		System.out.println("Cannot retrieve offerings.");
    	} else if (offerings.isEmpty()) {
            System.out.println("No offerings available.");
        } else {
            for (Offering offering : offerings) {
                System.out.println("Offering ID: " + offering.getId());
                System.out.println("Instructor: " + offering.getInstructor());
                System.out.println("-------------");
            }
        }
    }
    
    private void register(Scanner scanner) {
    	Instructor instructor = new Instructor();
    	
    	System.out.print("Enter name: ");
        instructor.setName(scanner.next());
        System.out.print("Enter email: ");
        instructor.setEmail(scanner.next());
        System.out.print("Enter password: ");
        instructor.setPassword(scanner.next());
        System.out.print("Enter phone: ");
        instructor.setPhone(scanner.next());
        
        System.out.print("Enter city availability (separated by ,): ");
        instructor.setCityAvailability(new HashSet<>(Arrays.asList(scanner.next().split(",\\s*"))));
        
        System.out.print("Enter specializations [JUDO, YOGA, BOXING, SWIMMING] (separated by ,): ");
        Set<String> specializations = new HashSet<>(Arrays.asList(scanner.next().split(",\\s*")));
        Set<ActivityType> activityTypes = new HashSet<>();
        for (String activityString : specializations) 
            activityTypes.add(ActivityType.valueOf(activityString.toUpperCase()));
        instructor.setSpecialization(activityTypes);
        
        userService.registerUser(instructor);
        instructorMenu(scanner);
    }

    private void login(Scanner scanner) {
        System.out.print("Enter username/email: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        
        if (username.equals("admin")) {
        	Admin.login(username, password);
        	adminMenu(scanner);
        }
        else {
        	userService.login(username, password);
        	instructorMenu(scanner);
        }
        
    }    

    private void createOffering(Scanner scanner) {
    	
    }
    
    private void takeOffering(Scanner scanner) {
    	
    }
}

