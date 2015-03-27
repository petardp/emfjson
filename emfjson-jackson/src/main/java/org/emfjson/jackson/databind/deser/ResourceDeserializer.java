package org.emfjson.jackson.databind.deser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import org.emfjson.common.Cache;
import org.emfjson.common.ReferenceEntry;
import org.emfjson.jackson.databind.ResourceProperty;
import org.emfjson.jackson.resource.JsonResource;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ResourceDeserializer extends JsonDeserializer<Resource> implements ContextualDeserializer {

	private final Cache cache;
	private final Set<ReferenceEntry> entries;
	private final Resource resource;

	public ResourceDeserializer(Resource resource, Cache cache, Set<ReferenceEntry> entries) {
		this.resource = resource;
		this.cache = cache;
		this.entries = entries;
	}

	public ResourceDeserializer() {
		this.resource = null;
		this.cache = new Cache();
		this.entries = new HashSet<>();
	}

	@Override
	public Resource deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
		ctxt.setAttribute("cache", cache);
		ctxt.setAttribute("entries", entries);

		if (parser.getCurrentToken() == null) {
			parser.nextToken();
		}

		if (parser.getCurrentToken() == JsonToken.START_OBJECT) {
			EObject root = ctxt.readPropertyValue(parser,
					new ResourceProperty(resource),
					EObject.class);

			if (root != null) {
				resource.getContents().add(root);
			}

		} else if (parser.getCurrentToken() == JsonToken.START_ARRAY) {

			while (parser.nextToken() != JsonToken.END_ARRAY) {
				EObject root = ctxt.readPropertyValue(parser,
						new ResourceProperty(resource),
						EObject.class);

				if (root != null) {
					resource.getContents().add(root);
				}
			}

		}

		for (ReferenceEntry entry: entries) {
			entry.resolve(resource.getResourceSet(), cache);
		}

		return resource;
	}

	@Override
	public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property)
			throws JsonMappingException {

		Resource resource = (Resource) ctxt.getAttribute("resource");
		if (resource == null) {
			resource = new JsonResource();
		}

		ResourceSet resourceSet = resource.getResourceSet();

		if (resourceSet == null) {
			resourceSet = (ResourceSet) ctxt.getAttribute("resourceSet");
		}
		if (resourceSet == null) {
			resourceSet = new ResourceSetImpl();
		}

		if (resource.getResourceSet() == null) {
			resourceSet.getResources().add(resource);
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

		if (property instanceof ResourceProperty) {
			return new EObjectDeserializer(resourceSet, cache, entries);
		}

		return new ResourceDeserializer(resource, cache, entries);
	}

}
