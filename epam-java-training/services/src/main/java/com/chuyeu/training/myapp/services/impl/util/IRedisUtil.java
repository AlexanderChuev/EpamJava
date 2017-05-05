package com.chuyeu.training.myapp.services.impl.util;

import com.chuyeu.training.myapp.datamodel.UserCredentials;

public interface IRedisUtil {

	String [] check(String [] credentials);
	void write(String [] credentials, UserCredentials user);
}
