package com.chuyeu.training.myapp.dao.api;

import java.util.List;

import com.chuyeu.training.myapp.dao.api.filters.OrderFilter;
import com.chuyeu.training.myapp.datamodel.Order;

public interface IOrdersDao {

	List<Order> getAll(OrderFilter orderFilter);

	Integer save(Order order);

	void update(Order order);

	void delete(Integer id);
	
	Order get(Integer id);

}
