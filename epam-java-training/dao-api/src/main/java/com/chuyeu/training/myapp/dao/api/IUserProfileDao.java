package com.chuyeu.training.myapp.dao.api;

import java.util.List;

import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.datamodel.UserProfile;

public interface IUserProfileDao {

	List<UserProfile> getAll(CommonFilter commonFilter);

	UserProfile get(Integer id);

	UserProfile insert(UserProfile userProfile);

	UserProfile update(UserProfile userProfile);

	void delete(Integer id);
	
	Integer getUserProfileQuantity();
}
