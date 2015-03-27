package org.emfjson.jackson.databind.ser;

import java.io.IOException;

import org.eclipse.emf.common.util.Enumerator;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class EnumeratorSerializer extends JsonSerializer<Enumerator> {

	@Override
	public void serialize(Enumerator value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeString(value.getLiteral());
	}

	@Override
	public Class<Enumerator> handledType() {
		return Enumerator.class;
	}
}
