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
import com.chuyeu.training.myapp.services.IVariantService;
import com.chuyeu.training.myapp.webapp.models.AttributeModel;
import com.chuyeu.training.myapp.webapp.models.IdModel;

@RestController
@RequestMapping("/attribute")
public class AttributeController extends AbstractConroller {

	@Inject
	private IAttributeService attributeService;
	
	@Inject
	private IVariantService variantService;
	
	
	@RequestMapping(value = "/names", method = RequestMethod.GET)
	public ResponseEntity<?> getAttributeNames() {
		return new ResponseEntity<List<String>>(attributeService.getNames(), HttpStatus.OK);
	}

	@RequestMapping(value = "/values", method = RequestMethod.GET)
	public ResponseEntity<?> getAttributeValues(@RequestParam(value = "name", required = false) String name) {
		return new ResponseEntity<List<String>>(attributeService.getValuesByName(name), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getIdByNameAndValue(@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "value", required = false) String value) {
		Integer id = attributeService.getIdByNameAndValue(name, value);
		return new ResponseEntity<IdModel>(new IdModel(id), HttpStatus.OK);
	}

	@RequestMapping(value = "/product-variant", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@RequestParam(value = "id", required = false) Integer id) {

		List<Attribute> attributes = attributeService.getProductVariantAttributes(id);
		List<AttributeModel> attributesModel = new ArrayList<>();

		for (Attribute attribute : attributes) {
			attributesModel.add(entity2model(attribute));
		}
		return new ResponseEntity<List<AttributeModel>>(attributesModel, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createNewAttribute(@RequestBody AttributeModel attributeModel) {
		Attribute attribute = model2entity(attributeModel);
		attributeService.add(attribute);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAttributeValue(@PathVariable(value = "id") Integer id) {
		attributeService.deleteAttributeValue(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{name}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteAttributeValue(@PathVariable(value = "name") String name) {
		attributeService.delete(name);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

/*	// замапить на др адрес
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createVariants(@RequestBody VariantsModel variantsModel) {
		variantsService.add(variantsModel.getProductVariantId(), variantsModel.getAttributeIds());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}*/
	
}
