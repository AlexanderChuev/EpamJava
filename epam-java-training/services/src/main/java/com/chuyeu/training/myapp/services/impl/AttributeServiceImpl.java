package com.chuyeu.training.myapp.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.api.IAttributeDao;
import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.services.IAttributeService;
import com.chuyeu.training.myapp.services.IVariantsService;

@Service
public class AttributeServiceImpl implements IAttributeService {

	@Inject
	private IAttributeDao attributeDao;
	
	@Inject
	private IVariantsService variantsService;

	
	@Override
	public void add(Attribute attribute) {
		attributeDao.add(attribute);
	}
	
	@Override
	public void add(String name) {
		attributeDao.add(name);
	}
	
	@Override
	public void deleteAttributeValue(Integer id) {
		variantsService.delete(id);
		attributeDao.deleteAttributeValue(id);
	}

	@Override
	public void delete(String name) {
		variantsService.delete(attributeDao.listIdByName(name));
		attributeDao.delete(name);
	}
	
	
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


}
