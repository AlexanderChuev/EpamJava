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

import com.chuyeu.training.myapp.dao.api.IUserProfileDao;
import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.datamodel.UserProfile;

@Repository
public class UserProfileDaoImpl  extends AbstractDaoImpl<UserProfile> implements IUserProfileDao{

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	protected UserProfileDaoImpl() {
        super(UserProfile.class);
    }
	
	@Override
	public List<UserProfile> getAll(CommonFilter commonFilter) {
		String sql = createSql(commonFilter);
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserProfile>(UserProfile.class));
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
				ps.setInt(3, userProfile.getUserCredentialsId());
				return ps;
			}
		}, keyHolder);

		userProfile.setId(keyHolder.getKey().intValue());

		return userProfile;
	}

	@Override
	public void update(UserProfile userProfile) {
		jdbcTemplate.update("update user_profile set first_name = ?, last_name = ?, user_credentials_id = ? "
				+ "where id = ?" , userProfile.getFirstName(), userProfile.getLastName(), userProfile.getUserCredentialsId(), userProfile.getId());
	}
	
	@Override
	public Integer getUserProfileQuantity() {
		return jdbcTemplate.queryForObject("select count (*) from user_profile", Integer.class);
	}
	
	private String createSql(CommonFilter commonFilter) {

		Integer offset = commonFilter.getLimit() * (commonFilter.getPageNumber() - 1);

		StringBuilder stringBuilder = new StringBuilder("select * from user_profile ");

		if (commonFilter.getSort() != null && commonFilter.getSort().getColumn() != null) {
			stringBuilder.append("order by ").append(commonFilter.getSort().getColumn());

			if ("desc".equals(commonFilter.getSort().getDirection())) {
				stringBuilder.append(" desc");
			}
		}

		stringBuilder.append(" limit ");
		stringBuilder.append(commonFilter.getLimit());
		stringBuilder.append(" offset ");
		stringBuilder.append(offset);
		return stringBuilder.toString();
	}

}
