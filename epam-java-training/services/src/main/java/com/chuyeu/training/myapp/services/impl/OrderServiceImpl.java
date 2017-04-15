package com.chuyeu.training.myapp.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.api.IOrdersDao;
import com.chuyeu.training.myapp.dao.api.filters.OrderFilter;
import com.chuyeu.training.myapp.datamodel.Order;
import com.chuyeu.training.myapp.services.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService {

	@Inject
	private IOrdersDao ordersDao;

	@Override
	public List<Order> getAll(OrderFilter orderFilter) {
		return ordersDao.getAll(orderFilter);
	}

	@Override
	public void delete(Integer id) {
		ordersDao.delete(id);
	}

	@Override
	public Integer save(Order order) {
		return ordersDao.save(order);
	}

	@Override
	public void update(Order order) {
		ordersDao.update(order);
	}

	@Override
	public Order get(Integer id) {
		return ordersDao.get(id);
	}

}
