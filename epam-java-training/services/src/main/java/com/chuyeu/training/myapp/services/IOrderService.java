package com.chuyeu.training.myapp.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.datamodel.Order;

public interface IOrderService {

	List<Order> getAll(CommonFilter commonFilter);
	
	Order get(Integer id);

	@Transactional
	Integer save(Order order);

	@Transactional
	void update(Order order);

	@Transactional
	void delete(Integer id);

}
