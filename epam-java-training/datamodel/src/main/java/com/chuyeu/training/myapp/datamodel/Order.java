package com.chuyeu.training.myapp.datamodel;

import java.util.Date;

public class Order extends AbstractModel {

	private Date created;
	private Integer userProfileId;
	private OrderStatus orderStatus;

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

	@Override
	public String toString() {
		return "Order [created=" + created + ", userProfileId=" + userProfileId + ", orderStatus=" + orderStatus + "]";
	}

}
