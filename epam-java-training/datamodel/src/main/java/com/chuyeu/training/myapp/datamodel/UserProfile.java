package com.chuyeu.training.myapp.datamodel;

public class UserProfile extends AbstractModel {

	private static final long serialVersionUID = -3738771608596521540L;

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

	@Override
	public String toString() {
		return "UserProfile [firstName=" + firstName + ", lastName=" + lastName + ", userCredentialsId="
				+ userCredentialsId + "]";
	}

}
