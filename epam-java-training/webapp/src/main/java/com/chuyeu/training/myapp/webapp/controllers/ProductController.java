package com.chuyeu.training.myapp.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chuyeu.training.myapp.dao.api.filters.ProductFilter;
import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.services.IProductService;
import com.chuyeu.training.myapp.webapp.models.ProductModel;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Inject
	private IProductService productService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProductModel>> getAll(@RequestParam(value="page", required = false) Integer page) {

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
			productModels.add(entity2model(product));
		}
		
		return new ResponseEntity<List<ProductModel>>(productModels,HttpStatus.OK);
	}

	private ProductModel entity2model(Product product) {
		ProductModel model = new ProductModel();
		model.setName(product.getName());
		model.setDescription(product.getDescription());
        return model;
    }
}
