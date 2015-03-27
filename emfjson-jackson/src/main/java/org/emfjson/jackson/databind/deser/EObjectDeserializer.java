package org.emfjson.jackson.databind.deser;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.*;
import com.fasterxml.jackson.databind.type.*;
import org.eclipse.emf.common.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.*;
import org.eclipse.emf.ecore.resource.impl.*;
import org.eclipse.emf.ecore.util.*;
import org.emfjson.*;
import org.emfjson.common.*;
import org.emfjson.jackson.databind.*;
import org.emfjson.jackson.databind.deser.list.*;

import java.io.*;
import java.util.*;

import static com.fasterxml.jackson.core.JsonToken.*;
import static org.emfjson.common.Constants.*;

public class EObjectDeserializer extends JsonDeserializer<EObject> implements ContextualDeserializer {

	private final Cache cache;
	private final ResourceSet resourceSet;
	private final EReference containment;
	private final Set<ReferenceEntry> entries;

	public EObjectDeserializer() {
		this.resourceSet = null;
		this.cache = null;
		this.containment = null;
		this.entries = new HashSet<>();
	}

	public EObjectDeserializer(ResourceSet resourceSet, Cache cache, Set<ReferenceEntry> entries) {
		this.resourceSet = resourceSet;
		this.cache = cache;
		this.containment = null;
		this.entries = entries;
	}

	public EObjectDeserializer(ResourceSet resourceSet, Cache cache, EReference containment, Set<ReferenceEntry> entries) {
		this.resourceSet = resourceSet;
		this.cache = cache;
		this.containment = containment;
		this.entries = entries;
	}

	@Override
	public Class<?> handledType() {
		return EObject.class;
	}

	@Override
	public EObject deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
		Object root = ctxt.getAttribute(EMFJs.OPTION_ROOT_ELEMENT);

		ctxt.setAttribute("resourceSet", resourceSet);
		ctxt.setAttribute("cache", cache);
		ctxt.setAttribute("entries", entries);

		EObject current = null;

		if (containment == null && root instanceof EClass) {
			current = EcoreUtil.create((EClass) root);
		}

		while (parser.nextToken() != END_OBJECT) {
			final String fieldName = parser.getCurrentName();

			if (fieldName == null) {
				throw new JsonParseException(null, parser.getCurrentLocation(), null);

			} else if (fieldName.equals(EJS_TYPE_KEYWORD)) {
				if (current == null) {
					if (parser.getCurrentToken() == FIELD_NAME) {
						current = create(parser.nextTextValue());
					} else {
						current = create(parser.getText());
					}
				}

			} else if (fieldName.equalsIgnoreCase(EJS_ID_FIELD)) {
				String id;
				if (parser.getCurrentToken() == FIELD_NAME) {
					id = parser.nextTextValue();
				} else {
					id = parser.getText();
				}
				if (id != null && current != null) {
					cache.put(id, current);
				}
			} else {
				if (parser.getCurrentToken() == FIELD_NAME) {
					parser.nextToken();
				}

				if (current == null && containment != null) {
					final EClass defaultType = containment.getEReferenceType();

					if (!defaultType.isAbstract()) {
						current = EcoreUtil.create(defaultType);
					}
				}

				if (current != null) {
					readFeature(parser, current, fieldName, ctxt);
				}
			}
		}

//		if (isRoot) {
//			for (ReferenceEntry entry: entries) {
//				entry.resolve(resourceSet, cache);
//			}
//		}

		return current;
	}

	private void readFeature(JsonParser parser, EObject current, final String fieldName, DeserializationContext ctxt) 
			throws IOException {

		final EClass eClass = current.eClass();
		final EStructuralFeature feature = cache.getEStructuralFeature(eClass, fieldName);

		if (feature == null) {
			parser.nextToken();
			return;
		}

		Class<?> type = feature.getEType().getInstanceClass();

		if (EObject.class.isAssignableFrom(type) && !feature.isMany()) {

			EObject value = ctxt.readPropertyValue(parser, new EObjectProperty(current, feature), EObject.class);
			current.eSet(feature, value);

		} else if (EObject.class.isAssignableFrom(type) && feature.isMany()) {

			ctxt.readPropertyValue(parser, new EObjectProperty(current, feature), EObject.class);

		}  else {

			Object value;

			if (feature.isMany()) {
				value = ctxt.readValue(parser, CollectionType.construct(Collection.class, SimpleType.construct(type)));

				EObjects.addAll(current, feature, (Collection<?>) value);
			} else {
				if (Enumerator.class.isAssignableFrom(type)) {
					value = EcoreUtil.createFromString((EDataType) feature.getEType(), parser.getText());
				} else {
					value = ctxt.readValue(parser, type);
				}

				EObjects.set(current, feature, value);
				
				if (((EAttribute) feature).isID()) {
					cache.put((String) value, current);
				}
			}
		}
	}

	protected EObject create(String type) {
		EClass eClass = cache.getEClass(resourceSet, type);
		if (eClass != null) {
			return EcoreUtil.create(eClass);
		}
		return null;
	}

	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
		ResourceSet resourceSet = (ResourceSet) ctxt.getAttribute("resourceSet");
		if (resourceSet == null) {
			resourceSet = new ResourceSetImpl();
		}

		Cache cache = (Cache) ctxt.getAttribute("cache");
		if (cache == null) {
			cache = new Cache();
		}

		@SuppressWarnings("unchecked")
		Set<ReferenceEntry> entries = (Set<ReferenceEntry>) ctxt.getAttribute("entries");
		if (entries == null) {
			entries = new HashSet<>();
		}

		if (property instanceof EObjectProperty) {
			EStructuralFeature feature = ((EObjectProperty) property).getFeature();

			if (feature instanceof EReference) {
				EReference reference = (EReference) feature;
				System.out.println(reference.getName());

				if (reference.isContainment()) {
					if (reference.isMany()) {
						return new EObjectContainmentEListDeserializer((EObjectProperty) property);
					} else {
						return new EObjectDeserializer(resourceSet, cache, reference, entries);
					}
				} else {
					if (reference.isMany()) {
						return new EListReferenceDeserializer((EObjectProperty) property);
					} else {
						return new EObjectReferenceDeserializer((EObjectProperty) property, entries);
					}
				}
			}
		}

		return new EObjectDeserializer(resourceSet, cache, entries);
	}

}

