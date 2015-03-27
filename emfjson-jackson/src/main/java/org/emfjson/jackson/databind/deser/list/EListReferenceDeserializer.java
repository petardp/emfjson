package org.emfjson.jackson.databind.deser.list;

import java.io.IOException;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.emfjson.*;
import org.emfjson.common.*;
import org.emfjson.jackson.databind.EObjectProperty;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

import static com.fasterxml.jackson.core.JsonToken.FIELD_NAME;
import static org.emfjson.common.Constants.EJS_REF_KEYWORD;

@SuppressWarnings("rawtypes")
public class EListReferenceDeserializer extends JsonDeserializer<EcoreEList> implements ContextualDeserializer {

	private final EObject owner;
	private final EStructuralFeature feature;

	public EListReferenceDeserializer(EObjectProperty property) {
		this.owner = property.getOwner();
		this.feature = property.getFeature();
	}

	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property)
			throws JsonMappingException {
		return this;
	}

	@Override
	public EcoreEList deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
		Set<ReferenceEntry> entries = (Set<ReferenceEntry>) ctxt.getAttribute("entries");

		if (owner != null && feature != null) {
			while (jp.nextToken() != JsonToken.END_ARRAY) {
				final JsonToken currentToken = jp.getCurrentToken();

				if (currentToken.equals(JsonToken.START_OBJECT)) {
					JsonToken token = jp.nextToken();

					if (token == FIELD_NAME) {
						if (jp.getCurrentName().equals(EJS_REF_KEYWORD)) {
							entries.add(new ReferenceEntry(owner, (EReference) feature, jp.nextTextValue()));
						}
					}
				}
			}
		}

		return null;
	}

}
