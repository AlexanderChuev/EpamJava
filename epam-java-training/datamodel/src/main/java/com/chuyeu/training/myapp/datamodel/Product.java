package com.chuyeu.training.myapp.datamodel;

public class Product extends AbstractModel{

	private String name;
	private String description;
	private Double startingPrice;
	private boolean active;
	
	
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
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	@Override
	public String toString() {
		return "Product [name=" + name + ", description=" + description + ", startingPrice=" + startingPrice
				+ ", active=" + active + "]";
	}
	
}
