package com.chuyeu.training.myapp.webapp.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.dao.EmptyResultDataAccessException;
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
import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.services.IAttributeService;
import com.chuyeu.training.myapp.services.IOrderService;
import com.chuyeu.training.myapp.webapp.models.EntityModelWrapper;
import com.chuyeu.training.myapp.webapp.models.OrderModel;
import com.chuyeu.training.myapp.webapp.models.ProductModel;
import com.chuyeu.training.myapp.webapp.models.parts.IdModel;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Inject
	private IOrderService orderService;
	
	// Возможно нужен отдельный фильтр, с учетом роли юзера.
	
/*	
		@RequestMapping(method = RequestMethod.GET)
		public ResponseEntity<?> getAll(@RequestParam(value = "page", required = false) Integer page,
				@RequestParam(value = "column", required = false) String column,
				@RequestParam(value = "direction", required = false) String direction,
				@RequestParam(value = "limit", required = false) Integer limit,
				@RequestParam(value = "status", required = false) String status) {

			CommonFilter commonFilter = new CommonFilter(page,limit,column,direction,OrderStatus.valueOf(status));

			List<Product> listProductsFromDb = orderService.getAll(commonFilter);
			List<ProductModel> listProductModel = new ArrayList<>();

			for (Product product : listProductsFromDb) {
				ProductModel productModel = new ProductModel();
				productModel.setId(product.getId());
				productModel.setName(product.getName());
				productModel.setBasePrice(product.getBasePrice());
				listProductModel.add(productModel);
			}
			
			EntityModelWrapper<ProductModel> wrapper = new EntityModelWrapper<ProductModel>();

			wrapper.setListEntityModel(listProductModel);
			wrapper.setPageCount(productService.getProductQuantity());

			return new ResponseEntity<EntityModelWrapper<ProductModel>>(wrapper, HttpStatus.OK);
		}
		

		@RequestMapping(value = "/{id}", method = RequestMethod.GET)
		public ResponseEntity<?> getById(@PathVariable(value = "id") Integer id) {

			Product product;
			try {
				product = productService.get(id);
			} catch (EmptyResultDataAccessException e) {
				return new ResponseEntity<String>("This product does not exist", HttpStatus.BAD_REQUEST);
			}
			return new ResponseEntity<ProductModel>(entity2model(product), HttpStatus.OK);

		}*/


		@RequestMapping(method = RequestMethod.POST)
		public ResponseEntity<?> createOrder(@RequestBody OrderModel orderModel) {
			
			Order order = new Order();
			order.setCreated(new Date());
			order.setUserProfileId(orderModel.getUserProfileId());
			order.setOrderStatus(OrderStatus.BASKET);
			order.setTotalPrice(0d);
			Integer id = orderService.save(order);
			
			return new ResponseEntity<IdModel>(new IdModel(id), HttpStatus.CREATED);
		}


		@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
		public ResponseEntity<?> updateOrder(@RequestBody OrderModel orderModel,
				@PathVariable(value = "id") Integer id) {

			Order order = orderService.get(id);
			order.setTotalPrice(orderModel.getTotalPrice());
			order.setOrderStatus(orderModel.getOrderStatus());
			orderService.update(order);

			return new ResponseEntity<Void>(HttpStatus.OK);
		}


}
