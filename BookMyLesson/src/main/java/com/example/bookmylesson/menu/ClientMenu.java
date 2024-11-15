package com.example.bookmylesson.menu;

import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.bookmylesson.model.booking.Booking;
import com.example.bookmylesson.model.offering.Offering;
import com.example.bookmylesson.service.BookingService;
import com.example.bookmylesson.service.OfferingService;

@Component
public class ClientMenu implements Menu {
	
	@Autowired
    private OfferingService offeringService;
	
	@Autowired
	private BookingService bookingService;

	@Override
	public void menu(Scanner scanner) {
		boolean running = true;
        while (running) {
            displayMenu();   
            try {
            	int choice = scanner.nextInt();
            	scanner.nextLine();
            	switch (choice) {
            	case 1:
            		findTakenOfferings();
            		break;
            	case 2:
            		findAllBookings();
            		break;
            	case 3:
            		makeBooking(scanner);
            		break;
            	case 4:
            		cancelBooking(scanner);
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
		System.out.println("\n--- Client/Representative Menu ---");
        System.out.println("1) View All Offerings");
        System.out.println("2) View your Booking");
        System.out.println("3) Make a Booking");
        System.out.println("4) Cancel a booking");
        System.out.println("0) Logout");
        System.out.print("Enter your choice: ");
	}
	
	private List<Offering> findTakenOfferings() {
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
    	return offerings;
    }
	
	private List<Booking> findAllBookings() {
		List<Booking> bookings = bookingService.findUserBookings();
		if (bookings == null) {
    		System.out.println("Cannot retrieve bookings.");
    	} else if (bookings.isEmpty()) {
            System.out.println("No bookings available.");
        } else {
        	for (int i = 0; i < bookings.size(); i++) {
            	Booking booking = bookings.get(i);
                System.out.println((i+1) + ": " + booking.toString());
                System.out.println("-------------");
            }
        }
    	return bookings;
	}
	
	private void makeBooking(Scanner scanner) {
		List<Offering> offerings = findTakenOfferings();
		if (offerings == null || offerings.isEmpty()) {
			System.out.println("No offerings available.");
			return;
		}
		System.out.print("Enter offering number: ");
		Offering offering = offerings.get(scanner.nextInt()-1);
		scanner.nextLine();
		
		Booking booking = new Booking();
		bookingService.createBooking(booking, offering);
		System.out.println("Booking created: " + booking.toString());
	}
	
	private void cancelBooking(Scanner scanner) {
		List<Booking> bookings = findAllBookings();
		if (bookings == null || bookings.isEmpty()) {
			System.out.println("No bookings available.");
			return;
		}
		System.out.print("Enter booking number: ");
		Booking booking = bookings.get(scanner.nextInt()-1);
		scanner.nextLine();
		
		bookingService.cancelBooking(booking);
		System.out.println("Booking cancelled.");
	}

}
