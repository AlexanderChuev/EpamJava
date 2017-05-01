package com.chuyeu.training.myapp.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.api.IUserCredentialsDao;
import com.chuyeu.training.myapp.dao.api.IUserProfileDao;
import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.datamodel.UserCredentials;
import com.chuyeu.training.myapp.datamodel.UserProfile;
import com.chuyeu.training.myapp.services.IUserService;

@Service
public class UserSeviceImpl implements IUserService {

	private final Logger LOGGER = LoggerFactory.getLogger(UserSeviceImpl.class);

	@Inject
	private IUserProfileDao userProfileDao;

	@Inject
	private IUserCredentialsDao userCredentialsDao;

	///
	@Override
	public UserCredentials getByEmailAndPassword(String email, String password) {
		return userCredentialsDao.find(email, password);
	}

	@Override
	public UserCredentials getUserCredentials(Integer id) {
		return userCredentialsDao.get(id);
	}

	@Override
	public void update(UserCredentials userCredentials) {
		userCredentialsDao.update(userCredentials);
		LOGGER.info("Update userCredentials with id={}. Email={}. UserRole={}", userCredentials.getId(),
				userCredentials.getEmail(), userCredentials.getUserRole().toString());
	}

	@Override
	public UserProfile registration(UserProfile userProfile, UserCredentials userCredentials) {

		Integer userCredentialsId = userCredentialsDao.add(userCredentials);
		userProfile.setUserCredentialsId(userCredentialsId);

		LOGGER.info("Insert user  with id={}. Email={}. UserRole={}. FirstName={}. LastName={}. ", userCredentialsId,
				userCredentials.getEmail(), userCredentials.getUserRole().toString(), userProfile.getFirstName(),
				userProfile.getLastName());

		return userProfileDao.insert(userProfile);
	}

	@Override
	public UserProfile getUserProfile(Integer id) {
		return userProfileDao.get(id);
	}

	@Override
	public void update(UserProfile userProfile) {
		userProfileDao.update(userProfile);
		LOGGER.info("Update UserProfile with id={}. FirstName={}. LastName={}. UserCredentialsId={}",
				userProfile.getId(), userProfile.getFirstName(), userProfile.getLastName(),
				userProfile.getUserCredentialsId());
	}

	@Override
	public List<UserProfile> getAll(CommonFilter commonFilter) {
		return userProfileDao.getAll(commonFilter);
	}

	@Override
	public void delete(Integer id) {
		UserProfile userProfile = userProfileDao.get(id);
		userProfileDao.delete(id);
		LOGGER.info("Delete userProfile with id " + id);
		userCredentialsDao.delete(userProfile.getUserCredentialsId());
		LOGGER.info("Delete userCredentials with id " + userProfile.getUserCredentialsId());
	}

	@Override
	public Integer getUserProfileQuantity() {
		return userProfileDao.getUserProfileQuantity();
	}

}
