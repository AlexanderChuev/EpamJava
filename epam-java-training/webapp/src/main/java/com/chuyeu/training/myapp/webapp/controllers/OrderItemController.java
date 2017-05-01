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

import com.chuyeu.training.myapp.datamodel.Order;
import com.chuyeu.training.myapp.datamodel.OrderItem;
import com.chuyeu.training.myapp.services.IOrderItemService;
import com.chuyeu.training.myapp.services.IOrderService;
import com.chuyeu.training.myapp.services.impl.UserAuthStorage;
import com.chuyeu.training.myapp.webapp.models.OrderItemModel;
import com.chuyeu.training.myapp.webapp.models.parts.QuantityModel;

@RestController
@RequestMapping("/order-item")
public class OrderItemController {

	@Inject
	private IOrderItemService orderItemService;

	@Inject
	private IOrderService orderService;

	@Inject
	private ApplicationContext context;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllByOrderId(@RequestParam(value = "order-id", required = false) Integer orderId) {

		List<OrderItem> listOrderItems = orderItemService.getAllByOrderId(orderId);
		List<OrderItemModel> listOrderItemsModel = new ArrayList<>();

		for (OrderItem orderItem : listOrderItems) {
			listOrderItemsModel.add(entyty2model(orderItem));
		}
		return new ResponseEntity<List<OrderItemModel>>(listOrderItemsModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable(value = "id") Integer id) {
		OrderItem orderItem = orderItemService.get(id);
		return new ResponseEntity<OrderItemModel>(entyty2model(orderItem), HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createOrderItem(@RequestBody OrderItemModel orderItemModel) {

		UserAuthStorage userAuthStorage = context.getBean(UserAuthStorage.class);
		Order order = orderService.get(orderItemModel.getOrderId());

		if (order.getUserProfileId().equals(userAuthStorage.getId())) {

			OrderItem orderItem = new OrderItem();
			orderItem.setProductVariantId(orderItemModel.getProductVariantId());
			orderItem.setOrderQuantity(orderItemModel.getOrderQuantity());
			orderItem.setOrderId(orderItemModel.getOrderId());

			orderItemService.saveOrUpdate(orderItem);

			return new ResponseEntity<Void>(HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateOrderItem(@RequestBody QuantityModel quantityModel,
			@PathVariable(value = "id") Integer id) {

		OrderItem orderItem = orderItemService.get(id);
		UserAuthStorage userAuthStorage = context.getBean(UserAuthStorage.class);
		Order order = orderService.get(orderItem.getOrderId());

		if (order.getUserProfileId().equals(userAuthStorage.getId())) {

			orderItem.setOrderQuantity(quantityModel.getQuantity());
			orderItemService.saveOrUpdate(orderItem);
			return new ResponseEntity<Void>(HttpStatus.OK);

		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteOrderItem(@PathVariable(value = "id") Integer id) {

		OrderItem orderItem = orderItemService.get(id);
		UserAuthStorage userAuthStorage = context.getBean(UserAuthStorage.class);
		Order order = orderService.get(orderItem.getOrderId());

		if (order.getUserProfileId().equals(userAuthStorage.getId())) {
			orderItemService.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} else {
			return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
		}
	}

	private OrderItemModel entyty2model(OrderItem orderItem) {
		OrderItemModel orderItemModel = new OrderItemModel();
		orderItemModel.setId(orderItem.getId());
		orderItemModel.setProductVariantId(orderItem.getProductVariantId());
		orderItemModel.setOrderQuantity(orderItem.getOrderQuantity());
		return orderItemModel;
	}

}
