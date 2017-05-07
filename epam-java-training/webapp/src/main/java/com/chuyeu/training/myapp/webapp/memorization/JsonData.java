package com.chuyeu.training.myapp.webapp.memorization;

import java.io.Serializable;
import java.util.Date;

public class JsonData implements Serializable{

	private Object data;
	private Date date;

	public JsonData(Object data) {
		super();
		date = new Date();
		this.data = data;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Date getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "JsonData [data=" + data + ", date=" + date + "]";
	}

}
