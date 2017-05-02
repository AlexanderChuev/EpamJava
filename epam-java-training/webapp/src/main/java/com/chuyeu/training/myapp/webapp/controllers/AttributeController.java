package com.chuyeu.training.myapp.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
import com.chuyeu.training.myapp.webapp.models.AttributeModel;
import com.chuyeu.training.myapp.webapp.models.parts.IdModel;
import com.chuyeu.training.myapp.webapp.models.parts.NameModel;
import com.chuyeu.training.myapp.webapp.models.parts.ValueModel;

@RestController
@RequestMapping("/attribute")
public class AttributeController {

	@Inject
	private IAttributeService attributeService;

	// +++
	@RequestMapping(value = "/names", method = RequestMethod.GET)
	public ResponseEntity<?> getAttributeNames() {

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
	@RequestMapping(value = "/product-variant", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@RequestParam(value = "id", required = false) Integer id) {

		List<Attribute> attributes = attributeService.getProductVariantAttributes(id);
		List<AttributeModel> attributesModel = new ArrayList<>();

		for (Attribute attribute : attributes) {
			attributesModel.add(entity2model(attribute));
		}
		return new ResponseEntity<List<AttributeModel>>(attributesModel, HttpStatus.OK);
	}

	// +++
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createAttribute(@RequestBody AttributeModel attributeModel) {
		Attribute attribute = model2entity(attributeModel);
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

	private AttributeModel entity2model(Attribute attribute) {
		AttributeModel model = new AttributeModel();
		model.setId(attribute.getId());
		model.setName(attribute.getName());
		model.setValue(attribute.getValue());
		return model;
	}

	private Attribute model2entity(AttributeModel attributeModel) {
		Attribute attribute = new Attribute();
		attribute.setName(attributeModel.getName());
		attribute.setValue(attributeModel.getValue());
		return attribute;
	}

}
