package com.chuyeu.training.myapp.webapp.models;

import java.util.Date;
import java.util.List;

import com.chuyeu.training.myapp.datamodel.OrderStatus;

public class OrderModel {

	private Date created;
	private Double totalPrice;
	private OrderStatus orderStatus;
	private List<OrderItemModel> listOrderItemModel;

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public List<OrderItemModel> getListOrderItemModel() {
		return listOrderItemModel;
	}

	public void setListOrderItemModel(List<OrderItemModel> listOrderItemModel) {
		this.listOrderItemModel = listOrderItemModel;
	}

}
