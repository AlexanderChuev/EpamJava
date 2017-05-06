package com.chuyeu.training.myapp.dao.api;

import java.util.List;

import com.chuyeu.training.myapp.datamodel.UserCredentials;

public interface IUserCredentialsDao extends AbstractDao<UserCredentials> {

	List<UserCredentials> getAll();

	Integer add(UserCredentials userCredentials);

	void update(UserCredentials userCredentials);

	UserCredentials find(String email, String password);

}
