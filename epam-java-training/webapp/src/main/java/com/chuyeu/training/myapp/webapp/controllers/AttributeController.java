package com.chuyeu.training.myapp.webapp.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.services.IAttributeService;
import com.chuyeu.training.myapp.webapp.memorization.Memoization;
import com.chuyeu.training.myapp.webapp.models.AttributeModel;
import com.chuyeu.training.myapp.webapp.models.parts.IdModel;
import com.chuyeu.training.myapp.webapp.models.parts.NameModel;
import com.chuyeu.training.myapp.webapp.models.parts.ValueModel;

@RestController
@RequestMapping("/attribute")
public class AttributeController {

	@Inject
	private IAttributeService attributeService;
	
	@Autowired
	ConversionService conversionService;

	// +++
	@RequestMapping(value = "/names", method = RequestMethod.GET)
	public ResponseEntity<?> getAttributeNames(HttpServletRequest request) {
		
	

		List<String> attributeNames = attributeService.getNames();
		List<NameModel> names = new ArrayList<>();

		for (String name : attributeNames) {
			names.add(new NameModel(name));
		}
		return new ResponseEntity<List<NameModel>>(names, HttpStatus.OK);
	}

	// +++
	@RequestMapping(value = "/values", method = RequestMethod.GET)
	public ResponseEntity<?> getAttributeValues(@RequestParam(value = "name", required = false) String name) {

		List<String> attributeValues = attributeService.getValuesByName(name);
		List<ValueModel> values = new ArrayList<>();

		for (String value : attributeValues) {
			values.add(new ValueModel(value));
		}
		return new ResponseEntity<List<ValueModel>>(values, HttpStatus.OK);
	}

	// +++
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getIdByNameAndValue(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "value", required = false) String value) {
		Integer id = attributeService.getIdByNameAndValue(name, value);
		return new ResponseEntity<IdModel>(new IdModel(id), HttpStatus.OK);
	}

	// +++
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/product-variant", method = RequestMethod.GET)
	public ResponseEntity<?> getAllById(@RequestParam(value = "id", required = false) Integer id, HttpServletRequest req) {
		
		List<AttributeModel> attributesModel;
		String path = new StringBuilder(req.getServletPath()).append("?").append(req.getQueryString()).toString();
		Object data = Memoization.getInstance().getData(path, new Date());
		if (data == null) {
		
		List<Attribute> attributes = attributeService.getProductVariantAttributes(id);
		attributesModel = new ArrayList<>();

		for (Attribute attribute : attributes) {
			AttributeModel model = conversionService.convert(attribute, AttributeModel.class);
			attributesModel.add(model);
		}
		Memoization.getInstance().putData(path, attributesModel);
		} else {
			attributesModel = (List<AttributeModel>) data;
		}
		return new ResponseEntity<List<AttributeModel>>(attributesModel, HttpStatus.OK);
	}

	// +++
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createAttribute(@RequestBody AttributeModel attributeModel) {
		Attribute attribute = conversionService.convert(attributeModel, Attribute.class);
		attributeService.save(attribute);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	// +++
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAttributeValue(@PathVariable(value = "id") Integer id) {
		attributeService.deleteAttributeValue(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// +++
	@RequestMapping(value = "/all/{name}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAttributeValue(@PathVariable(value = "name") String name) {
		attributeService.delete(name);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
