package com.chuyeu.training.myapp.dao;

import java.util.List;

import com.chuyeu.training.myapp.datamodel.Attribute;

public interface IAttributeDao {

	List<Attribute> getProductVariantAttributes(Integer productVariantId);
	
	List<String> getNames();

	List<String> getValuesByName(String attributeName);

	Integer getIdByNameAndValue(String attributeName, String attributeValue);
	
	void deleteValue(Integer id);
	
	void deleteName(String name);
	
	Attribute insert(Attribute attribute);
}
