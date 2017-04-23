package com.chuyeu.training.myapp.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chuyeu.training.myapp.dao.api.filters.ProductFilter;
import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.services.IUserService;
import com.chuyeu.training.myapp.webapp.models.ProductModel;

@RestController
@RequestMapping("/user-credentials")
public class UserCredentialsController {

/*	@Inject
	private IUserService userService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProductModel>> getAll() {

		userService.getAll()
		List<ProductModel> productModels = new ArrayList<>();

		for (Product product : all) {
			productModels.add(entity2model(product, null));
		}

		return new ResponseEntity<List<ProductModel>>(productModels, HttpStatus.OK);
	}
	*/
}
