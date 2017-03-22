package com.chuyeu.training.myapp.dao;

import java.util.List;

public interface IAbstractDao <T> {

	List<T> getAll();
	
	T get(Integer id);
	
	T insert(T product);
	
	T update(T product);
	
	void delete (Integer id);
}