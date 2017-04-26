package com.chuyeu.training.myapp.dao.api;

import java.util.List;

import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.datamodel.Order;

public interface IOrdersDao {

	List<Order> getAll(CommonFilter commonFilter);

	Integer save(Order order);

	void update(Order order);

	void delete(Integer id);
	
	Order get(Integer id);

}
