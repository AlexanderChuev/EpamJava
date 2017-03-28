package com.chuyeu.training.myapp.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chuyeu.training.myapp.datamodel.OrderItem;
import com.chuyeu.training.myapp.datamodel.ProductVariant;

public class OrderItemMapper implements RowMapper<OrderItem>{

	@Override
	public OrderItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		
	/*	ProductVariant productVariant = new ProductVariant();
		productVariant.se
		
		
		
		
		
		OrderItem orderItem = new OrderItem();
		orderItem.setId(rs.getInt("id"));
		orderItem.setOrder(rs.getInt("id"));
		orderItem.setProductVariant(rs.getInt("id"));
		orderItem.setQuantity(rs.getInt("order_quantity"));*/
		
		return null;
	}

}
