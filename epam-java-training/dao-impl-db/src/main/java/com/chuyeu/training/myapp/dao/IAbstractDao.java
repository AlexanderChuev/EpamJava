package com.chuyeu.training.myapp.dao;

import java.util.List;

public interface IAbstractDao <T, ID> {

	List<T> getAll();
	
	T get(ID id);
	
	T insert(T product);
	
	T update(T product);
	
	void delete (ID id);
}
