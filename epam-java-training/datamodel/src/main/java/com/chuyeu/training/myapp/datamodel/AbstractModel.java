package com.chuyeu.training.myapp.datamodel;

import java.io.Serializable;

public abstract class AbstractModel implements Serializable{

	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
