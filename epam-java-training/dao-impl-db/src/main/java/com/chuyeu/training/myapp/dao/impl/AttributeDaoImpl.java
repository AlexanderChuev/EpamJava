package com.chuyeu.training.myapp.dao.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.chuyeu.training.myapp.dao.api.IAttributeDao;
import com.chuyeu.training.myapp.datamodel.Attribute;

@Repository
public class AttributeDaoImpl implements IAttributeDao {

	@Inject
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public void add(Attribute attribute) {
		if(attribute.getValue() == null){
			attribute.setValue("choose value");
		}
		jdbcTemplate.update("insert into attribute (name, value) values (?,?)", attribute.getName(), attribute.getValue());
	}

	@Override
	public void deleteAttributeValue(Integer id) {
		jdbcTemplate.update("delete from attribute where id = " + id);
	}

	@Override
	public void delete(String name) {
		jdbcTemplate.update("delete from attribute where name = '" + name + "'");
	}

	
	@Override
	public List<String> getNames() {
		return jdbcTemplate.queryForList("select distinct name from attribute", String.class);
	}
	
	
	@Override
	public List<String> getValuesByName(String name) {
		return jdbcTemplate.queryForList("select value from attribute where name = ?", new Object[] { name },
				String.class);
	}
	
	
	@Override
	public Integer getIdByNameAndValue(String name, String value) {
		return jdbcTemplate.queryForObject("select id from attribute where name = ? and value = ?",
				new Object[] { name, value }, Integer.class);
	}
	
	
	@Override
	public List<Integer> listIdByName(String name) {
		return jdbcTemplate.queryForList("select id from attribute where name = ?", new Object[] { name },
				Integer.class);
	}

	@Override
	public List<Attribute> getProductVariantAttributes(Integer productVariantId) {
		return jdbcTemplate.query(
				"select * from attribute a join variants v on v.attribute_id = a.id where v.product_variant_id = ?",
				new Object[] { productVariantId }, new BeanPropertyRowMapper<Attribute>(Attribute.class));
	}


}
