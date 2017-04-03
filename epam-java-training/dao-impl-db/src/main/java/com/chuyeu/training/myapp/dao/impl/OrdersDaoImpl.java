package com.chuyeu.training.myapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.IOrdersDao;
import com.chuyeu.training.myapp.datamodel.Order;
import com.chuyeu.training.myapp.datamodel.OrderStatus;

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
		return jdbcTemplate.queryForObject("select * from orders where id = ? ", new Object[] { id },
				new BeanPropertyRowMapper<Order>(Order.class));
	}

	@Override
	public Order insert(Order order) {
		
		final String INSERT_SQL = "insert into orders (created, user_profile_id, total_price) values(?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setTimestamp(1, new Timestamp(order.getCreated().getTime()));
				ps.setInt(2, order.getUserProfileId());
				ps.setDouble(3, order.getTotalPrice());
				return ps;
			}
		}, keyHolder);

		order.setId(keyHolder.getKey().intValue());

		return order;
		
	}

	@Override
	public Order update(Order order) {
		jdbcTemplate.update("update orders set user_profile_id = ?, total_price = ? " + "where id = ?",
				order.getUserProfileId(), order.getTotalPrice(), order.getId());
		return get(order.getId()); 
	}

	@Override
	public void delete(Integer id) throws EmptyResultDataAccessException{
		jdbcTemplate.update("delete from orders where id=" + id);
	}

	@Override
	public Order getOrderByStatus(Integer id, OrderStatus status) {
		return jdbcTemplate.queryForObject("select * from orders where id = ? and order_status = ?", new Object[] { id, status.toString() },
				new BeanPropertyRowMapper<Order>(Order.class));
	}


}
