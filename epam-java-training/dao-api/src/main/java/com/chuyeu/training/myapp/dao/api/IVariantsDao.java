package com.chuyeu.training.myapp.dao.api;

import java.util.List;

public interface IVariantsDao {

	void delete(List<Integer> listId);
	
	void delete(Integer id);

	void add(Integer productVariantId, List<Integer> listAttributeId);
}
