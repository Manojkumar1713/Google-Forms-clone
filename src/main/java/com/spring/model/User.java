package com.spring.model;

import javax.persistence.Entity;

@Entity
public class User {
	public String phoneNo;
	public String password;

	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [phoneNo=" + phoneNo + ", password=" + password + "]";
	}
	
}
