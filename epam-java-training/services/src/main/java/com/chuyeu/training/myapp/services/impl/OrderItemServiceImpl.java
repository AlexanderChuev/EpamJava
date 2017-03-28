package com.chuyeu.training.myapp.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.IOrderItemDao;
import com.chuyeu.training.myapp.datamodel.OrderItem;
import com.chuyeu.training.myapp.services.IOrderItemService;

@Service
public class OrderItemServiceImpl implements IOrderItemService {

	@Inject
	private IOrderItemDao orderItemDao;

	@Override
	public List<OrderItem> getAll() {
		return orderItemDao.getAll();
	}

	@Override
	public OrderItem get(Integer id) {
		
		return orderItemDao.get(id);
	}

	@Override
	public void delete(Integer id) {
		orderItemDao.delete(id);
	}

	@Override
	public OrderItem saveOrUpdate(OrderItem entity) {
		if (entity.getId() == null) {
			return orderItemDao.insert(entity);
		} else {
			return orderItemDao.update(entity);
		}
	}

}
