package com.chuyeu.training.myapp.dao.api;

import java.util.List;

import com.chuyeu.training.myapp.datamodel.Order;
import com.chuyeu.training.myapp.datamodel.OrderStatus;

public interface IOrdersDao {

	List<Order> getAll();

	Order get(Integer id);

	Order insert(Order order);

	Order update(Order order);

	void delete(Integer id);

	Order getOrderByStatus(Integer id, OrderStatus orderStatus);

}
