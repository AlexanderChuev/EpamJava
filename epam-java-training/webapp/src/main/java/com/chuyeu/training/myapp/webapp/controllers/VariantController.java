package com.chuyeu.training.myapp.webapp.controllers;

import javax.inject.Inject;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chuyeu.training.myapp.datamodel.UserRole;
import com.chuyeu.training.myapp.services.IVariantService;
import com.chuyeu.training.myapp.services.impl.UserAuthStorage;
import com.chuyeu.training.myapp.webapp.models.VariantModel;

@RestController
@RequestMapping("/variant")
public class VariantController {
	
	@Inject
    private ApplicationContext context;

	@Inject
	private IVariantService variantService;

	//+++
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createVariant(@RequestBody VariantModel variantModel) {
		UserAuthStorage userAuthStorage = context.getBean(UserAuthStorage.class);
		if (userAuthStorage.getUserRole().equals(UserRole.ADMIN)) {
		variantService.add(variantModel.getProductVariantId(), variantModel.getAttributeId());
		return new ResponseEntity<Void>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}
	
	//+++
	@RequestMapping(value = "/{id1}/{id2}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteVariant(@PathVariable(value = "id1") Integer attributeId, @PathVariable(value = "id2") Integer productVariantId) {
		UserAuthStorage userAuthStorage = context.getBean(UserAuthStorage.class);
		if (userAuthStorage.getUserRole().equals(UserRole.ADMIN)) {
		variantService.delete(attributeId, productVariantId);
		return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
	}

}
