package com.chuyeu.training.myapp.datamodel;

public class UserProfile extends AbstractModel {

	private static final long serialVersionUID = -3738771608596521540L;

	private String firstName;
	private String lastName;
	private UserCredentials userCredentials;

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

	public UserCredentials getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}

	@Override
	public String toString() {
		return "UserProfile [firstName=" + firstName + ", lastName=" + lastName + ", userCredentials=" + userCredentials
				+ "]";
	}
	
}
