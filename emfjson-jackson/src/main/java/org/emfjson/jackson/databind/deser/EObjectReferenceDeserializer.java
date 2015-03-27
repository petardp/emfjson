package org.emfjson.jackson.databind.deser;

import java.io.IOException;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.emfjson.common.Constants;
import org.emfjson.common.ReferenceEntry;
import org.emfjson.jackson.databind.EObjectProperty;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class EObjectReferenceDeserializer extends JsonDeserializer<EObject> {

	private final EObject owner;
	private final EStructuralFeature feature;
	private final Set<ReferenceEntry> entries;

	public EObjectReferenceDeserializer(EObjectProperty property, Set<ReferenceEntry> entries) {
		this.owner = property.getOwner();
		this.feature = property.getFeature();
		this.entries = entries;
	}

	@Override
	public EObject deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		final JsonToken currentToken = jp.getCurrentToken();

		if (currentToken.equals(JsonToken.START_OBJECT)) {
			jp.nextToken();

			if (Constants.EJS_REF_KEYWORD.equals(jp.getCurrentName())) {
				entries.add(new ReferenceEntry(owner, (EReference) feature, jp.nextTextValue()));
			}
		}

		return null;
	}

}
