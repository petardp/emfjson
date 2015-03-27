package org.emfjson.jackson.databind.ser;

import java.io.IOException;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.emfjson.common.Cache;
import org.emfjson.common.Constants;
import org.emfjson.common.EObjects;
import org.emfjson.common.ModelUtil;
import org.emfjson.jackson.databind.EObjectProperty;
import org.emfjson.jackson.databind.ser.list.EcoreEListSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

public class EObjectSerializer extends JsonSerializer<EObject> implements ContextualSerializer {

	private final Cache cache = new Cache();

	@Override
	public void serialize(EObject object, JsonGenerator generator, SerializerProvider provider) throws IOException {
		System.out.println("object "+object);
		final EClass eClass = object.eClass();

		generator.writeStartObject();
		generator.writeStringField(Constants.EJS_TYPE_KEYWORD, cache.getHref(null, eClass));

		for (final EAttribute attribute: cache.getAttributes(eClass)) {
			if (EObjects.isCandidate(object, attribute)) {
				generator.writeFieldName(ModelUtil.getElementName(attribute));
				Object value = object.eGet(attribute);
				generator.writeObject(value);
			}
		}

		for (final EReference reference: cache.getReferences(eClass)) {
			if (EObjects.isCandidate(object, reference)) {
				generator.writeFieldName(ModelUtil.getElementName(reference));

				Object value = object.eGet(reference);

				if (value != null) {
					JsonSerializer<Object> serializer = provider.findValueSerializer(
							value.getClass(),
							new EObjectProperty(object, reference));

					if (serializer != null) {
						serializer.serialize(object.eGet(reference), generator, provider);
					}
				}
			}
		}

		generator.writeEndObject();
	}

	@Override
	public Class<EObject> handledType() {
		return EObject.class;
	}

	@Override
	public JsonSerializer<?> createContextual(SerializerProvider provider, BeanProperty property) throws JsonMappingException {
		System.out.println("ctx " + property);
		if (property instanceof EObjectProperty) {
			EStructuralFeature reference = ((EObjectProperty) property).getFeature();

			if (reference.isMany()) {
				return new EcoreEListSerializer((EObjectProperty) property);
			} else {
				if (reference instanceof EReference) {
					if (!((EReference) reference).isContainment()) {
						return new EObjectReferenceSerializer((EObjectProperty) property);
					}
				}
			}
		}

		return this;
	}

}
