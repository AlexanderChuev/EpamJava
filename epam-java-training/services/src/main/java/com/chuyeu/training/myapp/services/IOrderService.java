package com.chuyeu.training.myapp.services;

import java.util.List;

import com.chuyeu.training.myapp.datamodel.Order;
import com.chuyeu.training.myapp.datamodel.OrderStatus;

public interface IOrderService {

	List<Order> getAll();
	
	Order getOrderByStatus(Integer id, OrderStatus orderStatus);
	
	Order saveOrUpdate(Order order);
	
	void delete (Integer id);
	
	
}
