package com.chuyeu.training.myapp.dao.api;

import java.util.List;

import com.chuyeu.training.myapp.datamodel.OrderItem;

public interface IOrderItemDao extends AbstractDao<OrderItem>{

	List<OrderItem> getAll(Integer orderId);

	void insert(OrderItem orderItem);

	void update(OrderItem orderItem);

}
