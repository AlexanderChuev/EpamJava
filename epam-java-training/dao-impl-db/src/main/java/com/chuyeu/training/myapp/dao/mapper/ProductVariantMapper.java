package com.chuyeu.training.myapp.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;

import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.datamodel.ProductVariant;

public class ProductVariantMapper implements RowMapper<ProductVariant>{

	@Override
	public ProductVariant mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Product product = new Product();
		
		product.setId(rs.getInt("id"));
		product.setName(rs.getString("name"));
		product.setDescription(rs.getString("description"));
		product.setStartingPrice(rs.getDouble("starting_price"));
		product.setActive(rs.getBoolean("active"));
		
		ProductVariant productVariant = new ProductVariant();
		
		productVariant.setId(rs.getInt("id"));
		productVariant.setProduct(product);
		productVariant.setQuantity(rs.getInt("quantity"));
		productVariant.setPriceInfluence(rs.getDouble("price_influence"));
		
		List<Attribute> attributes = new ArrayList<>();
		
		do{
			Attribute attribute = new Attribute();
			attribute.setId(rs.getInt("id"));
			attribute.setAttributeName(rs.getString("attribute_name"));
			attribute.setValue(rs.getString("value"));
			attributes.add(attribute);
		} while (rs.next());
		
		productVariant.setAttributes(attributes);
		
		return productVariant;
	}

}
