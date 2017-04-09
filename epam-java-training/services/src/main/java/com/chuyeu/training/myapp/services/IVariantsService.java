package com.chuyeu.training.myapp.services;

import org.springframework.transaction.annotation.Transactional;

public interface IVariantsService {

	@Transactional
	void delete(String ids);

	@Transactional
	void delete(Integer id);
	
	@Transactional
	void add();
}
