package com.chuyeu.training.myapp.services;

import org.springframework.transaction.annotation.Transactional;

public interface IVariantService {

	@Transactional
	void delete(Integer attributeId, Integer productVariantId);
	
	@Transactional
	void add(Integer productVariantId, Integer attributeId);
}
