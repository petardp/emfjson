package org.emfjson.jackson.streaming;

import java.io.IOException;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.emfjson.common.Cache;
import org.emfjson.common.EObjects;
import org.emfjson.common.ModelUtil;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.ResolvableSerializer;

public class EObjectSerializer extends JsonSerializer<EObject> implements ResolvableSerializer, ContextualSerializer {

	private final Cache cache = new Cache();

	@Override
	public void serialize(EObject object, JsonGenerator generator, SerializerProvider provider) throws IOException {
		EClass eClass = object.eClass();

		generator.writeStartObject();

		for (EAttribute attribute: cache.getAttributes(eClass)) {
			if (EObjects.isCandidate(object, attribute)) {
				Object value = object.eGet(attribute);
				generator.writeFieldName(ModelUtil.getElementName(attribute));
				generator.writeObject(value);
			}
		}

		for (EReference reference: cache.getReferences(eClass)) {
//			if (EObjects.isCandidate(object, reference) && reference.isContainment()) {
				Object value = object.eGet(reference);
				generator.writeFieldName(ModelUtil.getElementName(reference));
				generator.writeObject(value);

//				if (reference.isMany()) {
//					Collection<?> values = (Collection<?>) value;
//					generator.writeStartArray();
//					for (Object current: values) {
//						generator.writeObject(current);
//					}
//					generator.writeEndArray();
//				} else {
//					generator.writeObject(value);
//				}
//			}
		}

		generator.writeEndObject();
	}

	@Override
	public Class<EObject> handledType() {
		return EObject.class;
	}

	@Override
	public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
		return this;
	}

	@Override
	public void resolve(SerializerProvider provider) throws JsonMappingException {
	}

}
