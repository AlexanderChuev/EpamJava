package com.chuyeu.training.myapp.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.IOrdersDao;
import com.chuyeu.training.myapp.datamodel.Order;
import com.chuyeu.training.myapp.services.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService {

	@Inject
	private IOrdersDao ordersDao;

	@Override
	public List<Order> getAll() {
		return ordersDao.getAll();
	}

	@Override
	public Order get(Integer id) {
		return ordersDao.get(id);
	}

	@Override
	public void delete(Integer id) {
		ordersDao.delete(id);
	}

	@Override
	public Order saveOrUpdate(Order entity) {
		if (entity.getId() == null) {
			return ordersDao.insert(entity);
		} else {
			return ordersDao.update(entity);
		}
	}

}
