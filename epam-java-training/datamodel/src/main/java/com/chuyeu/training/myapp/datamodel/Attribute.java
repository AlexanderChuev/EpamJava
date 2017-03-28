package com.chuyeu.training.myapp.datamodel;

public class Attribute extends AbstractModel {

	private static final long serialVersionUID = 4716720600199128802L;

	private String attributeName;
	private String value;

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Attribute [attributeName=" + attributeName + ", value=" + value + "]";
	}

}
