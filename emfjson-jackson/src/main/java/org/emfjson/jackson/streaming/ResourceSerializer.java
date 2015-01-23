package org.emfjson.jackson.streaming;

import java.io.IOException;

import org.eclipse.emf.ecore.resource.Resource;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class ResourceSerializer extends JsonSerializer<Resource> {

	@Override
	public void serialize(Resource value, JsonGenerator generator, SerializerProvider provider) throws IOException {
		
	}

	@Override
	public Class<Resource> handledType() {
		return Resource.class;
	}

}
