package org.emfjson.jackson.junit.tests;

import com.fasterxml.jackson.databind.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.*;
import org.emfjson.*;
import org.emfjson.jackson.junit.model.*;
import org.emfjson.jackson.junit.support.*;
import org.emfjson.jackson.module.*;
import org.emfjson.jackson.resource.*;
import org.junit.*;

import java.io.*;

import static org.junit.Assert.*;

public class PolymorphicTest extends TestSupport {

	@Before
	public void setUp() {
		super.setUp();
		mapper.registerModule(new EMFModule());
	}

	@Test
	public void testSaveTwoObjectsWithTypeInformation() {
		JsonNode expected = mapper.createObjectNode()
				.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//Container")
				.set("elements", mapper.createArrayNode()
						.add(mapper.createObjectNode()
								.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//ConcreteTypeOne")
								.put("name", "First"))
						.add(mapper.createObjectNode()
								.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//ConcreteTypeTwo")
								.put("name", "Two")));

		Resource resource = new JsonResource();

		Container c = ModelFactory.eINSTANCE.createContainer();
		ConcreteTypeOne one = ModelFactory.eINSTANCE.createConcreteTypeOne();
		one.setName("First");
		ConcreteTypeTwo two = ModelFactory.eINSTANCE.createConcreteTypeTwo();
		two.setName("Two");
		c.getElements().add(one);
		c.getElements().add(two);
		resource.getContents().add(c);

		assertEquals(expected, mapper.valueToTree(resource));
	}

	@Test
	public void testLoadTwoObjectsWithTypeInformation() throws IOException {
		options.put(EMFJs.OPTION_ROOT_ELEMENT, ModelPackage.eINSTANCE.getContainer());

		Resource resource = resourceSet.createResource(uri("test-load-types.json"));
		resource.load(options);

		assertEquals(1, resource.getContents().size());

		EObject root = resource.getContents().get(0);
		assertTrue(root instanceof Container);

		Container container = (Container) root;

		assertEquals(2, container.getElements().size());

		AbstractType first = container.getElements().get(0);
		AbstractType second = container.getElements().get(1);

		assertTrue(first instanceof ConcreteTypeOne);
		assertTrue(second instanceof ConcreteTypeTwo);

		assertEquals("First", first.getName());
		assertEquals("one", ((ConcreteTypeOne) first).getPropTypeOne());
		assertEquals("Two", second.getName());
		assertEquals("two", ((ConcreteTypeTwo) second).getPropTypeTwo());
	}

	@Test
	public void testLoadTwoObjectsWithReferenceAndTypeInformation() throws IOException {
		options.put(EMFJs.OPTION_ROOT_ELEMENT, ModelPackage.eINSTANCE.getContainer());

		Resource resource = resourceSet.createResource(uri("test-load-types-2.json"));

		resource.load(options);

		assertEquals(1, resource.getContents().size());

		EObject root = resource.getContents().get(0);
		assertTrue(root instanceof Container);

		Container container = (Container) root;

		assertEquals(2, container.getElements().size());

		AbstractType first = container.getElements().get(0);
		AbstractType second = container.getElements().get(1);

		assertTrue(first instanceof ConcreteTypeOne);
		assertTrue(second instanceof ConcreteTypeTwo);
		assertEquals(1, first.getRefProperty().size());

		AbstractType ref = first.getRefProperty().get(0);

		assertSame(second, ref);
	}

}
