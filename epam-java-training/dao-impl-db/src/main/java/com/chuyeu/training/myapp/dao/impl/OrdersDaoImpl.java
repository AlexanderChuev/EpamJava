package com.chuyeu.training.myapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.IOrdersDao;
import com.chuyeu.training.myapp.datamodel.Order;

@Repository
public class OrdersDaoImpl implements IOrdersDao{
	
	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Order> getAll() {
		return jdbcTemplate.query("select * from orders", new BeanPropertyRowMapper<Order>(Order.class));
	}

	@Override
	public Order get(Integer id) {
		return null;
	}

	@Override
	public Order insert(Order order) {
		
		final String INSERT_SQL = "insert into orders (created, user_profile_id, total_price) values(?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setTimestamp(1, new Timestamp(new Date().getTime()));
				ps.setInt(2, order.getUserProfile().getId());
				ps.setDouble(3, order.getTotalPrice());
				return ps;
			}
		}, keyHolder);

		order.setId(keyHolder.getKey().intValue());

		return order;
		
	}

	@Override
	public Order update(Order order) {
		return null;
	}

	@Override
	public void delete(Integer id) {
		
	}


}
