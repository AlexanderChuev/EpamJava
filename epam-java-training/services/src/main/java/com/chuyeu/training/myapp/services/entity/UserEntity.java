package com.chuyeu.training.myapp.services.entity;

import com.chuyeu.training.myapp.datamodel.UserCredentials;
import com.chuyeu.training.myapp.datamodel.UserProfile;

public class UserEntity {

	private UserProfile profile;
	private UserCredentials userCredentials;

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}

	public UserCredentials getUserCredentials() {
		return userCredentials;
	}

	public void setUserCredentials(UserCredentials userCredentials) {
		this.userCredentials = userCredentials;
	}

}
