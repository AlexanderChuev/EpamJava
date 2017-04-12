package com.chuyeu.training.myapp.dao.api;

import java.util.List;

import com.chuyeu.training.myapp.datamodel.UserCredentials;

public interface IUserCredentialsDao {

	List<UserCredentials> getAll();

	UserCredentials get(Integer id);

	UserCredentials insert(UserCredentials userCredentials);

	UserCredentials update(UserCredentials userCredentials);

	void delete(Integer id);
}
