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

import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.util.*;

import com.fasterxml.jackson.databind.node.*;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emfjson.EMFJs;
import org.emfjson.common.Constants;
import org.emfjson.jackson.junit.model.*;
import org.emfjson.jackson.junit.support.UuidSupport;
import org.emfjson.jackson.module.EMFModule;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UuidSaveTest extends UuidSupport {
	
	private final ObjectMapper mapper = new ObjectMapper();
	private Map<String, Object> options = new HashMap<>();

	@Before
	public void setUp() {
		EPackage.Registry.INSTANCE.put(ModelPackage.eNS_URI, ModelPackage.eINSTANCE);
		options.put(EMFJs.OPTION_INDENT_OUTPUT, true);
		mapper.registerModule(new EMFModule(options));
	}
	
	@Test
	public void testSerializeOneObjectWithUuid() {
		Resource resource = createUuidResource("test.xmi");
		Container root = ModelFactory.eINSTANCE.createContainer();
		resource.getContents().add(root);

		// Make sure the fragment identifier is a UUID 
		assertTrue(EcoreUtil.getURI(root).fragment().startsWith("_"));

		JsonNode node = mapper.valueToTree(root);

		assertNotNull(node);
		assertNotNull(node.get(Constants.EJS_UUID_ANNOTATION));
		assertEquals(uuid(root), uuid(node));
	}

	@Test
	public void testSerializeOneRootWithTwoChildHavingOneReference() throws IOException {
		Resource resource = createUuidResource("test.xmi");

		Container root = ModelFactory.eINSTANCE.createContainer();
		ConcreteTypeOne one = ModelFactory.eINSTANCE.createConcreteTypeOne();
		ConcreteTypeOne two = ModelFactory.eINSTANCE.createConcreteTypeOne();

		one.setName("one");
		two.setName("two");

		one.getRefProperty().add(two);

		root.getElements().add(one);
		root.getElements().add(two);

		resource.getContents().add(root);

		JsonNode node = mapper.valueToTree(root);

		assertNotNull(node);
		assertNotNull(node.get(Constants.EJS_UUID_ANNOTATION));
		assertEquals(uuid(root), uuid(node));

		assertTrue(node.get("elements").isArray());

		ArrayNode elements = (ArrayNode) node.get("elements");
		assertEquals(2, elements.size());

		JsonNode node1 = elements.get(0);
		JsonNode node2 = elements.get(1);

		assertNotNull(node1.get(Constants.EJS_UUID_ANNOTATION));
		assertEquals(uuid(one), uuid(node1));

		assertNotNull(node2.get(Constants.EJS_UUID_ANNOTATION));
		assertEquals(uuid(two), uuid(node2));

		assertNotNull(node1.get("refProperty"));
		assertNull(node2.get("refProperty"));
		assertTrue(node1.get("refProperty").isArray());

		ArrayNode refProperty = (ArrayNode) node1.get("refProperty");
		assertEquals(1, refProperty.size());

		JsonNode ref = refProperty.get(0);
		assertNotNull(ref.get(Constants.EJS_REF_KEYWORD));

		assertEquals(uuid(two), ref.get(Constants.EJS_REF_KEYWORD).asText());
	}

}