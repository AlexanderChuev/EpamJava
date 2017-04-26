package com.chuyeu.training.myapp.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.api.IOrdersDao;
import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.datamodel.Order;
import com.chuyeu.training.myapp.services.IOrderService;

@Service
public class OrderServiceImpl implements IOrderService {

	private final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

	@Inject
	private IOrdersDao ordersDao;

	@Override
	public List<Order> getAll(CommonFilter commonFilter) {
		return ordersDao.getAll(commonFilter);
	}

	@Override
	public void delete(Integer id) {
		ordersDao.delete(id);
		LOGGER.info("Delete order with id " + id);
	}

	@Override
	public Integer save(Order order) {
		LOGGER.info("Insert Order with Created={}. UserProfileId={}. OrderStatus={}", order.getCreated(),
				order.getUserProfileId(), order.getOrderStatus());
		return ordersDao.save(order);
	}

	@Override
	public void update(Order order) {
		ordersDao.update(order);
		LOGGER.info("Update Order with id={}. Created={}. UserProfileId={}. TotalPrice={}. OrderStatus={}",
				order.getId(), order.getCreated(), order.getUserProfileId(), order.getTotalPrice(),
				order.getOrderStatus());
	}

	@Override
	public Order get(Integer id) {
		return ordersDao.get(id);
	}

}
