package com.chuyeu.training.myapp.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IOrderItemDao;
import com.chuyeu.training.myapp.datamodel.OrderItem;

@Repository
public class OrderItemDaoImpl extends AbstractDaoImpl<OrderItem> implements IOrderItemDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	protected OrderItemDaoImpl() {
        super(OrderItem.class);
    }

	@Override
	public List<OrderItem> getAll(Integer orderId) {
		return jdbcTemplate.query("select * from order_item where order_id = ? ", new Object[] { orderId },
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

}
