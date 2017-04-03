package com.chuyeu.training.myapp.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.IOrdersDao;
import com.chuyeu.training.myapp.datamodel.Order;
import com.chuyeu.training.myapp.datamodel.OrderStatus;
import com.chuyeu.training.myapp.services.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService {

	@Inject
	private IOrdersDao ordersDao;

	// скорее всего не нужен
	@Override
	public List<Order> getAll() {

		List<Order> all = ordersDao.getAll();

		return all;
	}

	@Override
	public void delete(Integer id) {
		ordersDao.delete(id);
	}

	@Override
	public Order saveOrUpdate(Order order) {
		if (order.getId() == null) {
			return ordersDao.insert(order);
		} else {
			return ordersDao.update(order);
		}
	}

	@Override
	public Order getOrderByStatus(Integer id, OrderStatus orderStatus) {
		return ordersDao.getOrderByStatus(id, orderStatus);
	}

}
