package org.emfjson.jackson.databind.ser;

import java.io.IOException;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ResourceSerializer extends JsonSerializer<Resource> {

	@Override
	public void serialize(Resource value, JsonGenerator generator, SerializerProvider provider) throws IOException {
		final List<EObject> contents = value.getContents();

		if (contents.size() == 1) {
			generator.writeObject(contents.get(0));
		} else {
			generator.writeObject(contents);
		}
	}

	@Override
	public Class<Resource> handledType() {
		return Resource.class;
	}

}
