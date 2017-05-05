package com.chuyeu.training.myapp.webapp.converters;

import org.springframework.core.convert.converter.Converter;

import com.chuyeu.training.myapp.datamodel.UserProfile;
import com.chuyeu.training.myapp.webapp.models.UserProfileModel;

public class UserProfileConverter implements Converter<UserProfile, UserProfileModel> {

	@Override
	public UserProfileModel convert(UserProfile userProfile) {
		UserProfileModel userProfileModel = new UserProfileModel();
		userProfileModel.setFirstName(userProfile.getFirstName());
		userProfileModel.setLastName(userProfile.getLastName());
		userProfileModel.setUserCredentialsId(userProfile.getUserCredentialsId());
		return userProfileModel;
	}
}
