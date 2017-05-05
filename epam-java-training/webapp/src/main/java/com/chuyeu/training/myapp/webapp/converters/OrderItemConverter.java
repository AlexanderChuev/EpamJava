package com.chuyeu.training.myapp.webapp.converters;

import org.springframework.core.convert.converter.Converter;

import com.chuyeu.training.myapp.datamodel.OrderItem;
import com.chuyeu.training.myapp.webapp.models.OrderItemModel;

public class OrderItemConverter implements Converter<OrderItem, OrderItemModel>{

	@Override
	public OrderItemModel convert(OrderItem orderItem) {
		OrderItemModel orderItemModel = new OrderItemModel();
		orderItemModel.setId(orderItem.getId());
		orderItemModel.setProductVariantId(orderItem.getProductVariantId());
		orderItemModel.setOrderQuantity(orderItem.getOrderQuantity());
		return orderItemModel;
	}

}
