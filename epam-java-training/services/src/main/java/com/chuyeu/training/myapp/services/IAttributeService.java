package com.chuyeu.training.myapp.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.chuyeu.training.myapp.datamodel.Attribute;

public interface IAttributeService {

	List<Attribute> getProductVariantAttributes(Integer productVariantId);
	
	List<String> getNames();

	List<String> getValuesByName(String name);

	Integer getIdByNameAndValue(String name, String value);

	@Transactional
	void add(Attribute attribute);

	@Transactional
	void deleteAttributeValue(Integer id);

	@Transactional
	void delete(String name);
	
	List<Integer> getAllIdByName(String name);

}
