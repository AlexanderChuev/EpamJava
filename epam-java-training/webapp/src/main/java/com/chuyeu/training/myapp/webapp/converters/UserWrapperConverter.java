package com.chuyeu.training.myapp.webapp.converters;

import org.springframework.core.convert.converter.Converter;

import com.chuyeu.training.myapp.datamodel.UserCredentials;
import com.chuyeu.training.myapp.datamodel.UserRole;
import com.chuyeu.training.myapp.webapp.models.UserWrapper;

public class UserWrapperConverter implements Converter<UserWrapper, UserCredentials>{

	@Override
	public UserCredentials convert(UserWrapper userWrapper) {
		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setEmail(userWrapper.getUserCredentialsModel().getEmail());
		userCredentials.setPassword(userWrapper.getUserCredentialsModel().getPassword());
		userCredentials.setUserRole(UserRole.CLIENT);
		return userCredentials;
	}

}
