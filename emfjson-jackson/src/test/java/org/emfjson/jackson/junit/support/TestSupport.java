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
package org.emfjson.jackson.junit.support;

import com.fasterxml.jackson.databind.*;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.*;
import org.eclipse.emf.ecore.resource.impl.*;
import org.emfjson.jackson.junit.model.*;
import org.emfjson.jackson.module.*;
import org.emfjson.jackson.resource.*;
import org.junit.*;

import java.net.*;
import java.util.*;

public abstract class TestSupport {

	protected final URL testURI = getClass().getResource("/tests");
	protected URI baseTestFilesFileDirectory = URI.createFileURI(testURI.getFile()).appendSegment("");
	protected String baseURI = "http://eclipselabs.org/emfjson/tests/";

	protected final Map<String, Object> options = new HashMap<>();
	protected ResourceSet resourceSet;
	protected ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setUp() {
		URI baseURI = URI.createURI("http://eclipselabs.org/emfjson/tests/");

		EPackage.Registry.INSTANCE.put(ModelPackage.eNS_URI, ModelPackage.eINSTANCE);
		Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap().put("*", new JsonResourceFactory());

		options.clear();

		resourceSet = new ResourceSetImpl();		
		resourceSet.getURIConverter().getURIMap().put(baseURI, baseTestFilesFileDirectory);
		mapper.registerModule(new EMFModule());
	}

	protected URI uri(String fileName) {
		return URI.createURI(baseURI + fileName, true);
	}

}
