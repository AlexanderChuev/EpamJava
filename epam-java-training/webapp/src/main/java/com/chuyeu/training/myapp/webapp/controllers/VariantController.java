package com.chuyeu.training.myapp.webapp.controllers;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chuyeu.training.myapp.services.IVariantService;
import com.chuyeu.training.myapp.webapp.models.VariantModel;

@RestController
@RequestMapping("/variant")
public class VariantController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(VariantController.class);

	@Inject
	private IVariantService variantService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createVariant(@RequestBody VariantModel variantModel) {
		variantService.add(variantModel.getProductVariantId(), variantModel.getAttributeId());
		LOGGER.info("Add variant with productVariantId={}. attributeId={}", variantModel.getProductVariantId(), variantModel.getAttributeId());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id1}/{id2}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteVariant(@PathVariable(value = "id1") Integer productVariantId, @PathVariable(value = "id2") Integer attributeId) {
		variantService.delete(productVariantId, attributeId);
		LOGGER.info("Delete variant by productVariantId={} and attributeId={}", productVariantId, attributeId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
