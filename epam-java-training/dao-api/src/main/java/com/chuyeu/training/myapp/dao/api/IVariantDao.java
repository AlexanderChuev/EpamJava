package com.chuyeu.training.myapp.dao.api;

public interface IVariantDao {
	
	void delete(Integer productVariantId, Integer attributeId);

	void add(Integer productVariantId, Integer attributeId);
}
