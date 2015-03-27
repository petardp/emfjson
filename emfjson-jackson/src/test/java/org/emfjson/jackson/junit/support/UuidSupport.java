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

import static org.eclipse.emf.ecore.util.EcoreUtil.getURI;
import static org.emfjson.common.Constants.EJS_ID_FIELD;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.emfjson.jackson.resource.JsonResource;

import com.fasterxml.jackson.databind.JsonNode;

public abstract class UuidSupport {

	private ResourceSet resourceSet = new ResourceSetImpl();

	protected String uuid(EObject object) {
		return getURI(object).fragment();
	}

	protected String uuid(JsonNode node) {
		return node.get(EJS_ID_FIELD).asText();
	}
	
	protected Resource createUuidResource(String name) {
		Resource resource = new JsonResource(URI.createURI(name)) {
			@Override
			protected boolean useUUIDs() {
				return true;
			}
		};
		resourceSet.getResources().add(resource);
		return resource;
	}

	protected Resource createJsUuidResource(String name) {
		Resource resource = new JsonResource(URI.createURI(name)) {
			@Override
			protected boolean useUUIDs() {
				return true;
			}
		};
		resourceSet.getResources().add(resource);
		return resource;
	}

}
