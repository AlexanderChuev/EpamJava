package services;

import java.util.List;

import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.Assert;

import com.chuyeu.training.myapp.datamodel.Attribute;

public class AttributeServiceTest extends AbstractTesst {

	@Test
	public void test() {
		Assert.notNull(attributeService, "The attributeService must not be null");
	}

	@Test(expected = DuplicateKeyException.class)
	public void addTest() {

		Attribute attribute = createAttribute();
		attributeService.add(attribute);
		attributeService.add(attribute);

	}

	@Test
	public void getNamesTest() {

		Attribute attribute = createAttribute();
		attributeService.add(attribute);
		List<String> names = attributeService.getNames();
		
		Assert.notNull(names, "The list of attribute names must not be null");
		Assert.noNullElements(names.toArray(), "The list of attribute names must not contain null elements");
		Assert.notEmpty(names, "The list of attribute names must not contain empty elements");
	}

	@Test
	public void getValuesByNameTest() {

		Attribute attribute = createAttribute();
		attributeService.add(attribute);
		List<String> values = attributeService.getValuesByName(attribute.getName());
		
		Assert.notNull(values, "The list of attribute values must not be null");
		Assert.noNullElements(values.toArray(), "The list of attribute values must not contain null elements");
		Assert.notEmpty(values, "The list of attribute values must not contain empty elements");
	}

	@Test
	public void getIdByNameAndValueTest() {

		Attribute attribute = createAttribute();
		attributeService.add(attribute);
		Integer id = attributeService.getIdByNameAndValue(attribute.getName(), attribute.getValue());
		Assert.notNull(id, "Attribute id must not be null");
	}

	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteAttributeValueTest() {

		Attribute attribute = createAttribute();
		attributeService.add(attribute);
		Integer id = attributeService.getIdByNameAndValue(attribute.getName(), attribute.getValue());
		attributeService.deleteAttributeValue(id);
		attributeService.getIdByNameAndValue(attribute.getName(), attribute.getValue());
	}

	@Test
	public void deleteByNameTest() {

		Attribute attribute = createAttribute();
		attributeService.add(attribute);
		attribute.setValue("second");
		attributeService.add(attribute);
		attributeService.delete(attribute.getName());
		Assert.isTrue(attributeService.getValuesByName(attribute.getName()).isEmpty(),
				"The list of attribut values must be empty");

	}

	@Test
	public void getAllIdByNameTest() {
		
		Attribute attribute = createAttribute();
		attributeService.add(attribute);
		attribute.setValue("second");
		attributeService.add(attribute);
		List<Integer> ids = attributeService.getAllIdByName(attribute.getName());
		
		Assert.notNull(ids, "The list of attribute id must not be null");
	}

/*	@Test
	public void getProductVariantAttributesTest() {
		
		
		ProductVariant productVariant = createProductVariant(productId);
		productVariantService.saveOrUpdate(productVariant);
		Attribute attribute = createAttribute();
		attributeService.add(attribute);
		List<Attribute> attributes = attributeService.getProductVariantAttributes(1);
		
		
	}*/

}
