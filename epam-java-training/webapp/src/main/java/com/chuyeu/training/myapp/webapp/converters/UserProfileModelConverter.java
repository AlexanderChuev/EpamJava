package com.chuyeu.training.myapp.webapp.converters;

import org.springframework.core.convert.converter.Converter;

import com.chuyeu.training.myapp.datamodel.UserProfile;
import com.chuyeu.training.myapp.webapp.models.UserWrapper;

public class UserProfileModelConverter implements Converter<UserWrapper, UserProfile>{

	@Override
	public UserProfile convert(UserWrapper userWrapper) {
		UserProfile userProfile = new UserProfile();
		userProfile.setFirstName(userWrapper.getUserProfileModel().getFirstName());
		userProfile.setLastName(userWrapper.getUserProfileModel().getLastName());
		return userProfile;
	}
}
