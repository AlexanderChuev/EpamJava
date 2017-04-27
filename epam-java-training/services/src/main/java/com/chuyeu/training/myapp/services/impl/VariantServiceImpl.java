package com.chuyeu.training.myapp.services.impl;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.api.IVariantDao;
import com.chuyeu.training.myapp.services.IVariantService;

@Service
public class VariantServiceImpl implements IVariantService {

	private final Logger LOGGER = LoggerFactory.getLogger(VariantServiceImpl.class);

	@Inject
	private IVariantDao variantDao;

	@Override
	public void delete(Integer productVariantId, Integer attributeId) {
		variantDao.delete(productVariantId, attributeId);
		LOGGER.info("Delete variant with attributeId={}. productVariantId={} ", attributeId, productVariantId);
	}

	@Override
	public void add(Integer productVariantId, Integer attributeId) {
		variantDao.add(productVariantId, attributeId);
		LOGGER.info("Add variant with attributeId={}. productVariantId={} ", attributeId, productVariantId);
	}

}
