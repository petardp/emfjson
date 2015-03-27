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
import java.util.Date;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectEList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>User</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.emfjson.jackson.junit.model.User#getUserId <em>User Id</em>}</li>
 *   <li>{@link org.emfjson.jackson.junit.model.User#getName <em>Name</em>}</li>
 *   <li>{@link org.emfjson.jackson.junit.model.User#getBirthDate <em>Birth Date</em>}</li>
 *   <li>{@link org.emfjson.jackson.junit.model.User#getSex <em>Sex</em>}</li>
 *   <li>{@link org.emfjson.jackson.junit.model.User#getFriends <em>Friends</em>}</li>
 *   <li>{@link org.emfjson.jackson.junit.model.User#getUniqueFriend <em>Unique Friend</em>}</li>
 *   <li>{@link org.emfjson.jackson.junit.model.User#getAddress <em>Address</em>}</li>
 *   <li>{@link org.emfjson.jackson.junit.model.User#getUniqueFriendResolve <em>Unique Friend Resolve</em>}</li>
 * </ul>
 *
 * @generated
 */
public class User extends MinimalEObjectImpl.Container implements EObject {
	/**
	 * The default value of the '{@link #getUserId() <em>User Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserId()
	 * @generated
	 * @ordered
	 */
	protected static final String USER_ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getUserId() <em>User Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUserId()
	 * @generated
	 * @ordered
	 */
	protected String userId = USER_ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getBirthDate() <em>Birth Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBirthDate()
	 * @generated
	 * @ordered
	 */
	protected static final Date BIRTH_DATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getBirthDate() <em>Birth Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBirthDate()
	 * @generated
	 * @ordered
	 */
	protected Date birthDate = BIRTH_DATE_EDEFAULT;

	/**
	 * The default value of the '{@link #getSex() <em>Sex</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSex()
	 * @generated
	 * @ordered
	 */
	protected static final Sex SEX_EDEFAULT = Sex.MALE;

	/**
	 * The cached value of the '{@link #getSex() <em>Sex</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSex()
	 * @generated
	 * @ordered
	 */
	protected Sex sex = SEX_EDEFAULT;

	/**
	 * The cached value of the '{@link #getFriends() <em>Friends</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFriends()
	 * @generated
	 * @ordered
	 */
	protected EList<User> friends;

	/**
	 * The cached value of the '{@link #getUniqueFriend() <em>Unique Friend</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUniqueFriend()
	 * @generated
	 * @ordered
	 */
	protected User uniqueFriend;

	/**
	 * The cached value of the '{@link #getAddress() <em>Address</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAddress()
	 * @generated
	 * @ordered
	 */
	protected Address address;

	/**
	 * The cached value of the '{@link #getUniqueFriendResolve() <em>Unique Friend Resolve</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUniqueFriendResolve()
	 * @generated
	 * @ordered
	 */
	protected User uniqueFriendResolve;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected User() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.USER;
	}

	/**
	 * Returns the value of the '<em><b>User Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User Id</em>' attribute.
	 * @see #setUserId(String)
	 * @generated
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the value of the '{@link org.emfjson.jackson.junit.model.User#getUserId <em>User Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User Id</em>' attribute.
	 * @see #getUserId()
	 * @generated
	 */
	public void setUserId(String newUserId) {
		String oldUserId = userId;
		userId = newUserId;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.USER__USER_ID, oldUserId, userId));
	}

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the value of the '{@link org.emfjson.jackson.junit.model.User#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.USER__NAME, oldName, name));
	}

	/**
	 * Returns the value of the '<em><b>Birth Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Birth Date</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Birth Date</em>' attribute.
	 * @see #setBirthDate(Date)
	 * @generated
	 */
	public Date getBirthDate() {
		return birthDate;
	}

	/**
	 * Sets the value of the '{@link org.emfjson.jackson.junit.model.User#getBirthDate <em>Birth Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Birth Date</em>' attribute.
	 * @see #getBirthDate()
	 * @generated
	 */
	public void setBirthDate(Date newBirthDate) {
		Date oldBirthDate = birthDate;
		birthDate = newBirthDate;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.USER__BIRTH_DATE, oldBirthDate, birthDate));
	}

	/**
	 * Returns the value of the '<em><b>Sex</b></em>' attribute.
	 * The literals are from the enumeration {@link org.emfjson.jackson.junit.model.Sex}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sex</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sex</em>' attribute.
	 * @see org.emfjson.jackson.junit.model.Sex
	 * @see #setSex(Sex)
	 * @generated
	 */
	public Sex getSex() {
		return sex;
	}

	/**
	 * Sets the value of the '{@link org.emfjson.jackson.junit.model.User#getSex <em>Sex</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Sex</em>' attribute.
	 * @see org.emfjson.jackson.junit.model.Sex
	 * @see #getSex()
	 * @generated
	 */
	public void setSex(Sex newSex) {
		Sex oldSex = sex;
		sex = newSex == null ? SEX_EDEFAULT : newSex;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.USER__SEX, oldSex, sex));
	}

	/**
	 * Returns the value of the '<em><b>Friends</b></em>' reference list.
	 * The list contents are of type {@link org.emfjson.jackson.junit.model.User}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Friends</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Friends</em>' reference list.
	 * @generated
	 */
	public List<User> getFriends() {
		if (friends == null) {
			friends = new EObjectEList<User>(User.class, this, ModelPackage.USER__FRIENDS);
		}
		return friends;
	}

	/**
	 * Returns the value of the '<em><b>Unique Friend</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unique Friend</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unique Friend</em>' reference.
	 * @see #setUniqueFriend(User)
	 * @generated
	 */
	public User getUniqueFriend() {
		return uniqueFriend;
	}

	/**
	 * Sets the value of the '{@link org.emfjson.jackson.junit.model.User#getUniqueFriend <em>Unique Friend</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unique Friend</em>' reference.
	 * @see #getUniqueFriend()
	 * @generated
	 */
	public void setUniqueFriend(User newUniqueFriend) {
		User oldUniqueFriend = uniqueFriend;
		uniqueFriend = newUniqueFriend;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.USER__UNIQUE_FRIEND, oldUniqueFriend, uniqueFriend));
	}

	/**
	 * Returns the value of the '<em><b>Address</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Address</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Address</em>' containment reference.
	 * @see #setAddress(Address)
	 * @generated
	 */
	public Address getAddress() {
		if (address != null && address.eIsProxy()) {
			InternalEObject oldAddress = (InternalEObject)address;
			address = (Address)eResolveProxy(oldAddress);
			if (address != oldAddress) {
				InternalEObject newAddress = (InternalEObject)address;
				NotificationChain msgs = oldAddress.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.USER__ADDRESS, null, null);
				if (newAddress.eInternalContainer() == null) {
					msgs = newAddress.eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.USER__ADDRESS, null, msgs);
				}
				if (msgs != null) msgs.dispatch();
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.USER__ADDRESS, oldAddress, address));
			}
		}
		return address;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Address basicGetAddress() {
		return address;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAddress(Address newAddress, NotificationChain msgs) {
		Address oldAddress = address;
		address = newAddress;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.USER__ADDRESS, oldAddress, newAddress);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * Sets the value of the '{@link org.emfjson.jackson.junit.model.User#getAddress <em>Address</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Address</em>' containment reference.
	 * @see #getAddress()
	 * @generated
	 */
	public void setAddress(Address newAddress) {
		if (newAddress != address) {
			NotificationChain msgs = null;
			if (address != null)
				msgs = ((InternalEObject)address).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.USER__ADDRESS, null, msgs);
			if (newAddress != null)
				msgs = ((InternalEObject)newAddress).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.USER__ADDRESS, null, msgs);
			msgs = basicSetAddress(newAddress, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.USER__ADDRESS, newAddress, newAddress));
	}

	/**
	 * Returns the value of the '<em><b>Unique Friend Resolve</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unique Friend Resolve</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unique Friend Resolve</em>' reference.
	 * @see #setUniqueFriendResolve(User)
	 * @generated
	 */
	public User getUniqueFriendResolve() {
		if (uniqueFriendResolve != null && uniqueFriendResolve.eIsProxy()) {
			InternalEObject oldUniqueFriendResolve = (InternalEObject)uniqueFriendResolve;
			uniqueFriendResolve = (User)eResolveProxy(oldUniqueFriendResolve);
			if (uniqueFriendResolve != oldUniqueFriendResolve) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.USER__UNIQUE_FRIEND_RESOLVE, oldUniqueFriendResolve, uniqueFriendResolve));
			}
		}
		return uniqueFriendResolve;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public User basicGetUniqueFriendResolve() {
		return uniqueFriendResolve;
	}

	/**
	 * Sets the value of the '{@link org.emfjson.jackson.junit.model.User#getUniqueFriendResolve <em>Unique Friend Resolve</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unique Friend Resolve</em>' reference.
	 * @see #getUniqueFriendResolve()
	 * @generated
	 */
	public void setUniqueFriendResolve(User newUniqueFriendResolve) {
		User oldUniqueFriendResolve = uniqueFriendResolve;
		uniqueFriendResolve = newUniqueFriendResolve;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.USER__UNIQUE_FRIEND_RESOLVE, oldUniqueFriendResolve, uniqueFriendResolve));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.USER__ADDRESS:
				return basicSetAddress(null, msgs);
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
			case ModelPackage.USER__USER_ID:
				return getUserId();
			case ModelPackage.USER__NAME:
				return getName();
			case ModelPackage.USER__BIRTH_DATE:
				return getBirthDate();
			case ModelPackage.USER__SEX:
				return getSex();
			case ModelPackage.USER__FRIENDS:
				return getFriends();
			case ModelPackage.USER__UNIQUE_FRIEND:
				return getUniqueFriend();
			case ModelPackage.USER__ADDRESS:
				if (resolve) return getAddress();
				return basicGetAddress();
			case ModelPackage.USER__UNIQUE_FRIEND_RESOLVE:
				if (resolve) return getUniqueFriendResolve();
				return basicGetUniqueFriendResolve();
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
			case ModelPackage.USER__USER_ID:
				setUserId((String)newValue);
				return;
			case ModelPackage.USER__NAME:
				setName((String)newValue);
				return;
			case ModelPackage.USER__BIRTH_DATE:
				setBirthDate((Date)newValue);
				return;
			case ModelPackage.USER__SEX:
				setSex((Sex)newValue);
				return;
			case ModelPackage.USER__FRIENDS:
				getFriends().clear();
				getFriends().addAll((Collection<? extends User>)newValue);
				return;
			case ModelPackage.USER__UNIQUE_FRIEND:
				setUniqueFriend((User)newValue);
				return;
			case ModelPackage.USER__ADDRESS:
				setAddress((Address)newValue);
				return;
			case ModelPackage.USER__UNIQUE_FRIEND_RESOLVE:
				setUniqueFriendResolve((User)newValue);
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
			case ModelPackage.USER__USER_ID:
				setUserId(USER_ID_EDEFAULT);
				return;
			case ModelPackage.USER__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModelPackage.USER__BIRTH_DATE:
				setBirthDate(BIRTH_DATE_EDEFAULT);
				return;
			case ModelPackage.USER__SEX:
				setSex(SEX_EDEFAULT);
				return;
			case ModelPackage.USER__FRIENDS:
				getFriends().clear();
				return;
			case ModelPackage.USER__UNIQUE_FRIEND:
				setUniqueFriend((User)null);
				return;
			case ModelPackage.USER__ADDRESS:
				setAddress((Address)null);
				return;
			case ModelPackage.USER__UNIQUE_FRIEND_RESOLVE:
				setUniqueFriendResolve((User)null);
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
			case ModelPackage.USER__USER_ID:
				return USER_ID_EDEFAULT == null ? userId != null : !USER_ID_EDEFAULT.equals(userId);
			case ModelPackage.USER__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ModelPackage.USER__BIRTH_DATE:
				return BIRTH_DATE_EDEFAULT == null ? birthDate != null : !BIRTH_DATE_EDEFAULT.equals(birthDate);
			case ModelPackage.USER__SEX:
				return sex != SEX_EDEFAULT;
			case ModelPackage.USER__FRIENDS:
				return friends != null && !friends.isEmpty();
			case ModelPackage.USER__UNIQUE_FRIEND:
				return uniqueFriend != null;
			case ModelPackage.USER__ADDRESS:
				return address != null;
			case ModelPackage.USER__UNIQUE_FRIEND_RESOLVE:
				return uniqueFriendResolve != null;
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
		result.append(" (userId: ");
		result.append(userId);
		result.append(", name: ");
		result.append(name);
		result.append(", birthDate: ");
		result.append(birthDate);
		result.append(", sex: ");
		result.append(sex);
		result.append(')');
		return result.toString();
	}

} // User
