package com.chuyeu.training.myapp.webapp.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.dao.api.filters.OrderFilter;
import com.chuyeu.training.myapp.datamodel.Order;
import com.chuyeu.training.myapp.datamodel.OrderStatus;
import com.chuyeu.training.myapp.datamodel.UserRole;
import com.chuyeu.training.myapp.services.IOrderService;
import com.chuyeu.training.myapp.services.util.UserAuthStorage;
import com.chuyeu.training.myapp.webapp.models.EntityModelWrapper;
import com.chuyeu.training.myapp.webapp.models.OrderModel;

@RestController
@RequestMapping("/order")
public class OrderController {

	private final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

	@Inject
	private IOrderService orderService;

	@Inject
	private ApplicationContext context;

	@Autowired
	ConversionService conversionService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "column", required = false) String column,
			@RequestParam(value = "direction", required = false) String direction,
			@RequestParam(value = "limit", required = false) Integer limit,
			@RequestParam(value = "status", required = false) String status) {

		UserAuthStorage userAuthStorage = context.getBean(UserAuthStorage.class);

		CommonFilter commonFilter = new CommonFilter(page, limit, column, direction);
		OrderFilter orderFilter = new OrderFilter();
		orderFilter.setOrderStatus(OrderStatus.valueOf(status.toUpperCase()));
		orderFilter.setId(userAuthStorage.getId());
		orderFilter.setUserRole(userAuthStorage.getUserRole());

		List<Order> listOrdersFromDb = orderService.getAll(commonFilter, orderFilter);
		EntityModelWrapper<OrderModel> wrapper = new EntityModelWrapper<OrderModel>();

		if (!(listOrdersFromDb == null || listOrdersFromDb.isEmpty())) {

			List<OrderModel> listOrderModel = new ArrayList<>();

			for (Order orderFromDb : listOrdersFromDb) {
				OrderModel orderModel = conversionService.convert(orderFromDb, OrderModel.class);
				listOrderModel.add(orderModel);
			}
			wrapper.setListEntityModel(listOrderModel);
			wrapper.setPageCount(null);
			LOGGER.info("Get all orders for userAuthStorage ={} with commonFilter ={}", userAuthStorage, commonFilter);
			return new ResponseEntity<EntityModelWrapper<OrderModel>>(wrapper, HttpStatus.OK);

		} else {
			LOGGER.info("Return empty list of orders with commonFilter ={}", commonFilter);
			return new ResponseEntity<EntityModelWrapper<OrderModel>>(wrapper, HttpStatus.OK);
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getByOrderId(@PathVariable(value = "id") Integer id) {

		UserAuthStorage userAuthStorage = context.getBean(UserAuthStorage.class);
		Order order = orderService.get(id);
		if (order.getUserProfileId().equals(userAuthStorage.getId())
				|| UserRole.ADMIN.equals(userAuthStorage.getUserRole())) {
			OrderModel orderModel = conversionService.convert(order, OrderModel.class);
			LOGGER.info("The user with userAuthStorage ={} got order ={}", userAuthStorage,orderModel);
			return new ResponseEntity<OrderModel>(orderModel, HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateOrder(@RequestBody OrderModel orderModel, @PathVariable(value = "id") Integer id) {

		UserAuthStorage userAuthStorage = context.getBean(UserAuthStorage.class);
		Order order = orderService.get(id);

		if (order.getUserProfileId().equals(userAuthStorage.getId())) {
			order.setOrderStatus(OrderStatus.IN_PROCESSING);
			order.setCreated(new Date());
			orderService.update(order);
			LOGGER.info("Client made an order ={}, client id ={}", order, userAuthStorage.getId());

			Order newOrder = new Order();
			newOrder.setOrderStatus(OrderStatus.CART);
			newOrder.setUserProfileId(userAuthStorage.getId());
			orderService.save(newOrder);
			LOGGER.info("The new cart for user with id ={}, was created", userAuthStorage.getId());
			return new ResponseEntity<Void>(HttpStatus.OK);

		} else if (UserRole.ADMIN.equals(userAuthStorage.getUserRole())
				&& !OrderStatus.CART.equals(order.getOrderStatus())) {
			order.setOrderStatus(orderModel.getOrderStatus());
			orderService.update(order);
			LOGGER.info("Admin changed order={} to new status ={}", order, orderModel.getOrderStatus());
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}

}
