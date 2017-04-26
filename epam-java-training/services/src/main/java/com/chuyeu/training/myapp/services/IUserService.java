package com.chuyeu.training.myapp.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.datamodel.UserCredentials;
import com.chuyeu.training.myapp.datamodel.UserProfile;

public interface IUserService {
	
	UserCredentials getByEmailAndPassword(String email, String password);
	
	@Transactional
	UserProfile registration(UserProfile userProfile, UserCredentials userCredentials);

    UserProfile getUserProfile(Integer id);

    UserCredentials getUserCredentials(Integer id);

    @Transactional
    void update(UserProfile profile);

    List<UserProfile> getAll(CommonFilter commonFilter);

    @Transactional
    void update(UserCredentials credentials);
    
    @Transactional
	void delete(Integer id);
    
    Integer getUserProfileQuantity();

}
