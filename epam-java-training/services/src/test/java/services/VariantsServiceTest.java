package services;

import java.util.List;

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
		productVariantService.save(productVariant);
		List<ProductVariant> productVariants = productVariantService.getAllByProduct(productId);
		variantService.add(productVariants.get(0).getId(), getSavedAttributeId());
	}
	
	//+++
	@Test
	public void deleteTest(){
		
		Integer productId = productService.add(createProduct());
		ProductVariant productVariant = createProductVariant(productId);
		productVariantService.save(productVariant);
		List<ProductVariant> productVariants = productVariantService.getAllByProduct(productId);
		Integer productVariantId = productVariants.get(0).getId();
		Integer attributeId = getSavedAttributeId();
		variantService.add(productVariantId, attributeId);
		variantService.delete(attributeId, productVariantId);
	}
	
	private Integer getSavedAttributeId(){
		Attribute attribute = createAttribute();
		attributeService.save(attribute);
		return attributeService.getIdByNameAndValue(attribute.getName(), attribute.getValue());
	}
	
}
