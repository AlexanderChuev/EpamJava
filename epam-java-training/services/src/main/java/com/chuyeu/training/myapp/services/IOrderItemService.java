package com.chuyeu.training.myapp.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.chuyeu.training.myapp.datamodel.OrderItem;

public interface IOrderItemService{

	List<OrderItem> getAll();
	
	OrderItem get(Integer id);
	
	@Transactional
	OrderItem saveOrUpdate(OrderItem orderItem);
	
	@Transactional
	void delete (Integer id);
}
