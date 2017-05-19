package com.chuyeu.training.myapp.webapp.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.chuyeu.training.myapp.dao.api.filters.CommonFilter;
import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.services.IProductService;
import com.chuyeu.training.myapp.webapp.memorization.Memoization;
import com.chuyeu.training.myapp.webapp.models.EntityModelWrapper;
import com.chuyeu.training.myapp.webapp.models.ProductModel;
import com.chuyeu.training.myapp.webapp.models.parts.IdModel;

@RestController
@RequestMapping("/product")
public class ProductController {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Inject
	private IProductService productService;

	@Autowired
	ConversionService conversionService;

	@SuppressWarnings("unchecked")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> getAll(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "column", required = false) String column,
			@RequestParam(value = "direction", required = false) String direction,
			@RequestParam(value = "limit", required = false) Integer limit, HttpServletRequest req) {

		EntityModelWrapper<ProductModel> wrapper = new EntityModelWrapper<ProductModel>();
		String path = new StringBuilder(req.getServletPath()).append("?").append(req.getQueryString()).toString();
		Object data = Memoization.getInstance().getData(path, new Date());

		if (data == null) {

			CommonFilter commonFilter = new CommonFilter(page, limit, column, direction);

			List<Product> listProductsFromDb = productService.getAll(commonFilter);
			List<ProductModel> listProductModel = new ArrayList<>();

			for (Product product : listProductsFromDb) {
				ProductModel model = conversionService.convert(product, ProductModel.class);
				listProductModel.add(model);
			}

			wrapper.setListEntityModel(listProductModel);
			Integer quantity = productService.getProductQuantity();
			Integer pageCount = (int) Math.ceil((double) quantity / limit);
			wrapper.setPageCount(pageCount);

			Memoization.getInstance().putData(path, wrapper);
			LOGGER.info("Put data with path={} in cache", path);
		} else {
			wrapper = (EntityModelWrapper<ProductModel>) data;
			LOGGER.info("Took the data from cache by path={}", path);
		}
		return new ResponseEntity<EntityModelWrapper<ProductModel>>(wrapper, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable(value = "id") Integer id, HttpServletRequest req) {

		ProductModel model = null;

		String path = req.getServletPath();
		Object data = Memoization.getInstance().getData(path, new Date());
		if (data == null) {
			Product product = productService.get(id);
			model = conversionService.convert(product, ProductModel.class);
			Memoization.getInstance().putData(path, model);
			LOGGER.info("Put productModel ={} with path={} in cache", model, path);
		} else {
			model = (ProductModel) data;
			LOGGER.info("Took the productModel ={} from cache by path={}", model, path);
		}
		return new ResponseEntity<ProductModel>(model, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createNewProduct(@RequestBody ProductModel productModel) {

		if (productModel == null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
		Product product = conversionService.convert(productModel, Product.class);
		Integer id = productService.add(product);
		LOGGER.info("Create product ={} with id ={}", product, id);
		return new ResponseEntity<IdModel>(new IdModel(id), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateProduct(@RequestBody ProductModel productModel,
			@PathVariable(value = "id") Integer id) {

		Product productFromDb = productService.get(id);
		productFromDb.setNameRu(productModel.getNameRu());
		productFromDb.setDescriptionRu(productModel.getDescriptionRu());
		productFromDb.setNameEn(productModel.getNameEn());
		productFromDb.setDescriptionEn(productModel.getDescriptionEn());
		productFromDb.setActive(productModel.getActive());
		productFromDb.setBasePrice(productModel.getBasePrice());
		productService.update(productFromDb);
		LOGGER.info("Update product with id ={}", id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProduct(@PathVariable(value = "id") Integer id) {
		productService.delete(id);
		LOGGER.info("Delete product with id ={}", id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

}
