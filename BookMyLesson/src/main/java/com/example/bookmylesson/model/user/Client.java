package com.example.bookmylesson.model.user;

import jakarta.persistence.Entity;

@Entity
public class Client extends User {
	
	private int age;
	
	public Client() {}
	
	public Client(String email, String password, String name, String phone, int age) {
		super(email, password, name, phone);
		this.age = age;
	}
	
	public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
