package com.chuyeu.training.myapp.webapp.models;

import java.io.Serializable;
import java.util.Date;

import com.chuyeu.training.myapp.datamodel.OrderStatus;

public class OrderModel implements Serializable{

	private Integer id;
	private Date created;
	private Integer userProfileId;
	private OrderStatus orderStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Integer getUserProfileId() {
		return userProfileId;
	}

	public void setUserProfileId(Integer userProfileId) {
		this.userProfileId = userProfileId;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

}
