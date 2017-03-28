package com.chuyeu.training.myapp.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.chuyeu.training.myapp.datamodel.Attribute;

public class AttributesMapper implements RowMapper<Attribute> {

	@Override
	public Attribute mapRow(ResultSet rs, int rowNum) throws SQLException {

		Attribute attribute = new Attribute();
		attribute.setId(rs.getInt("id"));
		attribute.setAttributeName(rs.getString("attribute_name"));
		attribute.setValue(rs.getString("value"));

		return attribute;
	}

}
