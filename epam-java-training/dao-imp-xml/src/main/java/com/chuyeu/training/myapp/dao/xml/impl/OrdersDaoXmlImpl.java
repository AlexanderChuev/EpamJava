package com.chuyeu.training.myapp.dao.xml.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IOrdersDao;
import com.chuyeu.training.myapp.datamodel.Order;
import com.chuyeu.training.myapp.datamodel.OrderStatus;

@Repository
public class OrdersDaoXmlImpl implements IOrdersDao {

	@Override
	public List<Order> getAll() {
		return null;
	}

	@Override
	public Order get(Integer id) {
		return null;
	}

	@Override
	public Order insert(Order order) {
		return null;
	}

	@Override
	public Order update(Order order) {
		return null;
	}

	@Override
	public void delete(Integer id) {
	}

	@Override
	public Order getOrderByStatus(Integer id, OrderStatus status) {
		return null;
	}

}
