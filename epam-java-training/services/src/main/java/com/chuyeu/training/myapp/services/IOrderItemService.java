package com.chuyeu.training.myapp.services;

import java.util.List;

import com.chuyeu.training.myapp.datamodel.OrderItem;

public interface IOrderItemService{

	List<OrderItem> getAll();
	
	OrderItem get(Integer id);
	
	OrderItem saveOrUpdate(OrderItem orderItem);
	
	void delete (Integer id);
}
