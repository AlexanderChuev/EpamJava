package com.chuyeu.training.myapp.webapp.converters;

import org.springframework.core.convert.converter.Converter;

import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.webapp.models.AttributeModel;

public class AttributeConverter implements Converter<Attribute, AttributeModel>{

	@Override
	public AttributeModel convert(Attribute attribute) {
		AttributeModel model = new AttributeModel();
		model.setId(attribute.getId());
		model.setName(attribute.getName());
		model.setValue(attribute.getValue());
		return model;
	}

}
