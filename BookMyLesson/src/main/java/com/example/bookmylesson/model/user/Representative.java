package com.example.bookmylesson.model.user;

import jakarta.persistence.Entity;

@Entity
public class Representative extends Client {
	
	private String repName;
	private String repPhone;
	private String repEmail;
	private int repAge;
	
	public Representative() {}
	
	public Representative(String email, String password, String name, String phone, int age, 
			String repName, String repPhone, String repEmail, int repAge) {
		super(email, password, name, phone, age);
		this.repName = repName;
        this.repPhone = repPhone;
        this.repEmail = repEmail;
        this.repAge = repAge;
	}
	
	public String getRepName() {
        return repName;
    }

    public void setRepName(String repName) {
        this.repName = repName;
    }

    public String getRepPhone() {
        return repPhone;
    }

    public void setRepPhone(String repPhone) {
        this.repPhone = repPhone;
    }

    public String getRepEmail() {
        return repEmail;
    }

    public void setRepEmail(String repEmail) {
        this.repEmail = repEmail;
    }

    public int getRepAge() {
        return repAge;
    }

    public void setRepAge(int repAge) {
        this.repAge = repAge;
    }

}