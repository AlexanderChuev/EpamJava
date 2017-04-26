package com.chuyeu.training.myapp.dao.api;

import java.util.List;

import com.chuyeu.training.myapp.datamodel.OrderItem;

public interface IOrderItemDao {

	List<OrderItem> getAll();

	OrderItem get(Integer id);

	void insert(OrderItem orderItem);

	void update(OrderItem orderItem);

	void delete(Integer id);
}
