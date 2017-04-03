package com.chuyeu.training.myapp.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.IOrderItemDao;
import com.chuyeu.training.myapp.datamodel.OrderItem;
import com.chuyeu.training.myapp.services.IOrderItemService;
import com.chuyeu.training.myapp.services.IProductVariantService;
import com.chuyeu.training.myapp.services.entity.ProductVariantEntity;

@Service
public class OrderItemServiceImpl implements IOrderItemService {

	@Inject
	private IOrderItemDao orderItemDao;
	
	@Inject
	private IProductVariantService productVariantService;

	@Override
	public List<OrderItem> getAll() {
		return orderItemDao.getAll();
	}

	@Override
	public OrderItem get(Integer id) {
		OrderItem orderItem = orderItemDao.get(id);
		ProductVariantEntity productVariant = productVariantService.getProductVariant(orderItem.getProductVariantId());
		// TODO get Orders
		return orderItem;
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
