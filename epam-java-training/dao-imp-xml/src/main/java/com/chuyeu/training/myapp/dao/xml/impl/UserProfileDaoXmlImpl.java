package com.chuyeu.training.myapp.dao.xml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IUserProfileDao;
import com.chuyeu.training.myapp.datamodel.UserProfile;

@Repository
public class UserProfileDaoXmlImpl implements IUserProfileDao{

	
	@Override
	public List<UserProfile> getAll() {
		return null;
	}

	@Override
	public UserProfile get(Integer id) {
		return null;
	}

	@Override
	public UserProfile insert(UserProfile userProfile) {
		return null;
	}

	@Override
	public UserProfile update(UserProfile userProfile) {
		return null;
	}

	@Override
	public void delete(Integer id){
	}

}
