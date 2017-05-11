package com.chuyeu.training.myapp.webapp.converters;

import org.springframework.core.convert.converter.Converter;

import com.chuyeu.training.myapp.datamodel.OrderItem;
import com.chuyeu.training.myapp.webapp.models.OrderItemModel;

public class OrderItemModelConverter implements Converter<OrderItemModel, OrderItem>{

	@Override
	public OrderItem convert(OrderItemModel orderItemModel) {
		OrderItem orderItem = new OrderItem();
		orderItem.setProductVariantId(orderItemModel.getProductVariantId());
		orderItem.setOrderQuantity(orderItemModel.getOrderQuantity());
		orderItem.setOrderId(orderItemModel.getOrderId());
		orderItem.setPrice(orderItemModel.getPrice());
		return orderItem;
	}

}
