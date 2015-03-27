package org.emfjson.jackson.databind.ser;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateSerializer extends JsonSerializer<Date> {

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeString(sdf.format(value));
	}

	@Override
	public Class<Date> handledType() {
		return Date.class;
	}

}