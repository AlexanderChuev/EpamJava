package com.chuyeu.training.myapp.webapp.converters;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;

import com.chuyeu.training.myapp.datamodel.Order;
import com.chuyeu.training.myapp.datamodel.OrderStatus;
import com.chuyeu.training.myapp.webapp.models.OrderModel;

public class OrderModelConverter implements Converter<OrderModel, Order>{

	@Override
	public Order convert(OrderModel orderModel) {
		Order order = new Order();
		order.setCreated(new Date());
		order.setUserProfileId(orderModel.getUserProfileId());
		order.setOrderStatus(OrderStatus.CART);
		return order;
	}

}
