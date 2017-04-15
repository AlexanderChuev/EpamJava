package com.chuyeu.training.myapp.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface IVariantsService {

	@Transactional
	void delete(List<Integer> listId);

	@Transactional
	void delete(Integer attributeId);
	
	@Transactional
	void add(Integer productVariantId, List<Integer> listAttributeId);
}
