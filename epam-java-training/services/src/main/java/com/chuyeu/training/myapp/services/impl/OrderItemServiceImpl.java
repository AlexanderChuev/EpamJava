package com.chuyeu.training.myapp.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.api.IOrderItemDao;
import com.chuyeu.training.myapp.datamodel.OrderItem;
import com.chuyeu.training.myapp.services.IOrderItemService;

@Service
public class OrderItemServiceImpl implements IOrderItemService {

	private final Logger LOGGER = LoggerFactory.getLogger(OrderItemServiceImpl.class);
	
	@Inject
	private IOrderItemDao orderItemDao;
	
	@Override
	public List<OrderItem> getAllByOrderId(Integer orderId) {
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
	public OrderItem saveOrUpdate(OrderItem orderItem) {
		if (orderItem.getId() == null) {
			return orderItemDao.insert(orderItem);
		} else {
			return orderItemDao.update(orderItem);
		}
	}

}
