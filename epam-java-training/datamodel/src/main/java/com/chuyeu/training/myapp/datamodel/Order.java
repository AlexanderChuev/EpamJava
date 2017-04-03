package com.chuyeu.training.myapp.datamodel;

import java.util.Date;

public class Order extends AbstractModel {

	private static final long serialVersionUID = 8616211383338626819L;

	private Date created;
	private Integer userProfileId;
	private Double totalPrice;
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

	@Override
	public String toString() {
		return "Order [created=" + created + ", userProfileId=" + userProfileId + ", totalPrice=" + totalPrice
				+ ", orderStatus=" + orderStatus + "]";
	}

}
