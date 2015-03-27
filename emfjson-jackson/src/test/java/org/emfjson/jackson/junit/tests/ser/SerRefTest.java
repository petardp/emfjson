package org.emfjson.jackson.junit.tests.ser;

import static org.junit.Assert.*;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.emfjson.jackson.junit.model.ModelFactory;
import org.emfjson.jackson.junit.model.Node;
import org.emfjson.jackson.module.EMFModule;
import org.emfjson.jackson.resource.JsonResource;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerRefTest {

	private final ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setUp() {
		mapper.registerModule(new EMFModule());
	}

	@Test
	public void testSingleReferenceInSameResource() {
		Node n1 = ModelFactory.eINSTANCE.createNode();
		Node n2 = ModelFactory.eINSTANCE.createNode();

		n1.setTarget(n2);

		Resource resource = new JsonResource();
		resource.getContents().add(n1);
		resource.getContents().add(n2);

		JsonNode result = mapper.valueToTree(resource);

		assertTrue(result.isArray());
		assertEquals(2, result.size());
	}

	@Test
	public void testSingleReferenceInDifferentResource() {
		Node n1 = ModelFactory.eINSTANCE.createNode();
		Node n2 = ModelFactory.eINSTANCE.createNode();

		n1.setTarget(n2);

		Resource r1 = new JsonResource(URI.createURI("first"));
		Resource r2 = new JsonResource(URI.createURI("second"));

		r1.getContents().add(n1);
		r2.getContents().add(n2);
		
		JsonNode result = mapper.valueToTree(r1);

		assertTrue(result.isObject());
		assertEquals("http://www.eclipselabs.org/emfjson/junit#//Node", result.get("eClass").asText());
		assertTrue(result.get("target").isObject());
		assertEquals("second#/", result.get("target").get("$ref").asText());
	}

	@Test
	public void testSingleReferenceInDifferentResourceXmi() throws IOException {
		Node n1 = ModelFactory.eINSTANCE.createNode();
		Node n2 = ModelFactory.eINSTANCE.createNode();

		n1.setTarget(n2);

		Resource r1 = new XMIResourceImpl(URI.createURI("first"));
		Resource r2 = new XMIResourceImpl(URI.createURI("second"));

		r1.getContents().add(n1);
		r2.getContents().add(n2);

		JsonNode result = mapper.valueToTree(r1);

		assertTrue(result.isObject());
		assertEquals("http://www.eclipselabs.org/emfjson/junit#//Node", result.get("eClass").asText());
		assertTrue(result.get("target").isObject());
		assertEquals("second#/", result.get("target").get("$ref").asText());
	}

}
