package com.example.bookmylesson.menu;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bookmylesson.model.offering.Offering;
import com.example.bookmylesson.service.OfferingService;

@Component
public class InstructorMenu implements Menu {
	
	@Autowired
    private OfferingService offeringService;
    
    public InstructorMenu() {
    	offeringService = new OfferingService();
    }

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
            		findOpenOfferings();
            		break;
            	case 2:
            		findTakenOfferings();
            		break;
            	case 3:
            		takeOffering(scanner);
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
		System.out.println("\n--- Instructor Menu ---");
        System.out.println("1) View all open Offerings");
        System.out.println("2) View your Offerings");
        System.out.println("3) Take offering");
        System.out.println("0) Logout");
        System.out.print("Enter your choice: ");
	}
	
	private List<Offering> findOpenOfferings() {
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
    	return offerings;
	}
	
	private void findTakenOfferings() {
		List<Offering> offerings = offeringService.getOfferingsByInstructor();
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
	
	private void takeOffering(Scanner scanner) {
		List<Offering> offerings = findOpenOfferings();
		if (offerings == null || offerings.isEmpty()) {
			System.out.println("No open offerings available.");
			return;
		}
		System.out.print("Enter offering number: ");
		Offering offering = offerings.get(scanner.nextInt()-1);
		scanner.nextLine();
		
		offeringService.takeOffering(offering);
		System.out.println("Offering taken: " + offering.toString());
	}

}
