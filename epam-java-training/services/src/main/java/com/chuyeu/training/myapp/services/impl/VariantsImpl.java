package com.chuyeu.training.myapp.services.impl;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.IVariantsDao;
import com.chuyeu.training.myapp.services.IVariantsService;

@Service
public class VariantsImpl implements IVariantsService{
	
	@Inject
	private IVariantsDao variantsDao;

	@Override
	public void delete(String ids) {
		variantsDao.delete(ids);
	}

	@Override
	public void delete(Integer id) {
		variantsDao.delete(id);
	}
	
	
	@Override
	public void add() {
	}

}
