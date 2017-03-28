package com.chuyeu.training.myapp.datamodel;

import java.io.Serializable;

public abstract class AbstractModel implements Serializable{

	private static final long serialVersionUID = 1929717259999115968L;
	
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
