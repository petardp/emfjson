package org.emfjson.jackson.databind.ser.list;

import java.io.IOException;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

@SuppressWarnings("rawtypes")
public class EObjectContainmentEListSerializer extends JsonSerializer<EObjectContainmentEList> implements ContextualSerializer {

	@Override
	public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
		return this;
	}

	@Override
	public void serialize(EObjectContainmentEList values, JsonGenerator jg, SerializerProvider provider) throws IOException {
		jg.writeStartArray();
		for (Object value: values) {
			System.out.println(value);
			jg.writeObject(value);
		}
		jg.writeEndArray();
	}

	@Override
	public Class<EObjectContainmentEList> handledType() {
		return EObjectContainmentEList.class;
	}

}
