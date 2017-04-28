package com.chuyeu.training.myapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IUserCredentialsDao;
import com.chuyeu.training.myapp.datamodel.UserCredentials;

@Repository
public class UserCredentialsDaoImpl implements IUserCredentialsDao{

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<UserCredentials> getAll() throws UnsupportedOperationException{
		/*return jdbcTemplate.query("select * from user_credentials", new BeanPropertyRowMapper<UserCredentials>(UserCredentials.class));*/
		return null;
	}

	@Override
	public UserCredentials get(Integer id) {
		return jdbcTemplate.queryForObject("select * from user_credentials where id = ? ", new Object []{ id }, new BeanPropertyRowMapper<UserCredentials>(UserCredentials.class));
	}

	@Override
	public Integer add(UserCredentials userCredentials) throws DuplicateKeyException{
		final String INSERT_SQL = "insert into user_credentials (email, password, user_role) values(?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, userCredentials.getEmail());
				ps.setString(2, userCredentials.getPassword());
				ps.setString(3, userCredentials.getUserRole().toString());
				return ps;
			}
		}, keyHolder);

		return keyHolder.getKey().intValue();
	}

	@Override
	public void update(UserCredentials userCredentials) {
		jdbcTemplate.update("update user_credentials set email = ?, password = ?, user_role = ? "
				+ "where id = ?" , userCredentials.getEmail(), userCredentials.getPassword(), userCredentials.getUserRole().toString(), userCredentials.getId());
	}

	@Override
	public UserCredentials find(String email, String password) {
		return jdbcTemplate.queryForObject("select * from user_credentials where email = ? and password = ?", new Object []{email, password}, new BeanPropertyRowMapper<UserCredentials>(UserCredentials.class));
	}

	@Override
	public void delete(Integer id) {
		jdbcTemplate.update("delete from user_credentials where id = " + id);
		
	}

}
