package com.chuyeu.training.myapp.dao.api;

import java.util.List;

import com.chuyeu.training.myapp.datamodel.Attribute;

public interface IAttributeDao {
	
	List<Attribute> getProductVariantAttributes(Integer productVariantId);
	
	List<String> getNames();

	List<String> getValuesByName(String attributeName);

	Integer getIdByNameAndValue(String attributeName, String attributeValue);
	
	void deleteAttributeValue(Integer id);
	
	void deleteByName(String name);
	
	void save(Attribute attribute);

	List<Integer> getAllIdByName(String name);
}
