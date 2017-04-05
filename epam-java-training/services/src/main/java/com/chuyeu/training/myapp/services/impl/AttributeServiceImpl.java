package com.chuyeu.training.myapp.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.IAttributeDao;
import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.services.IAttributeService;

@Service
public class AttributeServiceImpl implements IAttributeService {

	@Inject
	private IAttributeDao attributeDao;

	@Override
	public List<String> getNames() {
		return attributeDao.getNames();
	}

	@Override
	public List<String> getValuesByName(String attributeName) {
		return attributeDao.getValuesByName(attributeName);
	}

	@Override
	public Integer getIdByNameAndValue(String name, String value) {
		return attributeDao.getIdByNameAndValue(name, value);
	}

	@Override
	public Attribute add(Attribute attribute) {
		return attributeDao.insert(attribute);
	}

	@Override
	public void deleteValue(Integer id) {
		attributeDao.deleteValue(id);
	}

	@Override
	public void deleteName(String name) {
		attributeDao.deleteName(name);
	}

}
