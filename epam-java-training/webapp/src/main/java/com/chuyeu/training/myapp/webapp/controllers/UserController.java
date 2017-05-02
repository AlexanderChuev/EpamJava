package com.chuyeu.training.myapp.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.datamodel.UserCredentials;
import com.chuyeu.training.myapp.datamodel.UserProfile;
import com.chuyeu.training.myapp.datamodel.UserRole;
import com.chuyeu.training.myapp.services.IUserService;
import com.chuyeu.training.myapp.services.impl.UserAuthStorage;
import com.chuyeu.training.myapp.webapp.models.EntityModelWrapper;
import com.chuyeu.training.myapp.webapp.models.UserCredentialsModel;
import com.chuyeu.training.myapp.webapp.models.UserProfileModel;
import com.chuyeu.training.myapp.webapp.models.UserWrapper;

@RestController
@RequestMapping("/user")
public class UserController {

	@Inject
	private IUserService userService;

	@Inject
	private ApplicationContext context;

	// +++
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllUserProfile(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "column", required = false) String column,
			@RequestParam(value = "direction", required = false) String direction,
			@RequestParam(value = "limit", required = false) Integer limit) {

			CommonFilter commonFilter = new CommonFilter(page, limit, column, direction);

			List<UserProfile> listUserProfileFromDB = userService.getAll(commonFilter);
			List<UserProfileModel> listUserProfileModel = new ArrayList<>();

			for (UserProfile userProfile : listUserProfileFromDB) {
				listUserProfileModel.add(entity2model(userProfile));
			}

			EntityModelWrapper<UserProfileModel> wrapper = new EntityModelWrapper<UserProfileModel>();

			wrapper.setListEntityModel(listUserProfileModel);
			wrapper.setPageCount(userService.getUserProfileQuantity()); // quantity

			return new ResponseEntity<EntityModelWrapper<UserProfileModel>>(wrapper, HttpStatus.OK);
	}

	// +++
	@RequestMapping(value = "/credentials/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCredentials(@PathVariable(value = "id") Integer id) {

			UserCredentials userCredentials = userService.getUserCredentials(id);
			UserCredentialsModel userCredentialsModel = new UserCredentialsModel();
			userCredentialsModel.setEmail(userCredentials.getEmail());
			userCredentialsModel.setUserRole(userCredentials.getUserRole().toString());

			return new ResponseEntity<UserCredentialsModel>(userCredentialsModel, HttpStatus.OK);
	}

	// +++
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getProfile(@PathVariable(value = "id") Integer id) {
			UserProfile userProfile = userService.getUserProfile(id);
			return new ResponseEntity<UserProfileModel>(entity2model(userProfile), HttpStatus.OK);
	}

	// +++
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody UserWrapper userWrapper) {

		if (userWrapper.getUserCredentialsModel() == null || userWrapper.getUserProfileModel() == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		UserCredentials userCredentials = new UserCredentials();
		userCredentials.setEmail(userWrapper.getUserCredentialsModel().getEmail());
		userCredentials.setPassword(userWrapper.getUserCredentialsModel().getPassword());
		userCredentials.setUserRole(UserRole.CLIENT);

		UserProfile userProfile = new UserProfile();
		userProfile.setFirstName(userWrapper.getUserProfileModel().getFirstName());
		userProfile.setLastName(userWrapper.getUserProfileModel().getLastName());

		userService.registration(userProfile, userCredentials);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/credentials/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUserCredentials(@RequestBody UserCredentialsModel userCredentialsModel,
			@PathVariable(value = "id") Integer id) {

		UserAuthStorage userAuthStorage = context.getBean(UserAuthStorage.class);
		if (userAuthStorage.getId().equals(id)) {

			UserCredentials userCredentialsFromDb = userService.getUserCredentials(id);
			userCredentialsFromDb.setPassword(userCredentialsModel.getPassword());
			userCredentialsFromDb.setUserRole(UserRole.valueOf(userCredentialsModel.getUserRole()));
			userService.update(userCredentialsFromDb);
			return new ResponseEntity<Void>(HttpStatus.OK);

		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/profile/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUserProfile(@RequestBody UserProfileModel userProfileModel,
			@PathVariable(value = "id") Integer id) {

			UserProfile userProfileFromDb = userService.getUserProfile(id);
			userProfileFromDb.setFirstName(userProfileModel.getFirstName());
			userProfileFromDb.setLastName(userProfileModel.getLastName());
			userService.update(userProfileFromDb);
			return new ResponseEntity<Void>(HttpStatus.OK);
	}

	private UserProfileModel entity2model(UserProfile userProfile) {
		UserProfileModel userProfileModel = new UserProfileModel();
		userProfileModel.setFirstName(userProfile.getFirstName());
		userProfileModel.setLastName(userProfile.getLastName());
		userProfileModel.setUserCredentialsId(userProfile.getUserCredentialsId());
		return userProfileModel;
	}

}
