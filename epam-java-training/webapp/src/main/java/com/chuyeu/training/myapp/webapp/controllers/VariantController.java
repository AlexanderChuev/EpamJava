package com.chuyeu.training.myapp.webapp.controllers;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chuyeu.training.myapp.services.IVariantService;
import com.chuyeu.training.myapp.webapp.models.VariantModel;

@RestController
@RequestMapping("/variant")
public class VariantController {

	@Inject
	private IVariantService variantService;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createVariant(@RequestBody VariantModel  variantModel) {
		variantService.add(variantModel.getProductVariantId(), variantModel.getAttributeId());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}
