package services;

import java.util.List;

import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;
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
	
	@Test(expected = DuplicateKeyException.class)
	public void addTest(){
		
		Attribute attribute = createAttribute();
		attributeService.save(attribute);
		Integer attributeId = attributeService.getIdByNameAndValue(attribute.getName(), attribute.getValue());
		
		Integer productId = productService.add(createProduct());
		ProductVariant productVariant = createProductVariant(productId);
		Integer productVariantId = productVariantService.save(productVariant);
		variantService.add(productVariantId, attributeId);
		variantService.add(productVariantId, attributeId);
	}
	
	@Test
	public void deleteTest(){
		
		Attribute attribute = createAttribute();
		attributeService.save(attribute);
		Integer attributeId = attributeService.getIdByNameAndValue(attribute.getName(), attribute.getValue());
		
		Integer productId = productService.add(createProduct());
		ProductVariant productVariant = createProductVariant(productId);
		Integer productVariantId = productVariantService.save(productVariant);
		variantService.add(productVariantId, attributeId);
		variantService.delete(productVariantId, attributeId);
		List<Attribute> productVariantAttributes = attributeService.getProductVariantAttributes(productVariantId);
		Assert.isTrue(productVariantAttributes.isEmpty(), "The list attributs must be empty");
	}
	
}
