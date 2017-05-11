package com.chuyeu.training.myapp.datamodel;

public class Product extends AbstractModel {

	private String nameRu;
	private String descriptionRu;
	private Double basePrice;
	private Boolean active;
	private String nameEn;
	private String descriptionEn;

	public String getNameRu() {
		return nameRu;
	}

	public void setNameRu(String nameRu) {
		this.nameRu = nameRu;
	}

	public String getDescriptionRu() {
		return descriptionRu;
	}

	public void setDescriptionRu(String descriptionRu) {
		this.descriptionRu = descriptionRu;
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

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getDescriptionEn() {
		return descriptionEn;
	}

	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}

	@Override
	public String toString() {
		return "Product [nameRu=" + nameRu + ", descriptionRu=" + descriptionRu + ", basePrice=" + basePrice
				+ ", active=" + active + ", nameEn=" + nameEn + ", descriptionEn=" + descriptionEn + "]";
	}

}
