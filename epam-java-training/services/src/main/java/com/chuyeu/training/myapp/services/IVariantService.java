package com.chuyeu.training.myapp.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

public interface IVariantService {

	@Transactional
	void delete(List<Integer> listId);

	@Transactional
	void delete(Integer attributeId, Integer productVariantId);
	
	@Transactional
	void add(Integer productVariantId, Integer attributeId);
}
