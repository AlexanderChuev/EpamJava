package com.chuyeu.training.myapp.webapp.models;

import java.io.Serializable;

public class UserWrapper implements Serializable{

	private UserCredentialsModel userCredentialsModel;
	private UserProfileModel userProfileModel;

	public UserCredentialsModel getUserCredentialsModel() {
		return userCredentialsModel;
	}

	public void setUserCredentialsModel(UserCredentialsModel userCredentialsModel) {
		this.userCredentialsModel = userCredentialsModel;
	}

	public UserProfileModel getUserProfileModel() {
		return userProfileModel;
	}

	public void setUserProfileModel(UserProfileModel userProfileModel) {
		this.userProfileModel = userProfileModel;
	}

}
