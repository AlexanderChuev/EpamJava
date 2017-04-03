package com.chuyeu.training.myapp.dao;

import java.util.List;

import com.chuyeu.training.myapp.datamodel.ProductVariant;

public interface IProductVariantDao extends IAbstractDao<ProductVariant>{

	List<ProductVariant> getAllByProduct(Integer productId);
}
