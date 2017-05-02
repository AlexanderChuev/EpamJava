package services;

import java.util.List;

import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.Assert;

import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.datamodel.ProductVariant;

public class AttributeServiceTest extends AbstractTesst {

	@Test
	public void test() {
		Assert.notNull(attributeService, "The attributeService must not be null");
	}

	@Test(expected = DuplicateKeyException.class)
	public void addTest() {

		Attribute attribute = createAttribute();
		attributeService.save(attribute);
		attributeService.save(attribute);
	}

	@Test
	public void getNamesTest() {

		Attribute attribute = createAttribute();
		attributeService.save(attribute);
		List<String> names = attributeService.getNames();
		
		Assert.notNull(names, "The list of attribute names must not be null");
		Assert.noNullElements(names.toArray(), "The list of attribute names must not contain null elements");
		Assert.notEmpty(names, "The list of attribute names must not contain empty elements");
	}

	@Test
	public void getValuesByNameTest() {

		Attribute attribute = createAttribute();
		attributeService.save(attribute);
		List<String> values = attributeService.getValuesByName(attribute.getName());
		
		Assert.notNull(values, "The list of attribute values must not be null");
		Assert.noNullElements(values.toArray(), "The list of attribute values must not contain null elements");
		Assert.notEmpty(values, "The list of attribute values must not contain empty elements");
	}

	@Test
	public void getIdByNameAndValueTest() {

		Attribute attribute = createAttribute();
		attributeService.save(attribute);
		Integer id = attributeService.getIdByNameAndValue(attribute.getName(), attribute.getValue());
		Assert.notNull(id, "Attribute id must not be null");
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteAttributeValueTest() {

		Attribute attribute = createAttribute();
		attributeService.save(attribute);
		Integer id = attributeService.getIdByNameAndValue(attribute.getName(), attribute.getValue());
		attributeService.deleteAttributeValue(id);
		attributeService.getIdByNameAndValue(attribute.getName(), attribute.getValue());
	}

	@Test
	public void deleteByNameTest() {

		Attribute attribute = createAttribute();
		attributeService.save(attribute);
		attribute.setValue("second");
		attributeService.save(attribute);
		attributeService.delete(attribute.getName());
		Assert.isTrue(attributeService.getValuesByName(attribute.getName()).isEmpty(),
				"The list of attribut values must be empty");
	}

	@Test
	public void getAllIdByNameTest() {
		
		Attribute attribute = createAttribute();
		attributeService.save(attribute);
		attribute.setValue("second");
		attributeService.save(attribute);
		List<Integer> ids = attributeService.getAllIdByName(attribute.getName());
		
		Assert.notNull(ids, "The list of attribute id must not be null");
	}

	@Test
	public void getProductVariantAttributesTest() {
		
		Integer productId = productService.add(createProduct());
		ProductVariant productVariant = createProductVariant(productId);
		productVariantService.save(productVariant);
		
		List<ProductVariant> productVariantById = productVariantService.getAllByProduct(productId);
		Integer productVariantId = productVariantById.get(0).getId();
		
		Attribute attribute1 = createAttribute();
		attributeService.save(attribute1);
		Integer attributeId1 = attributeService.getIdByNameAndValue(attribute1.getName(), attribute1.getValue());
		variantService.add(productVariantId, attributeId1);
		
		Attribute attribute2 = createAttribute();
		attributeService.save(attribute2);
		Integer attributeId2 = attributeService.getIdByNameAndValue(attribute2.getName(), attribute2.getValue());
		variantService.add(productVariantId, attributeId2);
		
		List<Attribute> attributes = attributeService.getProductVariantAttributes(productVariantId);
		
		Assert.notNull(attributes, "The list of attributes must not be null");
		Assert.noNullElements(attributes.toArray(), "The list of attributes must not contain null elements");
		Assert.notEmpty(attributes, "The list of attributes must not contain empty elements");
		
		Assert.isTrue(attributes.get(0).getName().equals(attribute1.getName()), "Attribute names must be equal");
		Assert.isTrue(attributes.get(1).getName().equals(attribute2.getName()), "Attribute names must be equal");
		Assert.isTrue(attributes.get(0).getValue().equals(attribute1.getValue()), "Attribute values must be equal");
		Assert.isTrue(attributes.get(1).getValue().equals(attribute2.getValue()), "Attribute values must be equal");
	}

}
