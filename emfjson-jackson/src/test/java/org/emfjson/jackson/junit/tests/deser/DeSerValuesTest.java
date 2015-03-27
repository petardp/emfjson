package org.emfjson.jackson.junit.tests.deser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emfjson.jackson.junit.model.ETypes;
import org.emfjson.jackson.junit.model.ModelPackage;
import org.emfjson.jackson.module.EMFModule;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeSerValuesTest {

	private final ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setUp() {
		ResourceSet resourceSet = new ResourceSetImpl();
		resourceSet.getPackageRegistry().put(ModelPackage.eNS_URI, ModelPackage.eINSTANCE);

		mapper.registerModule(new EMFModule());
		mapper.reader().withAttribute("resourceSet", resourceSet);
	}

	@Test
	public void testStringValue() throws JsonProcessingException {
		JsonNode data = mapper.createObjectNode()
				.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//ETypes")
				.put("eString", "Hello");

		ETypes element = (ETypes) mapper.treeToValue(data, EObject.class);

		assertNotNull(element);
		assertEquals("Hello", element.getEString());
	}

	@Test(expected = JsonMappingException.class)
	public void testWrongStringValue() throws JsonProcessingException {
		JsonNode data = mapper.createObjectNode()
				.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//ETypes")
				.set("eString", mapper.createObjectNode());

		mapper.treeToValue(data, EObject.class);
	}

	@Test
	public void testStringValues() throws JsonProcessingException {
		JsonNode data = mapper.createObjectNode()
				.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//ETypes")
				.set("eStrings", mapper.createArrayNode()
						.add("Hello")
						.add("World"));

		ETypes element = (ETypes) mapper.treeToValue(data, EObject.class);

		assertNotNull(element);
		assertEquals(Arrays.asList("Hello", "World"), element.getEStrings());
	}

	@Test
	public void testIntValue() throws JsonProcessingException {
		JsonNode data = mapper.createObjectNode()
				.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//ETypes")
				.put("eInt", 10);

		ETypes element = (ETypes) mapper.treeToValue(data, EObject.class);

		assertNotNull(element);
		assertEquals(10, element.getEInt());
	}

	@Test
	public void testIntValues() throws JsonProcessingException {
		JsonNode data = mapper.createObjectNode()
				.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//ETypes")
				.set("eInts", mapper.createArrayNode()
						.add(2)
						.add(3)
						.add(102));

		ETypes element = (ETypes) mapper.treeToValue(data, EObject.class);

		assertNotNull(element);
		assertEquals(Arrays.asList(2, 3, 102), element.getEInts());
	}

}
