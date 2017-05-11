package com.chuyeu.training.myapp.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chuyeu.training.myapp.datamodel.Order;
import com.chuyeu.training.myapp.datamodel.OrderStatus;

public class OrderMapper implements RowMapper<Order> {

	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {

		Order order = new Order();
		order.setId(rs.getInt("id"));
		order.setCreated(rs.getTimestamp("created"));
		order.setUserProfileId(rs.getInt("user_profile_id"));
		order.setOrderStatus(OrderStatus.valueOf(rs.getString("order_status")));

		return order;
	}

}
