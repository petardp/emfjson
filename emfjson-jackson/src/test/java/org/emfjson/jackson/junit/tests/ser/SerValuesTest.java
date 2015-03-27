package org.emfjson.jackson.junit.tests.ser;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emfjson.jackson.junit.model.ETypes;
import org.emfjson.jackson.junit.model.ModelFactory;
import org.emfjson.jackson.module.EMFModule;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Test serializations of primitive types.
 */
public class SerValuesTest {

	private final ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setUp() {
		mapper.registerModule(new EMFModule());
	}

	@Test
	public void testStringValue() {
		JsonNode expected = mapper.createObjectNode()
				.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//ETypes")
				.put("eString", "Hello");

		ETypes element = ModelFactory.eINSTANCE.createETypes();
		element.setEString("Hello");

		assertEquals(expected, mapper.valueToTree(element));
	}

	@Test
	public void testStringValues() {
		JsonNode expected = mapper.createObjectNode()
				.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//ETypes")
				.set("eStrings", mapper.createArrayNode()
						.add("Hello")
						.add("World"));

		ETypes element = ModelFactory.eINSTANCE.createETypes();
		element.getEStrings().add("Hello");
		element.getEStrings().add("World");

		assertEquals(expected, mapper.valueToTree(element));
	}

	@Test
	public void testIntValue() {
		JsonNode expected = mapper.createObjectNode()
				.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//ETypes")
				.put("eInt", 12);

		ETypes element = ModelFactory.eINSTANCE.createETypes();
		element.setEInt(12);

		assertEquals(expected, mapper.valueToTree(element));
	}

	@Test
	public void testIntValues() {
		JsonNode expected = mapper.createObjectNode()
				.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//ETypes")
				.set("eInts", mapper.createArrayNode()
						.add(3)
						.add(6)
						.add(1020));

		ETypes element = ModelFactory.eINSTANCE.createETypes();
		element.getEInts().add(3);
		element.getEInts().add(6);
		element.getEInts().add(1020);

		assertEquals(expected, mapper.valueToTree(element));
	}

	@Test
	public void testBooleanValue() {
		JsonNode expected = mapper.createObjectNode()
				.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//ETypes")
				.put("eBoolean", true);

		ETypes element = ModelFactory.eINSTANCE.createETypes();
		element.setEBoolean(true);

		assertEquals(expected, mapper.valueToTree(element));
	}

	@Test
	public void testBooleanValues() {
		JsonNode expected = mapper.createObjectNode()
				.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//ETypes")
				.set("eBooleans", mapper.createArrayNode()
						.add(true)
						.add(false)
						.add(true));

		ETypes element = ModelFactory.eINSTANCE.createETypes();
		element.getEBooleans().add(true);
		element.getEBooleans().add(false);
		element.getEBooleans().add(true);

		assertEquals(expected, mapper.valueToTree(element));
	}

	@Test
	public void testDoubleValue() {
		JsonNode expected = mapper.createObjectNode()
				.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//ETypes")
				.put("eDouble", 1.2);

		ETypes element = ModelFactory.eINSTANCE.createETypes();
		element.setEDouble(1.2);

		assertEquals(expected, mapper.valueToTree(element));
	}

	@Test
	public void testDoublesValues() {
		JsonNode expected = mapper.createObjectNode()
				.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//ETypes")
				.set("eDoubles", mapper.createArrayNode()
						.add(3.2)
						.add(6.7)
						.add(1020.12));

		ETypes element = ModelFactory.eINSTANCE.createETypes();
		element.getEDoubles().add(3.2);
		element.getEDoubles().add(6.7);
		element.getEDoubles().add(1020.12);

		assertEquals(expected, mapper.valueToTree(element));
	}

	@Test
	public void testDateValue() {
		JsonNode expected = mapper.createObjectNode()
				.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//ETypes")
				.put("eDate", "2011-10-10T00:00:00");

		ETypes element = ModelFactory.eINSTANCE.createETypes();
		element.setEDate(
				(Date) EcoreUtil.createFromString(EcorePackage.eINSTANCE.getEDate(), 
						"2011-10-10"));

		assertEquals(expected, mapper.valueToTree(element));
	}

}
