package org.emfjson.jackson.databind.ser.list;

import java.io.IOException;

import org.eclipse.emf.ecore.util.NotifyingInternalEListImpl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@SuppressWarnings("rawtypes")
public class NotifyingInternalEListImplSerializer extends JsonSerializer<NotifyingInternalEListImpl> {

	@Override
	public void serialize(NotifyingInternalEListImpl values, JsonGenerator jg, SerializerProvider provider) throws IOException{
		System.out.println("here");
		jg.writeStartArray();
		for (Object value: values) {
			jg.writeObject(value);
		}
		jg.writeEndArray();
	}
	
	@Override
	public Class<NotifyingInternalEListImpl> handledType() {
		return NotifyingInternalEListImpl.class;
	}

}
