package com.chuyeu.training.myapp.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
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
import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.services.IProductService;
import com.chuyeu.training.myapp.services.impl.UserAuthStorage;
import com.chuyeu.training.myapp.webapp.models.ProductModel;
import com.chuyeu.training.myapp.webapp.models.EntityModelWrapper;
import com.chuyeu.training.myapp.webapp.models.parts.IdModel;

@RestController
@RequestMapping(value ={"/product"},produces="application/json;charset=UTF-8")
public class ProductController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
	
	@Inject
	private IProductService productService;
	
	@Inject
    private ApplicationContext context;

	// +++
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "column", required = false) String column,
			@RequestParam(value = "direction", required = false) String direction,
			@RequestParam(value = "limit", required = false) Integer limit) {

		CommonFilter commonFilter = new CommonFilter(page,limit,column,direction);

		List<Product> listProductsFromDb = productService.getAll(commonFilter);
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
	
	// +++
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable(value = "id") Integer id) {
		
		UserAuthStorage userAuthStorage = context.getBean(UserAuthStorage.class);
		Product product;
		try {
			product = productService.get(id);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<String>("This product does not exist", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<ProductModel>(entity2model(product), HttpStatus.OK);

	}

	// +++
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createNewProduct(@RequestBody ProductModel productModel) {

		if (productModel == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		Integer id = productService.add(model2entity(productModel));
		return new ResponseEntity<IdModel>(new IdModel(id), HttpStatus.CREATED);
	}

	// +++
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateProduct(@RequestBody ProductModel productModel,
			@PathVariable(value = "id") Integer id) {

		Product productFromDb = productService.get(id);
		productFromDb.setName(productModel.getName());
		productFromDb.setDescription(productModel.getDescription());
		productFromDb.setActive(productModel.getActive());
		productFromDb.setBasePrice(productModel.getBasePrice());
		productService.update(productFromDb);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// +++
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProduct(@PathVariable(value = "id") Integer id) {
		productService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	public ProductModel entity2model(Product product) {
		ProductModel model = new ProductModel();
		model.setName(product.getName());
		model.setDescription(product.getDescription());
		model.setActive(product.getActive());
		model.setBasePrice(product.getBasePrice());
		return model;
	}

	public Product model2entity(ProductModel model) {
		Product product = new Product();
		product.setName(model.getName());
		product.setDescription(model.getDescription());
		product.setActive(model.getActive());
		product.setBasePrice(model.getBasePrice());
		return product;
	}

}
