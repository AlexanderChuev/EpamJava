package com.chuyeu.training.myapp.webapp.models;

import java.util.List;

public class ProductModel {

	private String name;
	private String description;
	private List<ProductVariantModel> listProductVariantModel;
	private Double basePrice;
	private Boolean active;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ProductVariantModel> getListProductVariantModel() {
		return listProductVariantModel;
	}

	public void setListProductVariantModel(List<ProductVariantModel> listProductVariantModel) {
		this.listProductVariantModel = listProductVariantModel;
	}

	public Double getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(Double basePrice) {
		this.basePrice = basePrice;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

}
