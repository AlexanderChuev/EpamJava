package com.chuyeu.training.myapp.services;

import java.util.List;

public interface IAbstractService<T> {

	List<T> getAll();
	
	T get(Integer id);
	
	T saveOrUpdate(T entity);
	
	void delete (Integer id);
	
}
