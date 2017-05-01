package com.chuyeu.training.myapp.webapp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chuyeu.training.myapp.datamodel.ProductVariant;
import com.chuyeu.training.myapp.services.IProductVariantService;
import com.chuyeu.training.myapp.webapp.models.ProductVariantModel;
import com.chuyeu.training.myapp.webapp.models.parts.IdModel;

@RestController
@RequestMapping("/product-variant")
public class ProductVariantController {

	@Inject
	private IProductVariantService productVariantService;

	// +++
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllByProduct(@RequestParam(value = "product-id", required = false) Integer productId) {

		List<ProductVariantModel> productVariantsModel = new ArrayList<>();
		List<ProductVariant> productVariants = productVariantService.getAllByProduct(productId);

		for (ProductVariant productVariant : productVariants) {
			productVariantsModel.add(entity2model(productVariant, productId));
		}
		return new ResponseEntity<List<ProductVariantModel>>(productVariantsModel, HttpStatus.OK);
	}

	// +++
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable(value = "id") Integer variantId) {

		ProductVariant productVariant = productVariantService.getProductVariant(variantId);
		ProductVariantModel productVariantModel = new ProductVariantModel();
		productVariantModel.setId(productVariant.getId());
		productVariantModel.setProductId(productVariant.getProductId());
		productVariantModel.setAvailableQuantity(productVariant.getAvailableQuantity());
		productVariantModel.setPriceInfluence(productVariant.getPriceInfluence());

		return new ResponseEntity<ProductVariantModel>(productVariantModel, HttpStatus.OK);
	}

	// +++
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createProductVariant(@RequestBody ProductVariantModel productVariantModel) {

		if (productVariantModel == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}

		ProductVariant productVariant = new ProductVariant();
		productVariant.setProductId(productVariantModel.getProductId());
		productVariant.setAvailableQuantity(productVariantModel.getAvailableQuantity());
		productVariant.setPriceInfluence(productVariantModel.getPriceInfluence());
		Integer id = productVariantService.saveOrUpdate(productVariant);
		return new ResponseEntity<IdModel>(new IdModel(id), HttpStatus.CREATED);
	}

	// +++
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateProductVariant(@RequestBody ProductVariantModel productVariantModel,
			@PathVariable(value = "id") Integer id) {

		ProductVariant productVariant = productVariantService.getProductVariant(id);
		productVariant.setAvailableQuantity(productVariantModel.getAvailableQuantity());
		productVariant.setPriceInfluence(productVariantModel.getPriceInfluence());
		productVariantService.saveOrUpdate(productVariant);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// +++
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProductVariant(@PathVariable(value = "id") Integer id) {
		productVariantService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	private ProductVariantModel entity2model(ProductVariant productVariant, Integer id) {
		ProductVariantModel productVariantModel = new ProductVariantModel();
		productVariantModel.setId(productVariant.getId());
		productVariantModel.setProductId(id);
		productVariantModel.setAvailableQuantity(productVariant.getAvailableQuantity());
		productVariantModel.setPriceInfluence(productVariant.getPriceInfluence());
		return productVariantModel;
	}
}
