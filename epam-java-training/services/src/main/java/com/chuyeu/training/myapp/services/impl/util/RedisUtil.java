package com.chuyeu.training.myapp.services.impl.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chuyeu.training.myapp.datamodel.UserCredentials;
import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisConnection;

@Component
public class RedisUtil implements IRedisUtil {

	private RedisClient redisClient;

	@Autowired
	public void setRedisClient(RedisClient redisClient) {
		this.redisClient = redisClient;
	}

	@Override
	public String[] check(String[] credentials) {

		String[] userDataStorage = null;
		RedisConnection<String, String> connection = redisClient.connect();
		String userData = connection.get(credentials[0] + credentials[1]);
		if (userData != null) {
			userDataStorage = userData.split("!", 2);
		}
		System.out.println("Есть");
		connection.close();
		return userDataStorage;
	}

	@Override
	public void write(String[] credentials, UserCredentials user) {
		RedisConnection<String, String> connection = redisClient.connect();
		connection.set(credentials[0] + credentials[1],
				user.getId().toString() + "!" + user.getUserRole().toString());
		connection.expire(credentials[0] + credentials[1], 15);
		System.out.println("Записал");
		connection.close();
	}

}