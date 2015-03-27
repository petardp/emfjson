package org.emfjson.jackson.databind;

import java.lang.annotation.Annotation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;
import com.fasterxml.jackson.databind.type.SimpleType;

public class EObjectProperty implements BeanProperty {

	private final EStructuralFeature feature;
	private final EObject owner;

	public EObjectProperty(EObject owner, EStructuralFeature feature) {
		this.feature = feature;
		this.owner = owner;
	}

	@Override
	public String getName() {
		return feature.getName();
	}

	@Override
	public PropertyName getFullName() {
		return new PropertyName(getName());
	}

	@Override
	public JavaType getType() {
		return SimpleType.construct(feature.getEType().getInstanceClass());
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
	public void depositSchemaProperty(JsonObjectFormatVisitor objectVisitor) throws JsonMappingException {}

	public EStructuralFeature getFeature() {
		return feature;
	}

	public EObject getOwner() {
		return owner;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof EObjectProperty) {
			EObjectProperty property = (EObjectProperty) obj;
			if (property.getFeature() != null) {
				if (property.getOwner() != null) {
					return property.getFeature().equals(getFeature()) &&
							property.getOwner().equals(getOwner());
				} else {
					return getOwner() == null && 
							property.getFeature().equals(getFeature());
				}
			} 
		}
		return super.equals(obj);
	}

}
