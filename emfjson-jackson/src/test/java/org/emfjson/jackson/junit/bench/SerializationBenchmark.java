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
package org.emfjson.jackson.junit.bench;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceFactoryImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.emfjson.EMFJs;
import org.emfjson.jackson.junit.model.ModelPackage;
import org.emfjson.jackson.module.EMFModule;
import org.emfjson.jackson.resource.JsonResourceFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SerializationBenchmark {

	int times = 50;

	public static void main(String[] args) throws JsonProcessingException {
		SerializationBenchmark b =  new SerializationBenchmark();
		// first
		System.out.println("--- 1st benchmarck ---");
//		b.benchmarkSerializeXmi(Benchmarks.first());
//		b.benchmarkSerializeBinary(Benchmarks.first());
		b.benchmarkSerializeJson(Benchmarks.first());
		b.benchmarkSerializeJson2(Benchmarks.first());
		// second
		System.out.println("--- 2nd benchmarck ---");
//		b.benchmarkSerializeXmi(Benchmarks.second());
//		b.benchmarkSerializeBinary(Benchmarks.second());
		b.benchmarkSerializeJson(Benchmarks.second());
		b.benchmarkSerializeJson2(Benchmarks.second());
		// third
		System.out.println("--- 3rd benchmarck ---");
//		b.benchmarkSerializeXmi(Benchmarks.third());
//		b.benchmarkSerializeBinary(Benchmarks.third());
//		b.benchmarkSerializeJson(Benchmarks.third());
//		b.benchmarkSerializeJson2(Benchmarks.third());
	}

	private long performSave(Resource resource, Map<String, Object> options) {
		long start = System.currentTimeMillis();
		try {
			resource.save(options);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return System.currentTimeMillis() - start;
	}
	
	private long performSave2(Resource resource) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		EMFModule m = new EMFModule();
		mapper.registerModule(m);

		long start = System.currentTimeMillis();

		mapper.writeValueAsBytes(resource.getContents().get(0));

		return System.currentTimeMillis() - start;
	}

	public void benchmarkSerializeXmi(EObject container) {
		long sum = 0;

		for (int i = 0; i < times; i++) {
			ResourceSet resourceSet = new ResourceSetImpl();
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new XMIResourceFactoryImpl());
			resourceSet.getPackageRegistry().put(ModelPackage.eNS_URI, ModelPackage.eINSTANCE);
			Resource resource = resourceSet.createResource(URI.createURI("bench1-model.xmi"));
			resource.getContents().add(container);

			sum += performSave(resource, null);
		}

		long average = sum / times;
		System.out.println("XMI: " + average / 1000.);
	}

	public void benchmarkSerializeBinary(EObject container) {
		long sum = 0;

		for (int i = 0; i < times; i++) {
			ResourceSet resourceSet = new ResourceSetImpl();
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new ResourceFactoryImpl() {
				@Override
				public Resource createResource(URI uri) {
					return new BinaryResourceImpl(uri);
				}
			});
			resourceSet.getPackageRegistry().put(ModelPackage.eNS_URI, ModelPackage.eINSTANCE);
			Resource resource = resourceSet.createResource(URI.createURI("bench1-model.n"));
			resource.getContents().add(container);

			sum += performSave(resource, null);
		}

		long average = sum / times;
		System.out.println("Binary: " + average / 1000.);
	}

	public void benchmarkSerializeJson(EObject container) {
		long sum = 0;
		Map<String, Object> options = new HashMap<>();
		options.put(EMFJs.OPTION_INDENT_OUTPUT, false);
		options.put(EMFJs.OPTION_SERIALIZE_REF_TYPE, false);
		options.put(EMFJs.OPTION_SERIALIZE_TYPE, false);

		for (int i = 0; i < times; i++) {
			ResourceSet resourceSet = new ResourceSetImpl();
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new JsonResourceFactory());
			resourceSet.getPackageRegistry().put(ModelPackage.eNS_URI, ModelPackage.eINSTANCE);
			Resource resource = resourceSet.createResource(URI.createURI("bench1-model.json"));
			resource.getContents().add(container);

			sum += performSave(resource, options);
		}

		long average = sum / times;
		System.out.println("JSON: " + average / 1000.);
	}
	
	public void benchmarkSerializeJson2(EObject container) throws JsonProcessingException {
		long sum = 0;
		for (int i = 0; i < times; i++) {
			ResourceSet resourceSet = new ResourceSetImpl();
			resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("*", new JsonResourceFactory());
			resourceSet.getPackageRegistry().put(ModelPackage.eNS_URI, ModelPackage.eINSTANCE);
			Resource resource = resourceSet.createResource(URI.createURI("bench1-model.json"));
			resource.getContents().add(container);
			sum += performSave2(resource);
		}
		long average = sum / times;
		System.out.println("JSON2: " + average / 1000.);
	}

}

