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
import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.datamodel.Product;
import com.chuyeu.training.myapp.datamodel.ProductVariant;
import com.chuyeu.training.myapp.services.IAttributeService;
import com.chuyeu.training.myapp.services.IProductService;
import com.chuyeu.training.myapp.services.IProductVariantService;
import com.chuyeu.training.myapp.services.IVariantsService;
import com.chuyeu.training.myapp.webapp.models.AttributeModel;
import com.chuyeu.training.myapp.webapp.models.ProductModel;
import com.chuyeu.training.myapp.webapp.models.ProductVariantModel;

@RestController
@RequestMapping("/product")
public class ProductController extends AbstractConroller {

	@Inject
	private IProductService productService;

	@Inject
	private IProductVariantService productVariantService;

	@Inject
	private IAttributeService attributeService;

	@Inject
	private IVariantsService variantsService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ProductModel>> getAll(@RequestParam(value = "page", required = false) Integer page) {

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
			productModels.add(entity2model(product, null));
		}

		return new ResponseEntity<List<ProductModel>>(productModels, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable(value = "id") Integer id) {

		List<ProductVariant> variantsByProduct;
		Product product;

		try {
			variantsByProduct = productVariantService.getAllByProduct(id);
			product = productService.get(id);
		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<String>("This product does not exist", HttpStatus.BAD_REQUEST);
		}

		List<ProductVariantModel> productVariantModels = new ArrayList<>();
		for (ProductVariant productVariant : variantsByProduct) {
			List<Attribute> attributes = attributeService.getProductVariantAttributes(productVariant.getId());
			ProductVariantModel productVariantModel = entity2model(productVariant, product, attributes);
			productVariantModels.add(productVariantModel);
		}

		return new ResponseEntity<ProductModel>(entity2model(product, productVariantModels), HttpStatus.OK);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> createNewProduct(@RequestBody ProductModel productModel) {

		if (productModel != null) {
			Integer productId = productService.add(model2entity(productModel));
			List<ProductVariantModel> listProductVariantModel = productModel.getListProductVariantModel();

			if (listProductVariantModel != null) {
				
				for (ProductVariantModel productVariantModel : listProductVariantModel) {
					List<AttributeModel> listAttributesModel = productVariantModel.getAttributes();
					List<Attribute> listAttributes = listModel2listEntity(listAttributesModel);
					List<Integer> listAttributeId = new ArrayList<>();
					
					for (Attribute attribute : listAttributes) {
						Integer attributeId = attributeService.getIdByNameAndValue(attribute.getName(),attribute.getValue());
						listAttributeId.add(attributeId);
					}

					ProductVariant productVariant = model2entity(productVariantModel, productId);
					Integer productVariantId = productVariantService.saveOrUpdate(productVariant);
					variantsService.add(productVariantId, listAttributeId);
				}

			} else {
				return new ResponseEntity<String>("Product can not be saved ", HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> updateProduct(@RequestBody ProductModel productModel,
			@PathVariable(value = "id") Integer id) {

		Product productFromDb = productService.get(id);
		productFromDb.setName(productModel.getName());
		productFromDb.setDescription(productModel.getDescription());
		productFromDb.setActive(productModel.getActive());
		productFromDb.setBasePrice(productModel.getBasePrice());
		productService.update(productFromDb);
		
		List<ProductVariant> ProductVariantsFromDb = productVariantService.getAllByProduct(id);
		List<ProductVariantModel> listProductVariantModel = productModel.getListProductVariantModel();
		
		for (ProductVariant productVariant : ProductVariantsFromDb) {
			productVariant.set
		}
		
		
		for (ProductVariant productVariant : productVariants) {
			productVariantService.saveOrUpdate(productVariant);
		}
		
		productModel.getListProductVariantModel();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteProduct(@PathVariable(value = "id") Integer id) {
		productService.delete(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	

}
