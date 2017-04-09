package com.chuyeu.training.myapp.dao;

import java.util.List;

import com.chuyeu.training.myapp.datamodel.Attribute;

public interface IAttributeDao {

	List<Attribute> getProductVariantAttributes(Integer productVariantId);
	
	List<String> getNames();

	List<String> getValuesByName(String attributeName);

	Integer getIdByNameAndValue(String attributeName, String attributeValue);
	
	void deleteAttributeValue(Integer id);
	
	void delete(String name);
	
	void add(String name);
	
	void add(Attribute attribute);

	List<Integer> listIdByName(String name);
}
