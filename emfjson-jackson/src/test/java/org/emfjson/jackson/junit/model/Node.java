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
package org.emfjson.jackson.junit.model;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.emfjson.jackson.junit.model.Node#getLabel <em>Label</em>}</li>
 *   <li>{@link org.emfjson.jackson.junit.model.Node#getTarget <em>Target</em>}</li>
 *   <li>{@link org.emfjson.jackson.junit.model.Node#getSource <em>Source</em>}</li>
 *   <li>{@link org.emfjson.jackson.junit.model.Node#getManyRef <em>Many Ref</em>}</li>
 *   <li>{@link org.emfjson.jackson.junit.model.Node#getChild <em>Child</em>}</li>
 *   <li>{@link org.emfjson.jackson.junit.model.Node#getUniqueChild <em>Unique Child</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Node extends MinimalEObjectImpl.Container implements EObject {
	/**
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected String label = LABEL_EDEFAULT;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected Node target;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected Node source;

	/**
	 * The cached value of the '{@link #getManyRef() <em>Many Ref</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getManyRef()
	 * @generated
	 * @ordered
	 */
	protected EList<Node> manyRef;

	/**
	 * The cached value of the '{@link #getChild() <em>Child</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChild()
	 * @generated
	 * @ordered
	 */
	protected EList<Node> child;

	/**
	 * The cached value of the '{@link #getUniqueChild() <em>Unique Child</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUniqueChild()
	 * @generated
	 * @ordered
	 */
	protected Node uniqueChild;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Node() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.NODE;
	}

	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @generated
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Sets the value of the '{@link org.emfjson.jackson.junit.model.Node#getLabel <em>Label</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	public void setLabel(String newLabel) {
		String oldLabel = label;
		label = newLabel;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__LABEL, oldLabel, label));
	}

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.emfjson.jackson.junit.model.Node#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Node)
	 * @see org.emfjson.jackson.junit.model.Node#getSource
	 * @generated
	 */
	public Node getTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTarget(Node newTarget, NotificationChain msgs) {
		Node oldTarget = target;
		target = newTarget;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__TARGET, oldTarget, newTarget);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * Sets the value of the '{@link org.emfjson.jackson.junit.model.Node#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	public void setTarget(Node newTarget) {
		if (newTarget != target) {
			NotificationChain msgs = null;
			if (target != null)
				msgs = ((InternalEObject)target).eInverseRemove(this, ModelPackage.NODE__SOURCE, Node.class, msgs);
			if (newTarget != null)
				msgs = ((InternalEObject)newTarget).eInverseAdd(this, ModelPackage.NODE__SOURCE, Node.class, msgs);
			msgs = basicSetTarget(newTarget, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__TARGET, newTarget, newTarget));
	}

	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link org.emfjson.jackson.junit.model.Node#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(Node)
	 * @see org.emfjson.jackson.junit.model.Node#getTarget
	 * @generated
	 */
	public Node getSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSource(Node newSource, NotificationChain msgs) {
		Node oldSource = source;
		source = newSource;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__SOURCE, oldSource, newSource);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * Sets the value of the '{@link org.emfjson.jackson.junit.model.Node#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	public void setSource(Node newSource) {
		if (newSource != source) {
			NotificationChain msgs = null;
			if (source != null)
				msgs = ((InternalEObject)source).eInverseRemove(this, ModelPackage.NODE__TARGET, Node.class, msgs);
			if (newSource != null)
				msgs = ((InternalEObject)newSource).eInverseAdd(this, ModelPackage.NODE__TARGET, Node.class, msgs);
			msgs = basicSetSource(newSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__SOURCE, newSource, newSource));
	}

	/**
	 * Returns the value of the '<em><b>Many Ref</b></em>' reference list.
	 * The list contents are of type {@link org.emfjson.jackson.junit.model.Node}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Many Ref</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Many Ref</em>' reference list.
	 * @generated
	 */
	public List<Node> getManyRef() {
		if (manyRef == null) {
			manyRef = new EObjectEList<Node>(Node.class, this, ModelPackage.NODE__MANY_REF);
		}
		return manyRef;
	}

	/**
	 * Returns the value of the '<em><b>Child</b></em>' containment reference list.
	 * The list contents are of type {@link org.emfjson.jackson.junit.model.Node}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Child</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Child</em>' containment reference list.
	 * @generated
	 */
	public List<Node> getChild() {
		if (child == null) {
			child = new EObjectContainmentEList.Resolving<Node>(Node.class, this, ModelPackage.NODE__CHILD);
		}
		return child;
	}

	/**
	 * Returns the value of the '<em><b>Unique Child</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unique Child</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unique Child</em>' containment reference.
	 * @see #setUniqueChild(Node)
	 * @generated
	 */
	public Node getUniqueChild() {
		if (uniqueChild != null && uniqueChild.eIsProxy()) {
			InternalEObject oldUniqueChild = (InternalEObject)uniqueChild;
			uniqueChild = (Node)eResolveProxy(oldUniqueChild);
			if (uniqueChild != oldUniqueChild) {
				InternalEObject newUniqueChild = (InternalEObject)uniqueChild;
				NotificationChain msgs = oldUniqueChild.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.NODE__UNIQUE_CHILD, null, null);
				if (newUniqueChild.eInternalContainer() == null) {
					msgs = newUniqueChild.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.NODE__UNIQUE_CHILD, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.NODE__UNIQUE_CHILD, oldUniqueChild, uniqueChild));
			}
		}
		return uniqueChild;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node basicGetUniqueChild() {
		return uniqueChild;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetUniqueChild(Node newUniqueChild, NotificationChain msgs) {
		Node oldUniqueChild = uniqueChild;
		uniqueChild = newUniqueChild;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__UNIQUE_CHILD, oldUniqueChild, newUniqueChild);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * Sets the value of the '{@link org.emfjson.jackson.junit.model.Node#getUniqueChild <em>Unique Child</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unique Child</em>' containment reference.
	 * @see #getUniqueChild()
	 * @generated
	 */
	public void setUniqueChild(Node newUniqueChild) {
		if (newUniqueChild != uniqueChild) {
			NotificationChain msgs = null;
			if (uniqueChild != null)
				msgs = ((InternalEObject)uniqueChild).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.NODE__UNIQUE_CHILD, null, msgs);
			if (newUniqueChild != null)
				msgs = ((InternalEObject)newUniqueChild).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.NODE__UNIQUE_CHILD, null, msgs);
			msgs = basicSetUniqueChild(newUniqueChild, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.NODE__UNIQUE_CHILD, newUniqueChild, newUniqueChild));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.NODE__TARGET:
				if (target != null)
					msgs = ((InternalEObject)target).eInverseRemove(this, ModelPackage.NODE__SOURCE, Node.class, msgs);
				return basicSetTarget((Node)otherEnd, msgs);
			case ModelPackage.NODE__SOURCE:
				if (source != null)
					msgs = ((InternalEObject)source).eInverseRemove(this, ModelPackage.NODE__TARGET, Node.class, msgs);
				return basicSetSource((Node)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.NODE__TARGET:
				return basicSetTarget(null, msgs);
			case ModelPackage.NODE__SOURCE:
				return basicSetSource(null, msgs);
			case ModelPackage.NODE__CHILD:
				return ((InternalEList<?>)getChild()).basicRemove(otherEnd, msgs);
			case ModelPackage.NODE__UNIQUE_CHILD:
				return basicSetUniqueChild(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.NODE__LABEL:
				return getLabel();
			case ModelPackage.NODE__TARGET:
				return getTarget();
			case ModelPackage.NODE__SOURCE:
				return getSource();
			case ModelPackage.NODE__MANY_REF:
				return getManyRef();
			case ModelPackage.NODE__CHILD:
				return getChild();
			case ModelPackage.NODE__UNIQUE_CHILD:
				if (resolve) return getUniqueChild();
				return basicGetUniqueChild();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.NODE__LABEL:
				setLabel((String)newValue);
				return;
			case ModelPackage.NODE__TARGET:
				setTarget((Node)newValue);
				return;
			case ModelPackage.NODE__SOURCE:
				setSource((Node)newValue);
				return;
			case ModelPackage.NODE__MANY_REF:
				getManyRef().clear();
				getManyRef().addAll((Collection<? extends Node>)newValue);
				return;
			case ModelPackage.NODE__CHILD:
				getChild().clear();
				getChild().addAll((Collection<? extends Node>)newValue);
				return;
			case ModelPackage.NODE__UNIQUE_CHILD:
				setUniqueChild((Node)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ModelPackage.NODE__LABEL:
				setLabel(LABEL_EDEFAULT);
				return;
			case ModelPackage.NODE__TARGET:
				setTarget((Node)null);
				return;
			case ModelPackage.NODE__SOURCE:
				setSource((Node)null);
				return;
			case ModelPackage.NODE__MANY_REF:
				getManyRef().clear();
				return;
			case ModelPackage.NODE__CHILD:
				getChild().clear();
				return;
			case ModelPackage.NODE__UNIQUE_CHILD:
				setUniqueChild((Node)null);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModelPackage.NODE__LABEL:
				return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
			case ModelPackage.NODE__TARGET:
				return target != null;
			case ModelPackage.NODE__SOURCE:
				return source != null;
			case ModelPackage.NODE__MANY_REF:
				return manyRef != null && !manyRef.isEmpty();
			case ModelPackage.NODE__CHILD:
				return child != null && !child.isEmpty();
			case ModelPackage.NODE__UNIQUE_CHILD:
				return uniqueChild != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (label: ");
		result.append(label);
		result.append(')');
		return result.toString();
	}

} // Node
