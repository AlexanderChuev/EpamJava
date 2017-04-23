package com.chuyeu.training.myapp.dao.api;

import java.util.List;

public interface IVariantDao {

	void delete(List<Integer> listId);
	
	void delete(Integer attributeId);

	void add(Integer productVariantId, Integer attributeId);
}
