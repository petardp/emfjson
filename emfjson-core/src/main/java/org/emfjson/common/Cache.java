/*
 * Copyright (c) 2011-2014 Guillaume Hillairet.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Guillaume Hillairet - initial API and implementation
 */
package org.emfjson.common;

import static org.emfjson.common.ModelUtil.getElementName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

public class Cache {

	protected final Map<EObject, String> mapOfID = new HashMap<>();
	protected final Map<EClass, List<EReference>> mapOfReferences = new HashMap<>();
	protected final Map<EClass, List<EAttribute>> mapOfAttributes = new HashMap<>();
	protected final Map<EStructuralFeature, String> mapOfNames = new HashMap<>();
	protected final Map<String, EClass> mapOfClasses = new HashMap<>();
	protected final Map<String, URI> mapOfURIs = new HashMap<>();
	protected final Map<EClass, Map<String, EStructuralFeature>> mapOfFeatures = new HashMap<>();
	protected final Map<String, EObject> mapOfObjectsById = new HashMap<>();

	public String getKey(EStructuralFeature feature) {
		String key = mapOfNames.get(feature);
		if (key == null) {
			key = getElementName(feature);
			mapOfNames.put(feature, key);
		}		
		return key;
	}

	public List<EReference> getReferences(EClass eClass) {
		List<EReference> references = mapOfReferences.get(eClass);
		if (references == null) {
			references = eClass.getEAllReferences();
			mapOfReferences.put(eClass, references);
		}
		return references;
	}

	public List<EAttribute> getAttributes(EClass eClass) {
		List<EAttribute> attributes = mapOfAttributes.get(eClass);
		if (attributes == null) {
			attributes = eClass.getEAllAttributes();
			mapOfAttributes.put(eClass, attributes);
		}
		return attributes;
	}

	public EClass getEClass(ResourceSet resourceSet, String type) {
		EClass eClass = mapOfClasses.get(type);
		if (eClass == null) {
			URI uri = mapOfURIs.get(type);
			if (uri == null) {
				uri = URI.createURI(type);
				mapOfURIs.put(type, uri);
			}
			eClass = (EClass) resourceSet.getEObject(uri, true);
			if (eClass != null) {
				mapOfClasses.put(type, eClass);
			}
		}
		return eClass;
	}

	public EStructuralFeature getEStructuralFeature(EClass eClass, String key) {
		Map<String, EStructuralFeature> featureByKey = mapOfFeatures.get(eClass);

		if (featureByKey == null) {
			featureByKey = new HashMap<>();
			mapOfFeatures.put(eClass, featureByKey);
		}

		EStructuralFeature feature = featureByKey.get(key);
		if (feature == null) {
			feature = findEStructuralFeature(eClass, key);
			if (feature != null) {
				featureByKey.put(key, feature);
			}
		}

		return feature;
	}

    private EStructuralFeature findEStructuralFeature(EClass eClass, String key) {
        if (eClass == null || key == null)
            return null;

        EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(key);

        if (eStructuralFeature == null) {
            int i = 0;
            List<EStructuralFeature> features = eClass.getEAllStructuralFeatures();
            while (i < features.size() && eStructuralFeature == null) {
                EStructuralFeature current = features.get(i);
                if (key.equals(getKey(current))) {
                    eStructuralFeature = current;
                }
                i++;
            }
        }

        return eStructuralFeature;
    }

    public String getHref(Resource current, EObject object) {
		String key = mapOfID.get(object);
		if (key != null) {
			return key;
		}

		String id = EcoreUtil.getID(object);
		if (id != null) {
			key = id;
		} else {
			final URI eObjectURI = EcoreUtil.getURI(object);
			final URI baseURI = eObjectURI.trimFragment().trimQuery();
			final String fragment = eObjectURI.fragment();

			if ((baseURI != null && !baseURI.hasAuthority())) {
				key = fragment;
			}
			else if (object.eResource() != null && object.eResource() != current) {
				key = eObjectURI.toString();
			} else {
				if (baseURI.isEmpty() || current == null || (current != null && baseURI.equals(current.getURI()))) {
					key = fragment;
				} else {
					key = eObjectURI.toString();
				}
			}
		}

		mapOfID.put(object, key);

		return key;
	}

    public void put(String id, EObject object) {
    	mapOfObjectsById.put(id, object);
    }

	public EObject getEObject(String id) {
		return mapOfObjectsById.get(id);
	}

}
