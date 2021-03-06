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

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.emfjson.EMFJs;
import org.emfjson.jackson.junit.support.TestSupport;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;

public class AnnotationTest extends TestSupport {

	@Test
	public void testSaveAnnotation() throws IOException {
		JsonNode expected = mapper.createObjectNode()
				.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EClass")
				.put("name", "Foo")
				.set("eAnnotations", mapper.createArrayNode()
						.add(mapper.createObjectNode()
								.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EAnnotation")
								.put("source", "source")
								.set("details", mapper.createObjectNode()
										.put("displayName", "value"))));

		EClass eClass = EcoreFactory.eINSTANCE.createEClass();
		eClass.setName("Foo");

		EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
		eAnnotation.setEModelElement(eClass);
		eAnnotation.setSource("source");
		eAnnotation.getDetails().put("displayName", "value");
		eClass.getEAnnotations().add(eAnnotation);

		Resource resource = resourceSet.createResource(URI.createURI("test.json"));
		resource.getContents().add(eClass);

		JsonNode result = mapper.valueToTree(resource);

		assertEquals(expected, result);
	}

	@Test
	public void testLoadAnnotation() throws IOException {
		JsonNode data = mapper.createObjectNode()
				.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EClass")
				.put("name", "Foo")
				.set("eAnnotations", mapper.createArrayNode()
						.add(mapper.createObjectNode()
								.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EAnnotation")
								.put("source", "source")
								.set("details", mapper.createObjectNode()
										.put("displayName", "value"))));

		Resource resource = resourceSet.createResource(URI.createURI("test.json"));
		resource.load(new ByteArrayInputStream(mapper.writeValueAsBytes(data)), null);

		assertEquals(1, resource.getContents().size());

		EObject root = resource.getContents().get(0);

		assertEquals(EcorePackage.Literals.ECLASS, root.eClass());

		EList<EAnnotation> annotations = ((EClass) root).getEAnnotations();

		assertEquals(1, annotations.size());

		EAnnotation annotation = annotations.get(0);
		assertEquals(1, annotation.getDetails().size());
		assertEquals("displayName", annotation.getDetails().get(0).getKey());
		assertEquals("value", annotation.getDetails().get(0).getValue());
	}

}