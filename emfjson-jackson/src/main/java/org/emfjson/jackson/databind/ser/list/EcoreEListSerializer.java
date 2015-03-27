package org.emfjson.jackson.databind.ser.list;

import java.io.IOException;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.emfjson.jackson.databind.EObjectProperty;
import org.emfjson.jackson.databind.ser.EObjectReferenceSerializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

@SuppressWarnings("rawtypes")
public class EcoreEListSerializer extends JsonSerializer<EcoreEList> implements ContextualSerializer {

	private EStructuralFeature feature;

	public EcoreEListSerializer() {}

	public EcoreEListSerializer(EObjectProperty property) {
		this.feature = property.getFeature();
	}

	@Override
	public void serialize(EcoreEList values, JsonGenerator jgen, SerializerProvider provider) throws IOException {

		boolean isContainment = feature instanceof EReference && ((EReference) feature).isContainment();

		jgen.writeStartArray();
		for (Object value: values) {
			if (value instanceof EObject) {

				EObject object = (EObject) value;
				System.out.println("here " + object);

				if (object.eContainer() instanceof Resource || isContainment) {
					jgen.writeObject(value);
				} else {
					new EObjectReferenceSerializer(
							new EObjectProperty(object, feature))
					.serialize(object, jgen, provider);
				}
			} else {
				jgen.writeObject(value);
			}
		}
		jgen.writeEndArray();
	}

	@Override
	public Class<EcoreEList> handledType() {
		return EcoreEList.class;
	}

	@Override
	public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {

		if (property instanceof EObjectProperty) {
			return new EcoreEListSerializer((EObjectProperty) property);
		}

		return this;
	}

}