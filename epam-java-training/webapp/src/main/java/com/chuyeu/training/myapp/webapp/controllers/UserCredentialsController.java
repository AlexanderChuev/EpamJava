package com.chuyeu.training.myapp.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chuyeu.training.myapp.dao.api.filters.ProductFilter;
import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.datamodel.UserCredentials;
import com.chuyeu.training.myapp.datamodel.UserRole;
import com.chuyeu.training.myapp.services.IProductService;
import com.chuyeu.training.myapp.services.IUserService;
import com.chuyeu.training.myapp.webapp.models.ProductModel;
import com.chuyeu.training.myapp.webapp.models.UserCredentialsModel;
import com.chuyeu.training.myapp.webapp.models.parts.IdModel;

@RestController
@RequestMapping("/user-credentials")
public class UserCredentialsController {
	
	@Inject
	private IUserService userService;
	

/*	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody UserCredentialsModel userCredentialsModel) {

		if (userCredentialsModel == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setEmail(userCredentialsModel.getEmail());
		userCredentials.setPassword(userCredentialsModel.getPassword());
		userCredentials.setUserRole(UserRole.CLIENT);
		
		Integer id = userService.
		return new ResponseEntity<IdModel>(new IdModel(id),HttpStatus.CREATED);
	}*/
	
	
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
