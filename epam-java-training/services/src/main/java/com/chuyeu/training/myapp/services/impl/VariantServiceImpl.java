package com.chuyeu.training.myapp.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.api.IVariantDao;
import com.chuyeu.training.myapp.services.IVariantService;

@Service
public class VariantServiceImpl implements IVariantService{
	
	@Inject
	private IVariantDao variantDao;

	@Override
	public void delete(List<Integer> listId) {
		variantDao.delete(listId);
	}

	@Override
	public void delete(Integer attributeId) {
		variantDao.delete(attributeId);
	}
	
	
	@Override
	public void add(Integer productVariantId, Integer attributeId) {
		variantDao.add(productVariantId, attributeId);
	}

}
