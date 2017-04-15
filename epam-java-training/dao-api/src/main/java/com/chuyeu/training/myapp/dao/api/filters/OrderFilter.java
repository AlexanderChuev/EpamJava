package com.chuyeu.training.myapp.dao.api.filters;

import com.chuyeu.training.myapp.datamodel.OrderStatus;

public class OrderFilter {

	private OrderStatus orderStatus;
	private SortData sort;

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public SortData getSort() {
		return sort;
	}

	public void setSort(SortData sort) {
		this.sort = sort;
	}

}
