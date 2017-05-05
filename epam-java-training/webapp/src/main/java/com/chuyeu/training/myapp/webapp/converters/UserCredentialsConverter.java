package com.chuyeu.training.myapp.webapp.converters;

import org.springframework.core.convert.converter.Converter;

import com.chuyeu.training.myapp.datamodel.UserCredentials;
import com.chuyeu.training.myapp.webapp.models.UserCredentialsModel;

public class UserCredentialsConverter implements Converter<UserCredentials, UserCredentialsModel> {

	@Override
	public UserCredentialsModel convert(UserCredentials userCredentials) {
		UserCredentialsModel userCredentialsModel = new UserCredentialsModel();
		userCredentialsModel.setEmail(userCredentials.getEmail());
		userCredentialsModel.setUserRole(userCredentials.getUserRole().toString());
		return userCredentialsModel;
	}

}
