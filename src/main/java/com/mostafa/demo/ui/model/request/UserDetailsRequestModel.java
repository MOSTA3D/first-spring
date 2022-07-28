package com.mostafa.demo.ui.model.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.Email;

public class UserDetailsRequestModel {
	@NotNull(message="this field is required")
	private String firstname;
	@NotNull(message="this field is required")
	private String lastname;
	@Email
	@NotNull(message="this field is required")
	private String email;
	@Size(min=8, max=16, message="this field is required")
	@NotNull(message="this field is required")
	private String password;
	
	public UserDetailsRequestModel(String firstname, String lastname, String email, String password) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
