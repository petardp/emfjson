package org.emfjson.jackson.databind.deser;

import java.io.IOException;

import org.eclipse.emf.common.util.Enumerator;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

public class EnumeratorDeserializer extends JsonDeserializer<Enumerator> implements ContextualDeserializer {

	public EnumeratorDeserializer() {}

	@Override
	public Enumerator deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
//		EcoreUtil.createFromString((EDataType) feature.getEType(), parser.getText());
		String value = jp.getText();
		return null;
	}

	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
		// TODO Auto-generated method stub
		return null;
	}

}
