package com.mostafa.demo.ui.model.request;

public class Creds {
	private String email;
	private String password;

	public Creds() {}
	
	public Creds(String firstname, String lastname) {
		this.email = firstname;
		this.password = lastname;
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
