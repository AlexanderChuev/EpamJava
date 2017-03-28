package com.chuyeu.training.myapp.datamodel;

import java.util.Date;

public class Order extends AbstractModel {

	private static final long serialVersionUID = 8616211383338626819L;

	private Date created;
	private UserProfile userProfile;
	private Double totalPrice;

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
