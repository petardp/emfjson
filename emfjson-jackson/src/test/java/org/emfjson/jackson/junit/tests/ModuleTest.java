/*
 * Copyright (c) 2011-2014 Guillaume Hillairet.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Guillaume Hillairet - initial API and implementation
 */
package org.emfjson.jackson.junit.tests;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.eclipse.emf.common.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.*;
import org.emfjson.jackson.junit.model.*;
import org.emfjson.jackson.junit.support.*;
import org.emfjson.jackson.resource.*;
import org.junit.*;

import java.io.*;

import static org.junit.Assert.*;

public class ModuleTest extends TestSupport {

	@Test
	public void testSaveWithModule() throws JsonProcessingException {
		JsonNode expected = mapper.createObjectNode()
				.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EClass")
				.put("name", "A");

		EClass c = EcoreFactory.eINSTANCE.createEClass();
		c.setName("A");

		assertEquals(expected, mapper.valueToTree(c));
	}

	@Test
	public void testReadWithModule() throws IOException {
		JsonNode data = mapper.createObjectNode()
				.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EClass")
				.put("name", "A");

		EClass result = (EClass) mapper.treeToValue(data, EObject.class);

		assertEquals("A", result.getName());
	}

	@Test
	public void testSaveResourceWithModule() throws JsonProcessingException {
		JsonNode expected = mapper.createObjectNode()
				.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EClass")
				.put("name", "A");

		Resource r = new JsonResource();
		EClass c = EcoreFactory.eINSTANCE.createEClass();
		c.setName("A");
		r.getContents().add(c);

		assertEquals(expected, mapper.valueToTree(r));
	}

	@Test
	public void testReadResourceWithModule() throws IOException {
		JsonNode data = mapper.createObjectNode()
				.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EClass")
				.put("name", "A");

		Resource result = mapper.treeToValue(data, Resource.class);

		assertEquals(1, result.getContents().size());
		assertEquals(EcorePackage.Literals.ECLASS, result.getContents().get(0).eClass());
		assertEquals("A", ((EClass) result.getContents().get(0)).getName());
	}

	@Test
	public void testWriteWithModule2() {
		Resource r = new JsonResource();
		Node n1 = ModelFactory.eINSTANCE.createNode();
		n1.setLabel("1");
		Node n2 = ModelFactory.eINSTANCE.createNode();
		n2.setLabel("2");
		Node n3 = ModelFactory.eINSTANCE.createNode();
		n3.setLabel("3");
		Node n4 = ModelFactory.eINSTANCE.createNode();
		n4.setLabel("4");
		n1.getChild().add(n2);
		n1.getChild().add(n3);
		n1.getChild().add(n4);

		n2.getManyRef().add(n3);
		n2.getManyRef().add(n4);

		n4.getManyRef().add(n2);
		
		r.getContents().add(n1);

		// TODO
	}
	
	@Test
	public void testWriteIdAttribute() {
		Resource r1 = resourceSet.createResource(URI.createURI("u1"));
		User u1 = ModelFactory.eINSTANCE.createUser();
		u1.setUserId("1");
		r1.getContents().add(u1);

		Resource r2 = resourceSet.createResource(URI.createURI("u2"));
		User u2 = ModelFactory.eINSTANCE.createUser();
		u2.setUserId("2");
		r2.getContents().add(u2);
		u1.getFriends().add(u2);

		// TODO
	}

	@Test
	public void testReadWithIdAttribute() throws IOException {
		JsonNode data = mapper.createObjectNode()
				.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//Node")
				.put("label", "1")
				.set("child", mapper.createArrayNode()
						.add(mapper.createObjectNode()
								.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//Node")
								.put("label", "2")));

		Node result = mapper
		.reader()
		.withAttribute("root", ModelPackage.Literals.NODE)
		.withAttribute("resourceSet", resourceSet)
		.withType(EObject.class)
		.readValue(data);

		assertNotNull(result);
		assertEquals("1", result.getLabel());
		assertEquals(1, result.getChild().size());
		assertEquals("2", result.getChild().get(0).getLabel());
	}

}
