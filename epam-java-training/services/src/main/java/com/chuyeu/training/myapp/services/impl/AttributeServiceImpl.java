package com.chuyeu.training.myapp.services.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.chuyeu.training.myapp.dao.IAttributeDao;
import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.services.IAttributeService;

@Service
public class AttributeServiceImpl implements IAttributeService {

	@Inject
	private IAttributeDao attributeDao;

	@Override
	public List<Attribute> getAll() {
		return attributeDao.getAll();
	}

	@Override
	public Attribute get(Integer id) {
		return attributeDao.get(id);
	}

	@Override
	public Attribute saveOrUpdate(Attribute entity) throws DuplicateKeyException{
		if (entity.getId() == null) {
			return attributeDao.insert(entity);
		} else {
			return attributeDao.update(entity);
		}
	}

	@Override
	public void delete(Integer id) throws EmptyResultDataAccessException {
		attributeDao.delete(id);
	}

}
