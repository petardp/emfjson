package org.emfjson.jackson.junit.tests.deser;

import static org.junit.Assert.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.emfjson.jackson.junit.model.ModelPackage;
import org.emfjson.jackson.module.EMFModule;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeSerRefTest {

	private final ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setUp() {
		EPackage.Registry.INSTANCE.put(ModelPackage.eNS_URI, ModelPackage.eINSTANCE);
		mapper.registerModule(new EMFModule());
	}

	@Test
	public void testSingleReferenceInSameResource() throws JsonProcessingException {
		JsonNode data = mapper.createArrayNode()
				.add(mapper.createObjectNode()
						.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//Node")
						.set("target", mapper.createObjectNode()
								.put("$ref", "/1")))
				.add(mapper.createObjectNode()
						.put("eClass", "http://www.eclipselabs.org/emfjson/junit#//Node"));

		Resource resource = mapper.treeToValue(data, Resource.class);

		assertEquals(2, resource.getContents().size());
		
		EObject first = resource.getContents().get(0);
		EObject second = resource.getContents().get(1);
		
		assertEquals("Node", first.eClass().getName());
		assertEquals("Node", second.eClass().getName());

		assertSame(second, first.eGet(first.eClass().getEStructuralFeature("target")));
		assertSame(first, second.eGet(second.eClass().getEStructuralFeature("source")));
	}

}
