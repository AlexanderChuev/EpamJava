package com.chuyeu.training.myapp.services;

import java.util.List;

public interface IAbstractService<T> {

	List<T> getAll();
	
	T get(Integer id);
	
	T insert(T product);
	
	T update(T product);
	
	void delete (Integer id);
	
}
