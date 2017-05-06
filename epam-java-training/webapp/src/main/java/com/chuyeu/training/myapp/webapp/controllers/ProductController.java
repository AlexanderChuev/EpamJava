package com.chuyeu.training.myapp.webapp.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
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
import com.chuyeu.training.myapp.webapp.memorization.MemMap;
import com.chuyeu.training.myapp.webapp.models.EntityModelWrapper;
import com.chuyeu.training.myapp.webapp.models.ProductModel;
import com.chuyeu.training.myapp.webapp.models.parts.IdModel;

@RestController
@RequestMapping(value = { "/product" }, produces = "application/json;charset=UTF-8")
public class ProductController {

	@Inject
	private IProductService productService;

	@Autowired
	ConversionService conversionService;
	

	// +++
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "column", required = false) String column,
			@RequestParam(value = "direction", required = false) String direction,
			@RequestParam(value = "limit", required = false) Integer limit) {
		
		if (page == null) {
			page = 1;
		}

		if (limit == null) {
			limit = 2;
		}

		CommonFilter commonFilter = new CommonFilter(page, limit, column, direction);

		List<Product> listProductsFromDb = productService.getAll(commonFilter);
		List<ProductModel> listProductModel = new ArrayList<>();

		for (Product product : listProductsFromDb) {
			ProductModel model = conversionService.convert(product, ProductModel.class);
			listProductModel.add(model);
		}

		EntityModelWrapper<ProductModel> wrapper = new EntityModelWrapper<ProductModel>();

		wrapper.setListEntityModel(listProductModel);
		Integer quantity = productService.getProductQuantity();
		Integer pageCount = (int) Math.ceil((double) quantity / limit);
		wrapper.setPageCount(pageCount);

		return new ResponseEntity<EntityModelWrapper<ProductModel>>(wrapper, HttpStatus.OK);
	}

	// +++
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable(value = "id") Integer id, HttpServletRequest req) {
		
		ProductModel model;
		
		Object data = MemMap.getInstance().getData(req.getServletPath(), new Date());
		if(data==null){
		
		Product product;
		try {
			product = productService.get(id);
			System.out.println("Y");
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<String>("This product does not exist", HttpStatus.BAD_REQUEST);
		}
		model = conversionService.convert(product, ProductModel.class);
		MemMap.getInstance().putData(req.getServletPath(), model);
		
		} else {
			model= (ProductModel) data;
			System.out.println("Map");
		}
		
		return new ResponseEntity<ProductModel>(model, HttpStatus.OK);

	}

	// +++
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createNewProduct(@RequestBody ProductModel productModel) {

		if (productModel == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		Product product = conversionService.convert(productModel, Product.class);
		Integer id = productService.add(product);
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

}
