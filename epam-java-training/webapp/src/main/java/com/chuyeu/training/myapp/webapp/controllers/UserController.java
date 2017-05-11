package com.chuyeu.training.myapp.webapp.controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

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

	@Inject
	private IUserService userService;

	@Inject
	private IOrderService orderService;

	@Inject
	private ApplicationContext context;

	@Autowired
	ConversionService conversionService;

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
			UserProfileModel userProfileModel = conversionService.convert(userProfile, UserProfileModel.class);
			listUserProfileModel.add(userProfileModel);
		}

		EntityModelWrapper<UserProfileModel> wrapper = new EntityModelWrapper<UserProfileModel>();

		wrapper.setListEntityModel(listUserProfileModel);
		Integer quantity = userService.getUserProfileQuantity();
		Integer pageCount = (int) Math.ceil((double) quantity / limit);
		wrapper.setPageCount(pageCount);

		return new ResponseEntity<EntityModelWrapper<UserProfileModel>>(wrapper, HttpStatus.OK);
	}

	// +++
	@RequestMapping(value = "/credentials/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getCredentials(@PathVariable(value = "id") Integer id) {
		UserCredentials userCredentials = userService.getUserCredentials(id);
		UserCredentialsModel userCredentialsModel = conversionService.convert(userCredentials,
				UserCredentialsModel.class);
		return new ResponseEntity<UserCredentialsModel>(userCredentialsModel, HttpStatus.OK);
	}

	// +++
	@RequestMapping(value = "/profile/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getProfile(@PathVariable(value = "id") Integer id) {
		UserProfile userProfile = userService.getUserProfile(id);
		UserProfileModel userProfileModel = conversionService.convert(userProfile, UserProfileModel.class);
		return new ResponseEntity<UserProfileModel>(userProfileModel, HttpStatus.OK);
	}

	// +++
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> registration(@RequestBody UserWrapper userWrapper,  HttpServletRequest req) {
		
		if(req.getHeader("Authorization")!=null){
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		
		if (userWrapper.getUserCredentialsModel() == null || userWrapper.getUserProfileModel() == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		UserCredentials userCredentials = conversionService.convert(userWrapper, UserCredentials.class);
		UserProfile userProfile = conversionService.convert(userWrapper, UserProfile.class);
		Integer registrationId = userService.registration(userProfile, userCredentials);
		UserProfile userProfileFromDb = userService.getUserProfile(registrationId);

		Order order = new Order();
		order.setOrderStatus(OrderStatus.CART);
		order.setUserProfileId(userProfileFromDb.getId());

		orderService.save(order);

		UserCredentials userCredentialsFromDb = userService
				.getUserCredentials(userProfileFromDb.getUserCredentialsId());

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=UTF-8");
		headers.set("Authorization", convert(userCredentialsFromDb));
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

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Type", "application/json; charset=UTF-8");
			headers.set("Authorization", convert(userCredentialsFromDb));
			return new ResponseEntity<Void>(headers, HttpStatus.OK);

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

	private String convert(UserCredentials userCredentialsFromDb) {

		String base64Decode = null;
		try {
			base64Decode = Base64.getEncoder().encodeToString(
					(userCredentialsFromDb.getEmail() + ":" + userCredentialsFromDb.getPassword()).getBytes("utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return base64Decode;
	}

}
