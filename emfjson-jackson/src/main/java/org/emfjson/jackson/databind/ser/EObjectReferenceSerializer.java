package org.emfjson.jackson.databind.ser;

import java.io.IOException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.emfjson.common.Constants;
import org.emfjson.jackson.databind.EObjectProperty;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class EObjectReferenceSerializer extends JsonSerializer<EObject> {

	private EObjectProperty property;

	public EObjectReferenceSerializer(EObjectProperty property) {
		this.property = property;
	}

	@Override
	public void serialize(EObject value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		String id = EcoreUtil.getID(value);

		if (id == null) {
			URI uri = EcoreUtil.getURI(value);

			if (property != null && property.getOwner() != null) {
				EObject owner = property.getOwner();

				if (owner != null && owner.eResource() != null && value.eResource() != null) {
					if (owner.eResource().equals(value.eResource())) {
						id = uri.fragment();
					}
				}
			}

			if (id == null) {
				id = uri.toString();
			}
		}

		jgen.writeStartObject();
		jgen.writeStringField(Constants.EJS_REF_KEYWORD, id);
		jgen.writeEndObject();
	}

	@Override
	public Class<EObject> handledType() {
		return EObject.class;
	}

}
