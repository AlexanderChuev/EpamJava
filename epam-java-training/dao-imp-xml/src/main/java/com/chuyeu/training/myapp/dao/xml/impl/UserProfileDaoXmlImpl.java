package com.chuyeu.training.myapp.dao.xml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IUserProfileDao;
import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.datamodel.UserProfile;

@Repository
public class UserProfileDaoXmlImpl implements IUserProfileDao {

	@Override
	public List<UserProfile> getAll(CommonFilter commonFilter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile insert(UserProfile userProfile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserProfile update(UserProfile userProfile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer getUserProfileQuantity() {
		// TODO Auto-generated method stub
		return null;
	}

}
