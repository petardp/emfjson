package org.emfjson.jackson.streaming;

import static org.emfjson.common.Constants.EJS_TYPE_KEYWORD;
import static org.emfjson.common.Constants.EJS_UUID_ANNOTATION;

import java.io.IOException;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emfjson.common.EObjects;
import org.emfjson.common.resource.UuidResource;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.util.TokenBuffer;

public class EObjectDeserializer extends JsonDeserializer<EObject> {

	@Override
	public EObject deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
		if (parser.getCurrentToken() == null) {
			parser.nextToken();
		}

		ctxt.getConfig().getAttributes();
		EObject result = null;
		switch (parser.getCurrentToken()) {
		case START_OBJECT:
			 result = parseObject(parser, null, null);
			break;
		default:
			break;
		}

		return result;
	}
	
	protected EObject parseObject(JsonParser parser, EReference containment, EObject owner) 
			throws IOException {

		EObject current = null;

		final TokenBuffer buffer = new TokenBuffer(parser);

		while (parser.nextToken() != JsonToken.END_OBJECT) {
			final String fieldName = parser.getCurrentName();

			switch (fieldName) {
			case EJS_TYPE_KEYWORD:
				current = create(parser.nextTextValue());
				break;
			case EJS_UUID_ANNOTATION:
				if (resource instanceof UuidResource) {
					if (current != null) {
						((UuidResource) resource).setID(current, parser.nextTextValue());
					}
				}
				break;
			default:
				if (current == null && containment != null) {
					final EClass defaultType = containment.getEReferenceType();

					if (!defaultType.isAbstract()) {
						current = EcoreUtil.create(defaultType);
					}
				}

				if (current != null) {
					readFeature(parser, current, fieldName);
				} else {
					buffer.copyCurrentStructure(parser);
				}
				break;
			}
		}

		buffer.close();

		if (current != null) {
			final JsonParser bufferedParser = buffer.asParser();
	
			while(bufferedParser.nextToken() != null) {
				readFeature(bufferedParser, current, bufferedParser.getCurrentName());
			}

			bufferedParser.close();
		}

		if (current != null && containment != null && owner != null) {
			EObjects.setOrAdd(owner, containment, current);
		}

		return current;
	}

}
