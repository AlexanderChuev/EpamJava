package com.chuyeu.training.myapp.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chuyeu.training.myapp.datamodel.UserCredentials;
import com.chuyeu.training.myapp.datamodel.UserProfile;
import com.chuyeu.training.myapp.datamodel.UserRole;

public class UserProfileMapper implements RowMapper<UserProfile> {

	@Override
	public UserProfile mapRow(ResultSet rs, int rowNum) throws SQLException {

		UserCredentials userCredentials = new UserCredentials();
		
		userCredentials.setEmail(rs.getString("email"));
		userCredentials.setPassword(rs.getString("password"));
		userCredentials.setUserRole(UserRole.valueOf(rs.getString("user_role")));
		
		
		UserProfile userProfile = new UserProfile();
		
		userProfile.setId(rs.getInt("id"));
		userProfile.setFirstName(rs.getString("first_name"));
		userProfile.setLastName(rs.getString("last_name"));
		userProfile.setUserCredentials(userCredentials);

		return userProfile;
	}

}
