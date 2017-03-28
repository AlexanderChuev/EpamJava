package com.chuyeu.training.myapp.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.IUserCredentialsDao;
import com.chuyeu.training.myapp.datamodel.UserCredentials;
import com.chuyeu.training.myapp.services.IUserCredentialsService;

@Service
public class UserCredentialsServiceImpl implements IUserCredentialsService {

	@Inject
	private IUserCredentialsDao userCredentialsDao;

	@Override
	public List<UserCredentials> getAll() {
		return userCredentialsDao.getAll();
	}

	@Override
	public UserCredentials get(Integer id) {
		return userCredentialsDao.get(id);
	}

	@Override
	public void delete(Integer id) {
		userCredentialsDao.delete(id);
	}

	@Override
	public UserCredentials saveOrUpdate(UserCredentials entity) {
		if (entity.getId() == null) {
			return userCredentialsDao.insert(entity);
		} else {
			return userCredentialsDao.update(entity);
		}
	}

}
