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
package org.emfjson.jackson.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.Map;

import com.fasterxml.jackson.databind.cfg.*;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.emfjson.jackson.module.EMFModule;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.emfjson.EMFJs.OPTION_ROOT_ELEMENT;

/**
 * A Resource implementation that read and write it's content in JSON.
 */
public class JsonResource extends AbstractUuidResource {

	private final ObjectMapper mapper = new ObjectMapper();

	public JsonResource() {
		this(null);
	}

	public JsonResource(URI uri) {
		super(uri);
		mapper.registerModule(new EMFModule());
	}

	@Override
	protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException {
		if (options == null) {
			options = Collections.<Object, Object> emptyMap();
		}

		if (inputStream instanceof URIConverter.Loadable) {

			((URIConverter.Loadable) inputStream).loadResource(this);

		} else {

			ContextAttributes attributes = ContextAttributes
					.getEmpty()
					.withSharedAttribute("resource", this)
					.withSharedAttribute(OPTION_ROOT_ELEMENT, options.get(OPTION_ROOT_ELEMENT));

			mapper.reader()
					.with(attributes)
					.forType(Resource.class)
					.readValue(inputStream);

		}

	}

	@Override
	protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException {
		if (options == null) {
			options = Collections.<String, Object> emptyMap();
		}

		if (outputStream instanceof URIConverter.Saveable) {
			((URIConverter.Saveable) outputStream).saveResource(this);
		} else {
			outputStream.write(mapper.writeValueAsBytes(this));
		}
	}

}
