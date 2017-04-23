package com.chuyeu.training.myapp.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.api.IAttributeDao;
import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.services.IAttributeService;
import com.chuyeu.training.myapp.services.IVariantService;

@Service
public class AttributeServiceImpl implements IAttributeService {

	private final Logger LOGGER = LoggerFactory.getLogger(AttributeServiceImpl.class);

	@Inject
	private IAttributeDao attributeDao;

	@Inject
	private IVariantService variantService;
	
	@Override
	public List<Attribute> getProductVariantAttributes(Integer productVariantId) {
		return attributeDao.getProductVariantAttributes(productVariantId);
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
	
	@Override
	public void add(Attribute attribute) {
		attributeDao.add(attribute);
		LOGGER.info("Insert new attribute name={}. value={}", attribute.getName(), attribute.getValue());
	}

	@Override
	public void deleteAttributeValue(Integer id) {
		variantService.delete(id);
		attributeDao.deleteAttributeValue(id);
	}

	@Override
	public void delete(String name) {
		variantService.delete(attributeDao.listIdByName(name));
		attributeDao.delete(name);
	}

}
