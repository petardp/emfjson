package org.emfjson.jackson.databind;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.type.SimpleType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

import java.lang.annotation.Annotation;

public class ResourceProperty implements BeanProperty {

	private final Resource resource;

	public ResourceProperty(Resource resource) {
		this.resource = resource;
	}

	@Override
	public String getName() {
		return "contents";
	}

	@Override
	public PropertyName getFullName() {
		return new PropertyName("contents");
	}

	@Override
	public JavaType getType() {
		return SimpleType.construct(EObject.class);
	}

	@Override
	public PropertyName getWrapperName() {
		return null;
	}

	@Override
	public PropertyMetadata getMetadata() {
		return null;
	}

	@Override
	public boolean isRequired() {
		return false;
	}

	@Override
	public <A extends Annotation> A getAnnotation(Class<A> acls) {
		return null;
	}

	@Override
	public <A extends Annotation> A getContextAnnotation(Class<A> acls) {
		return null;
	}

	@Override
	public AnnotatedMember getMember() {
		return null;
	}

	@Override
	public void depositSchemaProperty(JsonObjectFormatVisitor objectVisitor) throws JsonMappingException {

	}

}
