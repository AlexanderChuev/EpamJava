package com.chuyeu.training.myapp.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.chuyeu.training.myapp.datamodel.UserCredentials;
import com.chuyeu.training.myapp.datamodel.UserProfile;

public interface IUserService {
	
	@Transactional
    void register(UserProfile userProfile, UserCredentials userCredentials);

    UserProfile getProfile(Integer id);

    UserCredentials getCredentials(Integer id);

    @Transactional
    void update(UserProfile profile);

    @Transactional
    void delete(Integer id);

    List<UserProfile> getAll();

    @Transactional
    void update(UserCredentials credentials);

}
