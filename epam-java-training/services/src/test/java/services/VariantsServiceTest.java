package services;

import org.junit.Test;
import org.springframework.util.Assert;

import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.datamodel.ProductVariant;

public class VariantsServiceTest extends AbstractTesst{

	@Test
	public void test() {
		Assert.notNull(variantService, "The variantsService must not be null");
		Assert.notNull(productService, "The productService must not be null");
		Assert.notNull(productVariantService, "The productVariantsService must not be null");
		Assert.notNull(attributeService, "The attributeService must not be null");
	}
	
	//+++
	@Test
	public void addTest(){
	
		Integer productId = productService.add(createProduct());
		ProductVariant productVariant = createProductVariant(productId);
		Integer productVariantId = productVariantService.saveOrUpdate(productVariant);
		variantService.add(productVariantId, getSavedAttributeId());
	}
	
	//+++
	@Test
	public void deleteTest(){
		
		Integer productId = productService.add(createProduct());
		ProductVariant productVariant = createProductVariant(productId);
		Integer productVariantId = productVariantService.saveOrUpdate(productVariant);
		Integer attributeId = getSavedAttributeId();
		variantService.add(productVariantId, attributeId);
		
		variantService.delete(attributeId, productVariantId);
	}
	
	private Integer getSavedAttributeId(){
		Attribute attribute = createAttribute();
		attributeService.add(attribute);
		return attributeService.getIdByNameAndValue(attribute.getName(), attribute.getValue());
	}
	
}
