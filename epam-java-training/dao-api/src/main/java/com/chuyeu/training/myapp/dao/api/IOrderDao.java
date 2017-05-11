package com.chuyeu.training.myapp.dao.api;

import java.util.List;

import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.dao.api.filters.OrderFilter;
import com.chuyeu.training.myapp.datamodel.Order;

public interface IOrderDao extends AbstractDao<Order>{

	List<Order> getAll(CommonFilter commonFilter, OrderFilter orderFilter);

	Integer save(Order order);

	void update(Order order);

}
