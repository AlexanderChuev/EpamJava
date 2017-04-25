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

import com.chuyeu.training.myapp.datamodel.UserCredentials;
import com.chuyeu.training.myapp.datamodel.UserProfile;
import com.chuyeu.training.myapp.datamodel.UserRole;
import com.chuyeu.training.myapp.services.IUserService;
import com.chuyeu.training.myapp.webapp.models.ProductModel;
import com.chuyeu.training.myapp.webapp.models.UserCredentialsModel;
import com.chuyeu.training.myapp.webapp.models.UserProfileModel;

@RestController
@RequestMapping("/user")
public class UserController {

	@Inject
	private IUserService userService;

	@RequestMapping(value = "/credentials/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable(value = "id") Integer id) {

		UserCredentials userCredentials = userService.getUserCredentials(id);
		UserCredentialsModel userCredentialsModel = new UserCredentialsModel();
		userCredentialsModel.setEmail(userCredentials.getEmail());
		userCredentialsModel.setUserRole(userCredentials.getUserRole().toString());

		return new ResponseEntity<UserCredentialsModel>(userCredentialsModel, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
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
	}

	/*
	 * 
	 * @RequestMapping(method = RequestMethod.GET) public
	 * ResponseEntity<List<ProductModel>> getAll() {
	 * 
	 * userService.getAll() List<ProductModel> productModels = new
	 * ArrayList<>();
	 * 
	 * for (Product product : all) { productModels.add(entity2model(product,
	 * null)); }
	 * 
	 * return new ResponseEntity<List<ProductModel>>(productModels,
	 * HttpStatus.OK); }
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(@RequestParam(value = "page", required = false) Integer page) {

		/*
		 * ProductFilter productFilter = new ProductFilter();// заменить на др
		 * фильтр productFilter.setLimit(10);
		 * 
		 * if (page == null) { productFilter.setPageNumber(1); } else {
		 * productFilter.setPageNumber(page); }
		 */

		List<UserProfileModel> listUserModel = new ArrayList<>();

		List<UserProfile> all = userService.getAll();

		for (UserProfile userProfile : all) {
			UserProfileModel userProfileModel = new UserProfileModel();
			userProfileModel.setFirstName(userProfile.getFirstName());
			userProfileModel.setLastName(userProfile.getLastName());
			listUserModel.add(userProfileModel);
		}

		return new ResponseEntity<List<UserProfileModel>>(listUserModel, HttpStatus.OK);
	}

	/*
	 * @RequestMapping(value = "/{id}", method = RequestMethod.GET) public
	 * ResponseEntity<?> getById(@PathVariable(value = "id") Integer id) {
	 * 
	 * UserProfile userProfile = userService.getUserProfile(id); UserCredentials
	 * credentials =
	 * userService.getCredentials(userProfile.getUserCredentialsId());
	 * 
	 * UserProfileModel userProfileModel = new UserProfileModel();
	 * userProfileModel.setFirstName(userProfile.getFirstName());
	 * userProfileModel.setLastName(userProfile.getLastName());
	 * userProfileModel.setEmail(credentials.getEmail());
	 * userProfileModel.setLastName(credentials.getUserRole().toString());
	 * 
	 * return new ResponseEntity<UserProfileModel>(userProfileModel,
	 * HttpStatus.OK); }
	 */

}
