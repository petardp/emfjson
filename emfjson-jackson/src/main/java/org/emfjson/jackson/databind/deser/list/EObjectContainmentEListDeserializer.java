package org.emfjson.jackson.databind.deser.list;

import java.io.IOException;
import java.util.*;

import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.*;
import org.eclipse.emf.ecore.util.*;
import org.emfjson.common.*;
import org.emfjson.jackson.databind.EObjectProperty;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import org.emfjson.jackson.databind.deser.*;

@SuppressWarnings("rawtypes")
public class EObjectContainmentEListDeserializer extends JsonDeserializer<EObjectContainmentEList> implements ContextualDeserializer {

	private final EObject owner;
	private final EStructuralFeature feature;

	public EObjectContainmentEListDeserializer(EObjectProperty property) {
		owner = property.getOwner();
		feature = property.getFeature();
		System.out.println(owner + " " + feature.getName());
	}

	public EObjectContainmentEListDeserializer() {
		owner = null;
		feature = null;
	}

	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
		Set<ReferenceEntry> entries = (Set<ReferenceEntry>) ctxt.getAttribute("entries");

		System.out.println("ici " +entries);
		if (property instanceof EObjectProperty) {
			return new EObjectDeserializer(
					(ResourceSet) ctxt.getAttribute("resourceSet"),
					(Cache) ctxt.getAttribute("cache"),
					(EReference) ((EObjectProperty) property).getFeature(),
					entries);
		}
		return this;
	}

	@SuppressWarnings("unchecked")
	@Override
	public EObjectContainmentEList deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		if (owner != null && feature != null) {
			EObjectContainmentEList value = (EObjectContainmentEList) owner.eGet(feature);

			JsonToken nextToken = jp.nextToken();
			while (nextToken != null && nextToken != JsonToken.END_ARRAY) {
				final JsonToken currentToken = jp.getCurrentToken();
				Object currentValue = null;

				if (JsonToken.START_OBJECT.equals(currentToken)) {
//					currentValue = jp.readValueAs(EObject.class);
//					System.out.println(currentValue);
					currentValue = ctxt.readPropertyValue(jp,
							new EObjectProperty(owner, feature),
							EObjectContainmentEList.class);
					System.out.println(currentValue);
				}

				if (currentValue != null) {
					try {
						value.add(currentValue);
					} catch (Exception e) {
						throw new IOException(e);
					}
				}

				nextToken = jp.nextToken();
			}

			return value;
		}

		return null;
	}

}
