package com.chuyeu.training.myapp.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chuyeu.training.myapp.datamodel.UserCredentials;
import com.chuyeu.training.myapp.datamodel.UserProfile;
import com.chuyeu.training.myapp.datamodel.UserRole;
import com.chuyeu.training.myapp.services.IUserService;
import com.chuyeu.training.myapp.webapp.models.ProductModel;
import com.chuyeu.training.myapp.webapp.models.UserModel;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Inject
	private IUserService userService;


	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(@RequestParam(value = "page", required = false) Integer page) {
		

/*		ProductFilter productFilter = new ProductFilter();// заменить на др фильтр
		productFilter.setLimit(10);

		if (page == null) {
			productFilter.setPageNumber(1);
		} else {
			productFilter.setPageNumber(page);
		}*/

		
		List<UserModel> listUserModel = new ArrayList<>();
		
		List<UserProfile> all = userService.getAll();
		
		for (UserProfile userProfile : all) {
			UserModel userModel = new UserModel();
			userModel.setFirstName(userProfile.getFirstName());
			userModel.setLastName(userProfile.getLastName());
			listUserModel.add(userModel);
		}

		return new ResponseEntity<List<UserModel>>(listUserModel, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable(value = "id") Integer id) {
		
		UserProfile userProfile = userService.getUserProfile(id);
		UserCredentials credentials = userService.getCredentials(userProfile.getUserCredentialsId());
		
		UserModel userModel = new UserModel();
		userModel.setFirstName(userProfile.getFirstName());
		userModel.setLastName(userProfile.getLastName());
		userModel.setEmail(credentials.getEmail());
		userModel.setLastName(credentials.getUserRole().toString());
		
		return new ResponseEntity<UserModel>(userModel, HttpStatus.OK);
	}
	
	
}
