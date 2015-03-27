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
package org.emfjson.jackson.junit;

import org.emfjson.jackson.junit.tests.*;
import org.emfjson.jackson.junit.tests.deser.*;
import org.emfjson.jackson.junit.tests.ser.*;
import org.junit.runner.*;
import org.junit.runners.*;
import org.junit.runners.Suite.*;

@RunWith(Suite.class)
@SuiteClasses({
		DeSerValuesTest.class,
		DeSerRefTest.class,
		SerRefTest.class,
		SerValuesTest.class,
		AnnotationTest.class,
		DynamicTest.class,
		FeatureMapTest.class,
		ModuleTest.class,
		PolymorphicTest.class,
		ProxyAttributeTest.class,
		ReaderTest.class,
		UpdateObjectTest.class,
		EnumTest.class,
})
public class TestSuite {}
