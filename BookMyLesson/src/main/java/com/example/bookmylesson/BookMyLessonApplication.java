package com.example.bookmylesson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.bookmylesson.menu.PublicUserMenu;

import java.util.Scanner;

@SpringBootApplication
public class BookMyLessonApplication implements CommandLineRunner {

	@Autowired
	private PublicUserMenu userMenu;
	
    public static void main(String[] args) {
        SpringApplication.run(BookMyLessonApplication.class, args);
    }

    @Override
    public void run(String... args) {
    	Scanner scanner = new Scanner(System.in);
    	userMenu.menu(scanner);
    	scanner.close(); 
    }
    
}

