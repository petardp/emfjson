package org.emfjson.jackson.junit.tests;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.*;
import org.emfjson.jackson.junit.model.*;
import org.emfjson.jackson.junit.support.*;
import org.junit.*;

import static org.junit.Assert.*;

public class ReaderTest extends TestSupport {

	@Test
	public void shouldReadObjectWhenEClassFieldIsNotFirst() throws JsonProcessingException {
		JsonNode data = mapper.createObjectNode()
				.put("eInt", 1)
				.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//ETypes")
				.set("eInts", mapper.createArrayNode()
						.add(1)
						.add(2));

		Resource resource = mapper.treeToValue(data, Resource.class);

		assertEquals(1, resource.getContents().size());

		ETypes result = (ETypes) resource.getContents().get(0);

		assertEquals(ModelPackage.Literals.ETYPES, result.eClass());
		assertEquals(1, result.getEInt());
		assertEquals(2, result.getEInts().size());

		assertEquals(1, result.getEInts().get(0).intValue());
		assertEquals(2, result.getEInts().get(1).intValue());
	}

	@Test
	public void shouldReadObjectTreeWithEClassFieldNotFirst() throws JsonProcessingException {
		JsonNode data = ((ObjectNode) mapper.createObjectNode()
				.put("name", "A")
				.set("eStructuralFeatures", mapper.createArrayNode()
						.add(mapper.createObjectNode()
								.put("name", "foo")
								.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EAttribute"))
						.add(mapper.createObjectNode()
								.put("name", "bar")
								.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EAttribute"))))
				.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EClass");

		Resource resource = mapper.treeToValue(data, Resource.class);

		assertEquals(1, resource.getContents().size());

		EObject result = resource.getContents().get(0);
		assertEquals(EcorePackage.Literals.ECLASS, result.eClass());
		assertEquals(2, ((EClass) result).getEStructuralFeatures().size());
		assertEquals(EcorePackage.Literals.EATTRIBUTE, ((EClass) result).getEStructuralFeatures().get(0).eClass());
		assertEquals(EcorePackage.Literals.EATTRIBUTE, ((EClass) result).getEStructuralFeatures().get(1).eClass());
	}

	@Test
	public void shouldReadObjectTreeWithEClassFieldRandomPosition() throws JsonProcessingException {
		JsonNode data = mapper.createObjectNode()
				.put("name", "A")
				.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EClass")
				.set("eStructuralFeatures", mapper.createArrayNode()
						.add(mapper.createObjectNode()
								.put("name", "foo")
								.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EAttribute"))
						.add(mapper.createObjectNode()
								.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EAttribute")
								.put("name", "bar")));

		Resource resource = mapper.treeToValue(data, Resource.class);

		assertEquals(1, resource.getContents().size());

		EObject result = resource.getContents().get(0);
		assertEquals(EcorePackage.Literals.ECLASS, result.eClass());
		assertEquals(2, ((EClass) result).getEStructuralFeatures().size());
		assertEquals(EcorePackage.Literals.EATTRIBUTE, ((EClass) result).getEStructuralFeatures().get(0).eClass());
		assertEquals(EcorePackage.Literals.EATTRIBUTE, ((EClass) result).getEStructuralFeatures().get(1).eClass());
	}

}
