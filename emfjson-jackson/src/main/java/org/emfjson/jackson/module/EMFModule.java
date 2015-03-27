package org.emfjson.jackson.module;

import com.fasterxml.jackson.core.*;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.*;
import org.emfjson.jackson.databind.deser.EObjectDeserializer;
import org.emfjson.jackson.databind.deser.EnumeratorDeserializer;
import org.emfjson.jackson.databind.deser.ResourceDeserializer;
import org.emfjson.jackson.databind.deser.list.EObjectContainmentEListDeserializer;
import org.emfjson.jackson.databind.ser.DateSerializer;
import org.emfjson.jackson.databind.ser.EObjectSerializer;
import org.emfjson.jackson.databind.ser.EnumeratorSerializer;
import org.emfjson.jackson.databind.ser.ResourceSerializer;
import org.emfjson.jackson.databind.ser.list.DelegatingEcoreEListSerializer;
import org.emfjson.jackson.databind.ser.list.EObjectContainmentEListSerializer;
import org.emfjson.jackson.databind.ser.list.EcoreEListSerializer;
import org.emfjson.jackson.databind.ser.list.NotifyingInternalEListImplSerializer;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class EMFModule extends SimpleModule {

	private static final long serialVersionUID = 1L;

	public EMFModule() {
		addSerializer(new EObjectSerializer());
		addSerializer(new ResourceSerializer());
		addSerializer(new EcoreEListSerializer());
		addSerializer(new NotifyingInternalEListImplSerializer());
		addSerializer(new EObjectContainmentEListSerializer());
		addSerializer(new DelegatingEcoreEListSerializer());
		addSerializer(new EnumeratorSerializer());
		addSerializer(new DateSerializer());
		addDeserializer(Resource.class, new ResourceDeserializer());
		addDeserializer(EObject.class, new EObjectDeserializer());
		addDeserializer(Enumerator.class, new EnumeratorDeserializer());
		addDeserializer(EObjectContainmentEList.class, new EObjectContainmentEListDeserializer());
	}

	@Override
	public Version version() {
		return new Version(0, 11, 0, "SNAPSHOT", "org.emfjson", "emfjson-jackson");
	}

}
