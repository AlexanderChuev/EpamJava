package com.chuyeu.training.myapp.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IOrderItemDao;
import com.chuyeu.training.myapp.datamodel.OrderItem;

@Repository
public class OrderItemDaoImpl implements IOrderItemDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<OrderItem> getAll() {
		
		return jdbcTemplate.query("select * from order_item", new BeanPropertyRowMapper<OrderItem>(OrderItem.class));
	}

	@Override
	public OrderItem get(Integer id) {
		return jdbcTemplate.queryForObject("select * from order_item where id = ? ", new Object[] { id },
				new BeanPropertyRowMapper<OrderItem>(OrderItem.class));
		
	}

	@Override
	public OrderItem insert(OrderItem orderItem) {
		
		final String INSERT_SQL = "insert into order_item (product_variant_id, order_quantity, orders_id) values(?, ?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setInt(1, orderItem.getProductVariantId());
				ps.setInt(2, orderItem.getOrderQuantity());
				ps.setInt(3, orderItem.getOrdersId());
				return ps;
			}
		}, keyHolder);

		orderItem.setId(keyHolder.getKey().intValue());

		return orderItem;
	}

	@Override
	public OrderItem update(OrderItem orderItem) {
		jdbcTemplate.update("update order_item set product_variant_id = ?, order_quantity = ?, orders_id = ? "
				+ "where id = ?" , orderItem.getProductVariantId(), orderItem.getOrderQuantity(), orderItem.getOrdersId(), orderItem.getId());
		return get(orderItem.getId());
	}

	@Override
	public void delete(Integer id) throws EmptyResultDataAccessException{
		jdbcTemplate.update("delete from product where id=" + id);		
		
	}

}
