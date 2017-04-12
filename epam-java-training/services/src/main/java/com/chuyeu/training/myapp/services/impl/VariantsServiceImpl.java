package com.chuyeu.training.myapp.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.api.IVariantsDao;
import com.chuyeu.training.myapp.services.IVariantsService;

@Service
public class VariantsServiceImpl implements IVariantsService{
	
	@Inject
	private IVariantsDao variantsDao;

	@Override
	public void delete(List<Integer> listId) {
		variantsDao.delete(listId);
	}

	@Override
	public void delete(Integer id) {
		variantsDao.delete(id);
	}
	
	
	@Override
	public void add(Integer productVariantId, List<Integer> listAttributeId) {
		variantsDao.add(productVariantId, listAttributeId);
	}

}
