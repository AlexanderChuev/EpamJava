package com.chuyeu.training.myapp.webapp.models;

public class UserProfileModel {

	private String firstName;
	private String lastName;
	private Integer userCredentialsId;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getUserCredentialsId() {
		return userCredentialsId;
	}

	public void setUserCredentialsId(Integer userCredentialsId) {
		this.userCredentialsId = userCredentialsId;
	}

}
