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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.emfjson.jackson.module.EMFModule;
import org.emfjson.jackson.resource.JsonResource;
import org.junit.Test;

import static org.junit.Assert.*;

public class ModuleTest {

	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testSaveWithModule() throws JsonProcessingException {
		JsonNode expected = mapper.createObjectNode()
				.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EClass")
				.put("name", "A");

		mapper.registerModule(new EMFModule());
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

		EClass c = EcoreFactory.eINSTANCE.createEClass();
		c.setName("A");

		assertEquals(expected, mapper.valueToTree(c));
	}

	@Test
	public void testReadWithModule() throws IOException {
		JsonNode data = mapper.createObjectNode()
				.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EClass")
				.put("name", "A");

		mapper.registerModule(new EMFModule());
		EClass result = (EClass) mapper.treeToValue(data, EObject.class);

		assertEquals("A", result.getName());
	}

	@Test
	public void testSaveResourceWithModule() throws JsonProcessingException {
		JsonNode expected = mapper.createObjectNode()
				.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EClass")
				.put("name", "A");

		mapper.registerModule(new EMFModule());
		mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

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

		mapper.registerModule(new EMFModule());
		Resource result = mapper.treeToValue(data, Resource.class);

		assertEquals(1, result.getContents().size());
		assertEquals(EcorePackage.Literals.ECLASS, result.getContents().get(0).eClass());
		assertEquals("A", ((EClass) result.getContents().get(0)).getName());
	}

}
