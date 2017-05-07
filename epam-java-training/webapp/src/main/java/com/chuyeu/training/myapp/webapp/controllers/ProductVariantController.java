package com.chuyeu.training.myapp.webapp.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
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
import com.chuyeu.training.myapp.webapp.memorization.Memoization;
import com.chuyeu.training.myapp.webapp.models.ProductVariantModel;
import com.chuyeu.training.myapp.webapp.models.parts.IdModel;

@RestController
@RequestMapping("/product-variant")
public class ProductVariantController {

	@Inject
	private IProductVariantService productVariantService;

	@Autowired
	ConversionService conversionService;

	// +++
	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAllByProduct(@RequestParam(value = "product-id", required = false) Integer productId,
			HttpServletRequest req) {

		List<ProductVariantModel> productVariantsModel = new ArrayList<>();
		String path = new StringBuilder(req.getServletPath()).append("?").append(req.getQueryString()).toString();
		Object data = Memoization.getInstance().getData(path, new Date());
		if (data == null) {
			List<ProductVariant> productVariants = productVariantService.getAllByProduct(productId);

			for (ProductVariant productVariant : productVariants) {
				ProductVariantModel productVariantModel = conversionService.convert(productVariant,
						ProductVariantModel.class);
				productVariantModel.setProductId(productId);
				productVariantsModel.add(productVariantModel);
			}
			Memoization.getInstance().putData(path, productVariantsModel);
		} else {
			productVariantsModel = (List<ProductVariantModel>) data;
		}
		return new ResponseEntity<List<ProductVariantModel>>(productVariantsModel, HttpStatus.OK);
	}

	// +++
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable(value = "id") Integer variantId, HttpServletRequest req) {
		ProductVariantModel productVariantModel;
		Object data = Memoization.getInstance().getData(req.getServletPath(), new Date());
		if (data == null) {
			ProductVariant productVariant = productVariantService.getProductVariant(variantId);
			productVariantModel = conversionService.convert(productVariant, ProductVariantModel.class);
			productVariantModel.setProductId(productVariant.getId());
			Memoization.getInstance().putData(req.getServletPath(), productVariantModel);
		} else {
			productVariantModel = (ProductVariantModel) data;
		}
		return new ResponseEntity<ProductVariantModel>(productVariantModel, HttpStatus.OK);
	}

	// +++
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createProductVariant(@RequestBody ProductVariantModel productVariantModel) {

		if (productVariantModel == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		ProductVariant productVariant = conversionService.convert(productVariantModel, ProductVariant.class);
		Integer id = productVariantService.save(productVariant);
		return new ResponseEntity<IdModel>(new IdModel(id), HttpStatus.CREATED);
	}

	// +++
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateProductVariant(@RequestBody ProductVariantModel productVariantModel,
			@PathVariable(value = "id") Integer id) {

		ProductVariant productVariant = productVariantService.getProductVariant(id);
		productVariant.setAvailableQuantity(productVariantModel.getAvailableQuantity());
		productVariant.setPriceInfluence(productVariantModel.getPriceInfluence());
		productVariantService.update(productVariant);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	// +++
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProductVariant(@PathVariable(value = "id") Integer id) {
		productVariantService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
