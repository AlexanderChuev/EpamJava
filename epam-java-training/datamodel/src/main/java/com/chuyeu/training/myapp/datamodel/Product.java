package com.chuyeu.training.myapp.datamodel;

public class Product extends AbstractModel {

	private String name;
	private String description;
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

	@Override
	public String toString() {
		return "Product [name=" + name + ", description=" + description + ", basePrice=" + basePrice + ", active="
				+ active + "]";
	}

}
