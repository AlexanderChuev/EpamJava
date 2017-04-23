package com.chuyeu.training.myapp.webapp.controllers;

import java.util.ArrayList;
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

import com.chuyeu.training.myapp.dao.api.filters.ProductFilter;
import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.services.IProductService;
import com.chuyeu.training.myapp.webapp.models.IdModel;
import com.chuyeu.training.myapp.webapp.models.ProductModel;

@RestController
@RequestMapping("/product")
public class ProductController extends AbstractConroller {

	@Inject
	private IProductService productService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(@RequestParam(value = "page", required = false) Integer page) {

		ProductFilter productFilter = new ProductFilter();
		productFilter.setLimit(5);

		if (page == null) {
			productFilter.setPageNumber(1);
		} else {
			productFilter.setPageNumber(page);
		}

		List<Product> all = productService.getAll(productFilter);
		List<ProductModel> productModels = new ArrayList<>();

		for (Product product : all) {
			ProductModel productModel = new ProductModel();
			productModel.setName(product.getName());
			productModel.setBasePrice(product.getBasePrice());
			productModels.add(productModel);
		}

		return new ResponseEntity<List<ProductModel>>(productModels, HttpStatus.OK);
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

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createNewProduct(@RequestBody ProductModel productModel) {

		if (productModel == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		Integer id = productService.add(model2entity(productModel));
		return new ResponseEntity<IdModel>(new IdModel(id),HttpStatus.CREATED);
	}

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

	// А надо ли оно вообще?
	/*
	 * @RequestMapping(value = "/{id}", method = RequestMethod.DELETE) public
	 * ResponseEntity<Void> deleteProduct(@PathVariable(value = "id") Integer
	 * id) { productService.delete(id); return new
	 * ResponseEntity<Void>(HttpStatus.OK); }
	 */

}
