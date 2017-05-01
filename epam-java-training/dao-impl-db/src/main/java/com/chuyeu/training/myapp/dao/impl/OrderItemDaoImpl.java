package com.chuyeu.training.myapp.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IOrderItemDao;
import com.chuyeu.training.myapp.datamodel.OrderItem;

@Repository
public class OrderItemDaoImpl implements IOrderItemDao {

	@Inject
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<OrderItem> getAll(Integer orderId) {

		return jdbcTemplate.query("select * from order_item where order_id = ? ", new Object[] { orderId }, new BeanPropertyRowMapper<OrderItem>(OrderItem.class));
	}

	@Override
	public OrderItem get(Integer id) {
		return jdbcTemplate.queryForObject("select * from order_item where id = ? ", new Object[] { id },
				new BeanPropertyRowMapper<OrderItem>(OrderItem.class));

	}

	@Override
	public void insert(OrderItem orderItem) {
		jdbcTemplate.update("insert into order_item (product_variant_id, order_quantity, order_id) values(?, ?, ?)",
				orderItem.getProductVariantId(), orderItem.getOrderQuantity(), orderItem.getOrderId());
	}

	@Override
	public void update(OrderItem orderItem) {
		jdbcTemplate.update(
				"update order_item set product_variant_id = ?, order_quantity = ?, order_id = ? " + "where id = ?",
				orderItem.getProductVariantId(), orderItem.getOrderQuantity(), orderItem.getOrderId(),
				orderItem.getId());
	}

	@Override
	public void delete(Integer id) {
		jdbcTemplate.update("delete from order_item where id=" + id);

	}

}
