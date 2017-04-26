package com.chuyeu.training.myapp.dao.api;

public interface IVariantDao {
	
	void delete(Integer attributeId, Integer productVariantId);

	void add(Integer productVariantId, Integer attributeId);
}
