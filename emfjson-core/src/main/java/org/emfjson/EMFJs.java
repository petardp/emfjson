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
package org.emfjson;

/**
 * Constants used as options during load/save operations on a Resource.
 */
public final class EMFJs {

	private EMFJs() {}

	/**
	 * Sets the root element to be loaded from a JSON document without
	 * type field. 
	 * <p>Value must be an object of type EClass.</p>
	 * <p>Default value is null.</p>
	 */
	public static final String OPTION_ROOT_ELEMENT = "OPTION_ROOT_ELEMENT";

	/**
	 * When value is true, the writer will include a type to each JSON objects.
	 * <p>Default value is true.</p>
	 */
	public static final String OPTION_SERIALIZE_TYPE = "OPTION_SERIALIZE_TYPE";

	/**
	 * When value is true, the writer will include a type to each reference JSON
	 * objects.
	 * <p>Default value is false</p>
	 */
	public static final String OPTION_SERIALIZE_REF_TYPE = "OPTION_SERIALIZE_REF_TYPE";

	/**
	 * When value is true, the writer will indent the output JSON document.
	 * <p>Default value is true</p>
	 */
	public static final String OPTION_INDENT_OUTPUT = "OPTION_INDENT_OUTPUT";

	/**
	 * When value is true, the reader will load attributes of proxy objects.
	 * <p>Default value is false</p>
	 */
	public static final String OPTION_PROXY_ATTRIBUTES = "PROXY_ATTRIBUTES";

	/**
	 * When value is true, the reader will resolve proxy objects. 
	 * <p>Default value is true</p>
	 */
	public static final String OPTION_RESOLVE_PROXY = "OPTION_RESOLVE_PROXY";
	
	/**
	 * When value is true, the writer will include an _id key to each json objects and 
	 * sets as value the fragment identifier.
	 * <p>Default value is false</p>
	 */
	public static final String OPTION_USE_ID = "OPTION_USE_ID"; 

}
