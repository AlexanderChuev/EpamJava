package com.chuyeu.training.myapp.dao.api;

import java.util.List;

import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.datamodel.UserProfile;

public interface IUserProfileDao extends AbstractDao<UserProfile>{

	List<UserProfile> getAll(CommonFilter commonFilter);

	UserProfile insert(UserProfile userProfile);

	void update(UserProfile userProfile);

	Integer getUserProfileQuantity();
}
