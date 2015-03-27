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
import org.eclipse.emf.common.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.*;
import org.emfjson.jackson.junit.support.*;
import org.junit.*;

import java.io.*;

import static org.junit.Assert.*;

public class AnnotationTest extends TestSupport {

	final JsonNode data = mapper.createObjectNode()
			.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EClass")
			.put("name", "Foo")
			.set("eAnnotations", mapper.createArrayNode()
					.add(mapper.createObjectNode()
							.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EAnnotation")
							.put("source", "source")
							.set("details", mapper.createObjectNode()
									.put("displayName", "value"))));

	@Test
	public void testSaveAnnotation() throws IOException {
		EClass eClass = EcoreFactory.eINSTANCE.createEClass();
		eClass.setName("Foo");

		EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
		eAnnotation.setEModelElement(eClass);
		eAnnotation.setSource("source");
		eAnnotation.getDetails().put("displayName", "value");
		eClass.getEAnnotations().add(eAnnotation);

		Resource resource = resourceSet.createResource(URI.createURI("test.json"));
		resource.getContents().add(eClass);

		assertEquals(data, mapper.valueToTree(resource));
	}

	@Test
	public void testLoadAnnotation() throws IOException {
		Resource resource = mapper.treeToValue(data, Resource.class);

		assertEquals(1, resource.getContents().size());

		EClass root = (EClass) resource.getContents().get(0);

		EList<EAnnotation> annotations = root.getEAnnotations();

		assertEquals(1, annotations.size());

		EAnnotation annotation = annotations.get(0);
		assertEquals(1, annotation.getDetails().size());
		assertEquals("displayName", annotation.getDetails().get(0).getKey());
		assertEquals("value", annotation.getDetails().get(0).getValue());
	}

}