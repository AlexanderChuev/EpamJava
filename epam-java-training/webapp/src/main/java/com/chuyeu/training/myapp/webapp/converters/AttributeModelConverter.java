package com.chuyeu.training.myapp.webapp.converters;

import org.springframework.core.convert.converter.Converter;

import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.webapp.models.AttributeModel;

public class AttributeModelConverter implements Converter<AttributeModel, Attribute>{

	@Override
	public Attribute convert(AttributeModel attributeModel) {
		Attribute attribute = new Attribute();
		attribute.setName(attributeModel.getName());
		attribute.setValue(attributeModel.getValue());
		return attribute;
	}

}
