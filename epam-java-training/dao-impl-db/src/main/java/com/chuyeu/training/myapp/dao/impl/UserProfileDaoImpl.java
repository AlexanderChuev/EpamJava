package com.chuyeu.training.myapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.IUserProfileDao;
import com.chuyeu.training.myapp.dao.mapper.UserProfileMapper;
import com.chuyeu.training.myapp.datamodel.UserProfile;

@Repository
public class UserProfileDaoImpl implements IUserProfileDao{

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<UserProfile> getAll() {
		return jdbcTemplate.query("select * from user_profile f left join user_credentials s on f.user_credentials_id = s.id", new UserProfileMapper());
	}

	@Override
	public UserProfile get(Integer id) {
		return jdbcTemplate.queryForObject("select * from user_profile where id = ? ", new Object []{ id }, new BeanPropertyRowMapper<UserProfile>(UserProfile.class));
	}

	@Override
	public UserProfile insert(UserProfile userProfile) {
		final String INSERT_SQL = "insert into user_profile (first_name, last_name, user_credentials_id) values(?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, userProfile.getFirstName());
				ps.setString(2, userProfile.getLastName());
				ps.setInt(3, userProfile.getUserCredentials().getId());
				return ps;
			}
		}, keyHolder);

		userProfile.setId(keyHolder.getKey().intValue());

		return userProfile;
	}

	@Override
	public UserProfile update(UserProfile userProfile) {
		jdbcTemplate.update("update user_profile set first_name = ?, last_name = ?, user_credentials_id = ? "
				+ "where id = ?" , userProfile.getFirstName(), userProfile.getLastName(), userProfile.getUserCredentials().getId(), userProfile.getId());
		return get(userProfile.getId());  // ничего не возвращает
	}

	@Override
	public void delete(Integer id) {
		jdbcTemplate.update("delete from user_profile where id=" + id);	
		
	}

}
