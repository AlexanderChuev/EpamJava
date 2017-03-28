package com.chuyeu.training.myapp.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.IUserProfileDao;
import com.chuyeu.training.myapp.datamodel.UserProfile;
import com.chuyeu.training.myapp.services.IUserProfileService;

@Service
public class UserProfileServiceImpl implements IUserProfileService {

	@Inject
	private IUserProfileDao userProfileDao;

	@Override
	public List<UserProfile> getAll() {
		return userProfileDao.getAll();
	}

	@Override
	public UserProfile get(Integer id) {
		return userProfileDao.get(id);
	}

	@Override
	public void delete(Integer id) {
		userProfileDao.delete(id);
	}

	@Override
	public UserProfile saveOrUpdate(UserProfile entity) {
		if (entity.getId() == null) {
			return userProfileDao.insert(entity);
		} else {
			return userProfileDao.update(entity);
		}
	}

}
