package services;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.util.Assert;

import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.datamodel.ProductVariant;
import com.chuyeu.training.myapp.services.IAttributeService;
import com.chuyeu.training.myapp.services.IProductService;
import com.chuyeu.training.myapp.services.IProductVariantService;
import com.chuyeu.training.myapp.services.IVariantService;

public class VariantsServiceTest extends AbstractTesst{

	@Test
	public void test() {
		Assert.notNull(variantService, "The variantsService must not be null");
		Assert.notNull(productService, "The productService must not be null");
		Assert.notNull(productVariantService, "The productVariantsService must not be null");
		Assert.notNull(attributeService, "The attributeService must not be null");
	}
	
	@Test
	public void addTest(){
	
		Integer productId = productService.add(createProduct());
		ProductVariant productVariant = createProductVariant(productId);
		Integer productVariantId = productVariantService.saveOrUpdate(productVariant);
		variantService.add(productVariantId, getSavedAttributeId());
	}
	
	/*@Test
	public void deleteTest(){
		
		Integer productId = productService.add(createProduct());
		
		ProductVariant productVariant = createProductVariant(productId);
		productVariantService.saveOrUpdate(productVariant);
		
		Integer productVariantId = productVariantService.getAllByProduct(productId).get(0).getProductVariant().getId();
		
		Integer attributeId = getSavedAttributeId();
		
		List<Integer> list = new ArrayList<>();
		list.add(attributeId);
		variantService.add(productVariantId, list);
		
		variantService.delete(attributeId);
		variantService.delete(attributeId);
	}*/
	
	private Integer getSavedAttributeId(){
		Attribute attribute = createAttribute();
		attributeService.add(attribute);
		return attributeService.getIdByNameAndValue(attribute.getName(), attribute.getValue());
	}
	
}
