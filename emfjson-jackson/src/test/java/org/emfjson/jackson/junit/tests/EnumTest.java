package org.emfjson.jackson.junit.tests;

import com.fasterxml.jackson.databind.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.*;
import org.emfjson.jackson.junit.model.*;
import org.emfjson.jackson.module.*;
import org.emfjson.jackson.resource.*;
import org.junit.*;

import java.io.*;

import static org.junit.Assert.*;

public class EnumTest {

	private final ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setUp() {
		EPackage.Registry.INSTANCE.put(ModelPackage.eNS_URI, ModelPackage.eINSTANCE);
		mapper.registerModule(new EMFModule());
	}

	@Test
	public void testEnums() throws IOException {
		JsonNode expected = mapper.createArrayNode()
				.add(mapper.createObjectNode()
						.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//User")
						.put("sex", "MALE"))
				.add(mapper.createObjectNode()
						.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//User")
						.put("sex", "FEMALE"));

		Resource resource = new JsonResource();

		User u1 = ModelFactory.eINSTANCE.createUser();
		u1.setSex(Sex.MALE);

		User u2 = ModelFactory.eINSTANCE.createUser();
		u2.setSex(Sex.FEMALE);

		resource.getContents().add(u1);
		resource.getContents().add(u2);

		JsonNode result = mapper.valueToTree(resource);

		assertEquals(expected, result);
	}

	@Test
	public void testLoadEnums() throws IOException {
		JsonNode data = mapper.createArrayNode()
				.add(mapper.createObjectNode()
						.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//User")
						.put("sex", "MALE"))
				.add(mapper.createObjectNode()
						.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//User")
						.put("sex", "FEMALE"));

		Resource resource = mapper.treeToValue(data, Resource.class);

		assertEquals(2, resource.getContents().size());

		assertEquals(ModelPackage.Literals.USER, resource.getContents().get(0).eClass());
		assertEquals(ModelPackage.Literals.USER, resource.getContents().get(1).eClass());

		User u1 = (User) resource.getContents().get(0);
		User u2 = (User) resource.getContents().get(1);

		assertEquals(Sex.MALE, u1.getSex());
		assertEquals(Sex.FEMALE, u2.getSex());
	}

	@Test
	public void testSaveEnumDifferentCases() throws IOException {
		JsonNode expected = mapper.createArrayNode()
				.add(mapper.createObjectNode()
						.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//PrimaryObject")
						.put("kind", "one"))
				.add(mapper.createObjectNode()
						.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//PrimaryObject")
						.put("kind", "two"))
				.add(mapper.createObjectNode()
						.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//PrimaryObject")
						.put("kind", "Three-is-Three"));

		Resource resource = new JsonResource();
		{
			PrimaryObject p = ModelFactory.eINSTANCE.createPrimaryObject();
			p.setKind(SomeKind.ONE);
			resource.getContents().add(p);
		}
		{
			PrimaryObject p = ModelFactory.eINSTANCE.createPrimaryObject();
			p.setKind(SomeKind.TWO);
			resource.getContents().add(p);
		}
		{
			PrimaryObject p = ModelFactory.eINSTANCE.createPrimaryObject();
			p.setKind(SomeKind.THREE);
			resource.getContents().add(p);
		}

		assertEquals(expected, mapper.valueToTree(resource));
	}

	@Test
	public void testLoadEnumDifferentCases() throws IOException {
		JsonNode data = mapper.createArrayNode()
				.add(mapper.createObjectNode()
						.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//PrimaryObject")
						.put("kind", "one"))
				.add(mapper.createObjectNode()
						.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//PrimaryObject")
						.put("kind", "two"))
				.add(mapper.createObjectNode()
						.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//PrimaryObject")
						.put("kind", "Three-is-Three"));

		Resource resource = mapper.treeToValue(data, Resource.class);

		assertEquals(3, resource.getContents().size());

		EObject one = resource.getContents().get(0);
		EObject two = resource.getContents().get(1);
		EObject three = resource.getContents().get(2);

		assertEquals(ModelPackage.Literals.PRIMARY_OBJECT, one.eClass());
		assertEquals(ModelPackage.Literals.PRIMARY_OBJECT, two.eClass());
		assertEquals(ModelPackage.Literals.PRIMARY_OBJECT, three.eClass());

		assertEquals(SomeKind.ONE, ((PrimaryObject) one).getKind());
		assertEquals(SomeKind.TWO, ((PrimaryObject) two).getKind());
		assertEquals(SomeKind.THREE, ((PrimaryObject) three).getKind());
	}

}
