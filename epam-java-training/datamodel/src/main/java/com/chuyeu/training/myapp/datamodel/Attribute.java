package com.chuyeu.training.myapp.datamodel;

public class Attribute extends AbstractModel {

	private static final long serialVersionUID = 4716720600199128802L;

	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Attribute [name=" + name + ", value=" + value + "]";
	}

}
