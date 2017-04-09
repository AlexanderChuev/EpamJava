package services;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.util.Assert;

import com.chuyeu.training.myapp.datamodel.Attribute;
import com.chuyeu.training.myapp.services.IAttributeService;

public class AttributeServiceTest extends AbstractTest {

	@Inject
	private IAttributeService attributeService;

	/*-------------------------------------------------------------------------------------------*/

	@Test
	public void test() {
		Assert.notNull(attributeService);
	}

	@Test(expected = DuplicateKeyException.class)
	public void addTest() {

		Attribute attribute = createAttribute();
		attributeService.add(attribute);
		attributeService.add(attribute);
	}

	@Test(expected = DuplicateKeyException.class)
	public void addAttributeTest() {
		String name = "Name" + new Date().getTime();
		attributeService.add(name);
		attributeService.add(name);
	}

	/*-------------------------------------------------------------------------------------------*/

	@Test
	public void getNamesTest() {

		List<String> names = attributeService.getNames();
		Assert.notNull(names);
		Assert.noNullElements(names.toArray());
	}

	@Test
	public void getValuesByNameTest() {
		// TODO
	}

	@Test
	public void getIdByNameAndValueTest() {

		Attribute attribute = createAttribute();

		attributeService.add(attribute);
		Integer id = attributeService.getIdByNameAndValue(attribute.getName(), attribute.getValue());
		Assert.notNull(id, "The id must not be null");

	}

	/*-------------------------------------------------------------------------------------------*/

	@Test(expected = EmptyResultDataAccessException.class)
	public void deleteAttributeValue() {

		Attribute attribute = createAttribute();

		attributeService.add(attribute);

		Integer id = attributeService.getIdByNameAndValue(attribute.getName(), attribute.getValue());

		attributeService.deleteAttributeValue(id);

		attributeService.getIdByNameAndValue(attribute.getName(), attribute.getValue());
	}

	@Test
	public void delete() {

		Attribute attribute = createAttribute();

		attributeService.add(attribute);
		attributeService.delete(attribute.getName());

		Assert.isTrue(attributeService.getValuesByName(attribute.getName()).isEmpty());

	}

	/*-------------------------------------------------------------------------------------------*/

	private Attribute createAttribute() {

		Attribute attribute = new Attribute();

		String name = "Name" + new Date().getTime();
		String value = "Value" + new Date().getTime();

		attribute.setName(name);
		attribute.setValue(value);

		return attribute;
	}

}
