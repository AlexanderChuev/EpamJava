package com.chuyeu.training.myapp.webapp.controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.datamodel.Order;
import com.chuyeu.training.myapp.datamodel.OrderStatus;
import com.chuyeu.training.myapp.datamodel.UserCredentials;
import com.chuyeu.training.myapp.datamodel.UserProfile;
import com.chuyeu.training.myapp.datamodel.UserRole;
import com.chuyeu.training.myapp.services.IOrderService;
import com.chuyeu.training.myapp.services.IUserService;
import com.chuyeu.training.myapp.services.util.UserAuthStorage;
import com.chuyeu.training.myapp.webapp.models.EntityModelWrapper;
import com.chuyeu.training.myapp.webapp.models.UserCredentialsModel;
import com.chuyeu.training.myapp.webapp.models.UserProfileModel;
import com.chuyeu.training.myapp.webapp.models.UserWrapper;

@RestController
@RequestMapping("/user")
public class UserController {

	private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Inject
	private IUserService userService;

	@Inject
	private IOrderService orderService;

	@Inject
	private ApplicationContext context;

	@Autowired
	ConversionService conversionService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllUserProfile(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "column", required = false) String column,
			@RequestParam(value = "direction", required = false) String direction,
			@RequestParam(value = "limit", required = false) Integer limit) {

		CommonFilter commonFilter = new CommonFilter(page, limit, column, direction);
		List<UserProfile> listUserProfileFromDB = userService.getAll(commonFilter);
		List<UserProfileModel> listUserProfileModel = new ArrayList<>();

		for (UserProfile userProfile : listUserProfileFromDB) {
			UserProfileModel userProfileModel = conversionService.convert(userProfile, UserProfileModel.class);
			listUserProfileModel.add(userProfileModel);
		}
		
		EntityModelWrapper<UserProfileModel> wrapper = new EntityModelWrapper<UserProfileModel>();
		wrapper.setListEntityModel(listUserProfileModel);
		Integer quantity = userService.getUserProfileQuantity();
		Integer pageCount = (int) Math.ceil((double) quantity / limit);
		wrapper.setPageCount(pageCount);
		LOGGER.info("An admin got list userProfiles");
		return new ResponseEntity<EntityModelWrapper<UserProfileModel>>(wrapper, HttpStatus.OK);
	}

	@RequestMapping(value = "/credentials/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCredentials(@PathVariable(value = "id") Integer id) {
		UserCredentials userCredentials = userService.getUserCredentials(id);
		UserCredentialsModel userCredentialsModel = conversionService.convert(userCredentials, UserCredentialsModel.class);
		LOGGER.info("{} got credentials by id= {}", userCredentialsModel, id);
		return new ResponseEntity<UserCredentialsModel>(userCredentialsModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getProfile(@PathVariable(value = "id") Integer id) {
		UserProfile userProfile = userService.getUserProfile(id);
		UserProfileModel userProfileModel = conversionService.convert(userProfile, UserProfileModel.class);
		LOGGER.info("{} got userProfileModel by id={}", userProfileModel,id);
		return new ResponseEntity<UserProfileModel>(userProfileModel, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody UserWrapper userWrapper, HttpServletRequest req) {

		if (req.getHeader("Authorization") != null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		if (userWrapper == null || userWrapper.getUserCredentialsModel() == null || userWrapper.getUserProfileModel() == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		UserCredentials userCredentials = conversionService.convert(userWrapper, UserCredentials.class);
		UserProfile userProfile = conversionService.convert(userWrapper, UserProfile.class);
		Integer registrationId = userService.registration(userProfile, userCredentials);
		LOGGER.info("User with userProfile ={} is registered, id={}", userProfile, registrationId);
		UserProfile userProfileFromDb = userService.getUserProfile(registrationId);

		Order order = new Order();
		order.setOrderStatus(OrderStatus.CART);
		order.setUserProfileId(userProfileFromDb.getId());
		orderService.save(order);
		LOGGER.info("Cart for user with userProfile ={} is added", userProfile);
		
		UserCredentials userCredentialsFromDb = userService
				.getUserCredentials(userProfileFromDb.getUserCredentialsId());

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		headers.set("Authorization", convert(userCredentialsFromDb));
		LOGGER.info("Send authorization data for user with id ={}", registrationId);
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
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
			LOGGER.info("Update userCredentials with id ={}", userAuthStorage.getId());
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json; charset=UTF-8");
			headers.set("Authorization", convert(userCredentialsFromDb));
			LOGGER.info("Return new authorization data for user with id ={}", userAuthStorage.getId());
			return new ResponseEntity<Void>(headers, HttpStatus.OK);

		} else {
			LOGGER.error("Attempt to access other people's resources, id ={}", userAuthStorage.getId());
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
		LOGGER.info("Update userProfile with id={}. setFirstName ={} setLastName ={}", userProfileFromDb.getId(),
				userProfileModel.getFirstName(), userProfileModel.getLastName());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	private String convert(UserCredentials userCredentialsFromDb) {

		String base64Decode = null;
		try {
			base64Decode = Base64.getEncoder().encodeToString(
					(userCredentialsFromDb.getEmail() + ":" + userCredentialsFromDb.getPassword()).getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Error base64Decode");
		}
		return base64Decode;
	}

}
