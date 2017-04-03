package com.chuyeu.training.myapp.services;

import java.util.List;

import com.chuyeu.training.myapp.datamodel.Attribute;

public interface IAttributeService {

	List<String> getNames();

	List<String> getValuesByName(String name);

	Integer getIdByNameAndValue(String name, String value);

	Attribute saveOrUpdate(Attribute attribute);

	void delete(Integer id);
}
