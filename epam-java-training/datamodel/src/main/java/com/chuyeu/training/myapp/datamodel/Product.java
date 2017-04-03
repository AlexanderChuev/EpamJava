package com.chuyeu.training.myapp.datamodel;

public class Product extends AbstractModel {

	private static final long serialVersionUID = -1239132148748692829L;
	private String name;
	private String description;
	private Double startingPrice;
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

	public Double getStartingPrice() {
		return startingPrice;
	}

	public void setStartingPrice(Double startingPrice) {
		this.startingPrice = startingPrice;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", description=" + description + ", startingPrice=" + startingPrice
				+ ", active=" + active + "]";
	}

}
