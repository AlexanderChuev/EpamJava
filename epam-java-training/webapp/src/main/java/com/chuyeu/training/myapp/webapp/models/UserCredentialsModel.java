package com.chuyeu.training.myapp.webapp.models;

import com.chuyeu.training.myapp.datamodel.UserRole;

public class UserCredentialsModel {

	private String email;
	private UserRole userRole;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
}
