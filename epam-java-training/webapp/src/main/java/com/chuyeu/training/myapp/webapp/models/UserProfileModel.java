package com.chuyeu.training.myapp.webapp.models;

import java.util.List;

public class UserProfileModel {

	private String firstName;
	private String lastName;
	private UserCredentialsModel userCredentialsModel;
	private List<OrderModel> listOrderModel;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UserCredentialsModel getUserCredentialsModel() {
		return userCredentialsModel;
	}

	public void setUserCredentialsModel(UserCredentialsModel userCredentialsModel) {
		this.userCredentialsModel = userCredentialsModel;
	}

	public List<OrderModel> getListOrderModel() {
		return listOrderModel;
	}

	public void setListOrderModel(List<OrderModel> listOrderModel) {
		this.listOrderModel = listOrderModel;
	}

}
