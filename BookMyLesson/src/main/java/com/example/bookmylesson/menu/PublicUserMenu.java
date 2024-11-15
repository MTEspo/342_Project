package com.example.bookmylesson.menu;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bookmylesson.enums.ActivityType;
import com.example.bookmylesson.model.admin.Admin;
import com.example.bookmylesson.model.offering.Offering;
import com.example.bookmylesson.model.user.Client;
import com.example.bookmylesson.model.user.Instructor;
import com.example.bookmylesson.model.user.Representative;
import com.example.bookmylesson.service.OfferingService;
import com.example.bookmylesson.service.UserService;

@Component
public class PublicUserMenu implements Menu {
	
	@Autowired
    private OfferingService offeringService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AdminMenu adminMenu;
	
	@Autowired
	private InstructorMenu instructorMenu;
	
	@Autowired
	private ClientMenu clientMenu;
	
	@Override
	public void menu(Scanner scanner) {
		boolean running = true;
        while (running) {
            displayMenu();   
            try {
            	System.out.print("Enter choice: ");
            	int choice = scanner.nextInt();
            	scanner.nextLine();
            	switch (choice) {
                case 1:
                    displayOfferings();
                    break;
                case 2:
                    registerAsInstructor(scanner);
                    logout();
                    break;
                case 3:
                	registerAsClient(scanner);
                	logout();
                    break;
                case 4:
                    registerAsRepresentative(scanner);
                    logout();
                    break;
                case 5:
                    loginAsInstructor(scanner);
                    logout();
                    break;
                case 6:
                    loginAsClient(scanner);
                    logout();
                    break;
                case 7:
                    loginAsRepresentative(scanner);
                    logout();
                    break;
                case 8:
                    loginAsAdmin(scanner);
                    Admin.logout();
                    break;
                case 0:
                    System.out.println("Exiting the application...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception ex) {
                System.out.println(ex.toString());
            }
        }
    }
	
	@Override
	public void displayMenu() {
		System.out.println("\n--- Book My Lesson Menu ---");
        System.out.println("1) View All Offerings");
        System.out.println("2) Register as Instructor");
        System.out.println("3) Register as Client");
        System.out.println("4) Register as Representative");
        System.out.println("5) Login as Instructor");
        System.out.println("6) Login as Client");
        System.out.println("7) Login as Representative");
        System.out.println("8) Login as Admin");
        System.out.println("0) Exit");
        System.out.print("Enter your choice: ");
	}
	
    private void displayOfferings() {
    	List<Offering> offerings = offeringService.findAllTakenOfferings();
    	if (offerings == null) {
    		System.out.println("Cannot retrieve offerings.");
    	} else if (offerings.isEmpty()) {
            System.out.println("No offerings available.");
        } else {
        	for (int i = 0; i < offerings.size(); i++) {
            	Offering offering = offerings.get(i);
                System.out.println((i+1) + ": " + offering.toString());
                System.out.println("-------------");
            }
        }
    }
    
    private void registerAsClient(Scanner scanner) {
        System.out.println("Enter email: ");
        String email = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        System.out.println("Enter phone: ");
        String phone = scanner.nextLine();

        System.out.println("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        Client client = new Client(email, password, name, phone, age);
        userService.registerClient(client);
        clientMenu.menu(scanner);
    }

    private void registerAsInstructor(Scanner scanner) {
        System.out.println("Enter email: ");
        String email = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        System.out.println("Enter phone: ");
        String phone = scanner.nextLine();

        System.out.println("Enter specialization (comma separated): ");
        String specializationInput = scanner.nextLine();
        Set<ActivityType> specialization = new HashSet<>();
        for (String activity : specializationInput.split(",")) {
            specialization.add(ActivityType.valueOf(activity.trim()));
        }

        System.out.println("Enter city availability (comma separated): ");
        String cityInput = scanner.nextLine();
        Set<String> cityAvailability = new HashSet<>();
        for (String city : cityInput.split(",")) {
            cityAvailability.add(city.trim());
        }

        Instructor instructor = new Instructor(email, password, name, phone, specialization, cityAvailability);
        userService.registerInstructor(instructor);
        instructorMenu.menu(scanner);
    }

    private void registerAsRepresentative(Scanner scanner) {
        System.out.println("Enter email: ");
        String email = scanner.nextLine();

        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        System.out.println("Enter name: ");
        String name = scanner.nextLine();

        System.out.println("Enter phone: ");
        String phone = scanner.nextLine();

        System.out.println("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter representative name: ");
        String repName = scanner.nextLine();

        System.out.println("Enter representative phone: ");
        String repPhone = scanner.nextLine();

        System.out.println("Enter representative email: ");
        String repEmail = scanner.nextLine();

        System.out.println("Enter representative age: ");
        int repAge = scanner.nextInt();
        scanner.nextLine();

        Representative representative = new Representative(email, password, name, phone, age, repName, repPhone, repEmail, repAge);
        userService.registerRepresentative(representative);
        clientMenu.menu(scanner);
    }

    
    private void loginAsAdmin(Scanner scanner) {
        System.out.print("Enter username/email: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        
        Admin.login(username, password);
        adminMenu.menu(scanner);
    }
    
    private void loginAsInstructor(Scanner scanner) {
        System.out.print("Enter username/email: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        
        userService.loginAsInstructor(username, password);
        instructorMenu.menu(scanner);
    }   
    
    private void loginAsClient(Scanner scanner) {
        System.out.print("Enter username/email: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        
        userService.loginAsClient(username, password);
        clientMenu.menu(scanner);
    }
    
    private void loginAsRepresentative(Scanner scanner) {
        System.out.print("Enter username/email: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        
        userService.loginAsRepresentative(username, password);
        clientMenu.menu(scanner);
    }
    
    private void logout() {
    	userService.logout();
    }

}
