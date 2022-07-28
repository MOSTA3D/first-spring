package com.mostafa.demo.ui.model.response;

public class UserRest {
	private String firstname;
	private String lastname;
	private String email;
	private Long userId;
	
	public UserRest(String firstname, String lastname, String email, Long userId) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.userId = userId;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}
