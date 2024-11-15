package com.example.bookmylesson.menu;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bookmylesson.enums.ActivityType;
import com.example.bookmylesson.enums.Days;
import com.example.bookmylesson.enums.LessonType;
import com.example.bookmylesson.model.offering.Location;
import com.example.bookmylesson.model.offering.Offering;
import com.example.bookmylesson.model.offering.Schedule;
import com.example.bookmylesson.service.LocationService;
import com.example.bookmylesson.service.OfferingService;

@Component
public class AdminMenu implements Menu {
	
	@Autowired
    private OfferingService offeringService;
	
	@Autowired
    private LocationService locationService;
	
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
            		displayAllOfferings();
            		break;
            	case 2:
            		displayOpenOfferings();
            		break;
            	case 3:
            		displayTakenOfferings();
            		break;
            	case 4:
            		createOffering(scanner);
            		break;
            	case 5:
            		displayAllLocations();
            		break;
            	case 6:
            		createLocation(scanner);
            		break;
                case 0:
                    System.out.println("Logging out...");
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
		System.out.println("\n--- Admin Menu ---");
        System.out.println("1) View All Offerings");
        System.out.println("2) View all open Offerings");
        System.out.println("3) View all taken Offerings");
        System.out.println("4) Create an offering");
        System.out.println("5) View all Locations");
        System.out.println("6) Create a Location");
        System.out.println("0) Logout");
        System.out.print("Enter your choice: ");
	}
	
	private void displayAllOfferings() {
    	List<Offering> offerings = offeringService.findAllOfferings();
    	if (offerings == null) {
    		System.out.println("Cannot retrieve offerings.");
    	} else if (offerings.isEmpty()) {
            System.out.println("No offerings available.");
        } else {
            for (Offering offering : offerings) {
                System.out.println(offering.toString());
                System.out.println("-------------");
            }
        }
    }
	
	private void displayOpenOfferings() {
    	List<Offering> offerings = offeringService.findAllOpenOfferings();
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
	
	private void displayTakenOfferings() {
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
	
	private List<Location> displayAllLocations() {
    	List<Location> locations = locationService.findAllLocations();
    	if (locations == null) {
    		System.out.println("Cannot retrieve locations.");
    	} else if (locations.isEmpty()) {
            System.out.println("No locations available.");
        } else {
        	for (int i = 0; i < locations.size(); i++) {
            	Location location = locations.get(i);
                System.out.println((i+1) + ": " + location.toString());
                System.out.println("-------------");
            }
        }
    	return locations;
    }
	
	private void createLocation(Scanner scanner) {
        System.out.println("Enter schedule details:");

        System.out.println("Enter the days for the schedule (comma separated, e.g., MONDAY,TUESDAY):");
        String daysInput = scanner.nextLine();
        Set<Days> days = new HashSet<>();
        for (String day : daysInput.split(",")) {
            days.add(Days.valueOf(day.trim().toUpperCase()));
        }

        System.out.println("Enter start date (YYYY-MM-DD):");
        LocalDate startDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Enter end date (YYYY-MM-DD):");
        LocalDate endDate = LocalDate.parse(scanner.nextLine());

        System.out.println("Enter start time (HH:MM):");
        LocalTime startTime = LocalTime.parse(scanner.nextLine());

        System.out.println("Enter end time (HH:MM):");
        LocalTime endTime = LocalTime.parse(scanner.nextLine());

        Schedule schedule = new Schedule(days, startDate, endDate, startTime, endTime);

        System.out.println("Enter location details:");

        System.out.println("Enter location name:");
        String name = scanner.nextLine();

        System.out.println("Enter street:");
        String street = scanner.nextLine();

        System.out.println("Enter city:");
        String city = scanner.nextLine();

        System.out.println("Enter province:");
        String province = scanner.nextLine();

        System.out.println("Enter postal code:");
        String postalCode = scanner.nextLine();

        Location location = new Location(name, street, city, province, postalCode);
        locationService.createLocation(location, schedule);
        System.out.println("Location created: " + location.toString());
    }
	
	public void createOffering(Scanner scanner) {
		List<Location> locations = displayAllLocations();
		if (locations == null || locations.isEmpty()) {
			System.out.println("No locations available.");
			return;
		}
		System.out.print("Enter location number: ");
		Location location = locations.get(scanner.nextInt()-1);
		scanner.nextLine();
		
	    System.out.print("Enter activity type (e.g., YOGA, SWIMMING): ");
	    ActivityType activityType = ActivityType.valueOf(scanner.nextLine().toUpperCase());
	    
	    System.out.print("Enter lesson type (e.g., GROUP, PRIVATE): ");
	    LessonType lessonType = LessonType.valueOf(scanner.nextLine().toUpperCase());
	    
	    System.out.print("Enter start time (HH:MM): ");
	    LocalTime startTime = LocalTime.parse(scanner.nextLine());
	    
	    System.out.print("Enter end time (HH:MM): ");
	    LocalTime endTime = LocalTime.parse(scanner.nextLine());
	    
	    Offering offering = new Offering(activityType, lessonType, startTime, endTime);
	    offeringService.createOffering(offering, location);
	    System.out.println("Offering created: " + offering.toString());
	}


}
