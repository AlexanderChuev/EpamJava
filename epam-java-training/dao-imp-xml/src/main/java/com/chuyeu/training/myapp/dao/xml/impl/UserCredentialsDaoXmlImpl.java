package com.chuyeu.training.myapp.dao.xml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IUserCredentialsDao;
import com.chuyeu.training.myapp.datamodel.UserCredentials;

@Repository
public class UserCredentialsDaoXmlImpl implements IUserCredentialsDao {

	@Override
	public List<UserCredentials> getAll() throws UnsupportedOperationException {
		return null;
	}

	@Override
	public UserCredentials get(Integer id) {
		return null;
	}

	@Override
	public UserCredentials insert(UserCredentials userCredentials) {
		return null;
	}

	@Override
	public UserCredentials update(UserCredentials userCredentials) {
		return null;
	}

	@Override
	public void delete(Integer id) {
	}

}
