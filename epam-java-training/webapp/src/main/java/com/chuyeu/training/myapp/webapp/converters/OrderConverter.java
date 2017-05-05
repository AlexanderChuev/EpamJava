package com.chuyeu.training.myapp.webapp.converters;

import org.springframework.core.convert.converter.Converter;

import com.chuyeu.training.myapp.datamodel.Order;
import com.chuyeu.training.myapp.webapp.models.OrderModel;

public class OrderConverter implements Converter<Order, OrderModel>{

	@Override
	public OrderModel convert(Order order) {
		OrderModel orderModel = new OrderModel();
		orderModel.setId(order.getId());
		orderModel.setCreated(order.getCreated());
		orderModel.setUserProfileId(order.getUserProfileId());
		orderModel.setTotalPrice(order.getTotalPrice());
		orderModel.setOrderStatus(order.getOrderStatus());
		return orderModel;
	}

}
