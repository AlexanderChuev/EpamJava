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
		return orderItemDao.getAll(orderId);
	}

	@Override
	public OrderItem get(Integer id) {
		return orderItemDao.get(id);
	}

	@Override
	public void delete(Integer id) {
		orderItemDao.delete(id);
		LOGGER.info("Delete order item with id " + id);
	}

	@Override
	public void saveOrUpdate(OrderItem orderItem) {
		if (orderItem.getId() == null) {
			orderItemDao.insert(orderItem);
			LOGGER.info("Insert OrderItem with ProductVariantId={}. OrderQuantity={}. OrderId={}",
					orderItem.getProductVariantId(), orderItem.getOrderQuantity(), orderItem.getOrderId());
		} else {
			orderItemDao.update(orderItem);
			LOGGER.info("Update OrderItem with Id ={}. ProductVariantId={}. OrderQuantity={}. OrderId={}",
					orderItem.getId(), orderItem.getProductVariantId(), orderItem.getOrderQuantity(),
					orderItem.getOrderId());
		}

	}

}
