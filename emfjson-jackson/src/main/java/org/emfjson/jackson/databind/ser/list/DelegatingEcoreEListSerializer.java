package org.emfjson.jackson.databind.ser.list;

import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.DelegatingEcoreEList;
import org.emfjson.jackson.databind.EObjectProperty;
import org.emfjson.jackson.databind.ser.EObjectReferenceSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@SuppressWarnings("rawtypes")
public class DelegatingEcoreEListSerializer extends JsonSerializer<DelegatingEcoreEList> {

	@Override
	public void serialize(DelegatingEcoreEList values, JsonGenerator jg, SerializerProvider provider) throws IOException {
		jg.writeStartArray();
		for (Object value: values) {
			new EObjectReferenceSerializer(new EObjectProperty(
					values.getEObject(), 
					values.getEStructuralFeature()))
			.serialize((EObject) value, jg, provider);
		}
		jg.writeEndArray();
	}
	
	@Override
	public Class<DelegatingEcoreEList> handledType() {
		return DelegatingEcoreEList.class;
	}

}
