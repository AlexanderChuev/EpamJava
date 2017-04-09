package com.chuyeu.training.myapp.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.chuyeu.training.myapp.datamodel.Order;
import com.chuyeu.training.myapp.datamodel.OrderStatus;

public interface IOrderService {

	List<Order> getAll();
	
	Order getOrderByStatus(Integer id, OrderStatus orderStatus);
	
	@Transactional
	Order saveOrUpdate(Order order);
	
	@Transactional
	void delete (Integer id);
	
	
}
