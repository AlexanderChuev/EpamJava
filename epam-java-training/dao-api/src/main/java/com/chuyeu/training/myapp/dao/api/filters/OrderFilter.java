package com.chuyeu.training.myapp.dao.api.filters;

import com.chuyeu.training.myapp.datamodel.OrderStatus;
import com.chuyeu.training.myapp.datamodel.UserRole;

public class OrderFilter {

	private OrderStatus orderStatus;
	private Integer id;
	private UserRole userRole;

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

}
