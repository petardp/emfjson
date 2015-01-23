package org.emfjson.jackson.streaming;

import java.io.IOException;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.emfjson.common.Cache;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@SuppressWarnings("rawtypes")
public class EListSerializer extends JsonSerializer<EList> {

	private final Cache cache = new Cache();

	@Override
	public void serialize(EList value, JsonGenerator generator, SerializerProvider provider) throws IOException {
		generator.writeStartArray();

		for (Object current: value) {
			if (current instanceof EObject) {
				EObject o = (EObject) current;

				if (o.eContainmentFeature() != null) {
					generator.writeObject(o);
				} else {
					generator.writeString(cache.getHref(o.eResource(), o));
				}
			}
		}

		generator.writeEndArray();
	}

	@Override
	public Class<EList> handledType() {
		return EList.class;
	}

}
