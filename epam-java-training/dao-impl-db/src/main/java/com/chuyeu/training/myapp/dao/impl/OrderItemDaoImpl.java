package com.chuyeu.training.myapp.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.IOrderItemDao;
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
		/*
		final String INSERT_SQL = "insert into attribute (attribute_name, value) values(?, ?)";

		KeyHolder keyHolder = new GeneratedKeyHolder();

		jdbcTemplate.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[] { "id" });
				ps.setString(1, attribute.getAttributeName());
				ps.setString(2, attribute.getValue());
				return ps;
			}
		}, keyHolder);

		attribute.setId(keyHolder.getKey().intValue());*/

		return orderItem;
	}

	@Override
	public OrderItem update(OrderItem orderItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
