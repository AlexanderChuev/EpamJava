package com.chuyeu.training.myapp.dao.api.filters;

public class SortData {

	private String column;
	private String direction;

	public SortData(String column, String direction) {
		super();
		this.column = column;
		this.direction = direction;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "SortData [column=" + column + ", direction=" + direction + "]";
	}

}
