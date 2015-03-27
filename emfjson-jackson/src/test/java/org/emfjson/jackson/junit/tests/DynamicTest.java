package org.emfjson.jackson.junit.tests;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.resource.*;
import org.eclipse.emf.ecore.resource.impl.*;
import org.emfjson.jackson.module.*;
import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class DynamicTest {

	private ObjectMapper mapper = new ObjectMapper();
	private ResourceSet resourceSet = new ResourceSetImpl();

	private JsonNode ePackageNode = mapper.createObjectNode()
			.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EPackage")
			.put("name", "foo")
			.put("nsURI", "http://test/foo")
			.put("nsPrefix", "foo")
			.put("_id", "/")
			.set("eClassifiers", mapper.createArrayNode()
					.add(mapper.createObjectNode()
							.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EClass")
							.put("name", "A")
							.put("_id", "//A")
							.set("eStructuralFeatures", mapper.createArrayNode()
									.add(mapper.createObjectNode()
											.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EReference")
											.put("name", "bs")
											.put("upperBound", -1)
											.set("eType", mapper.createObjectNode()
													.put("$ref", "//B")))))
					.add(mapper.createObjectNode()
							.put("eClass", "http://www.eclipse.org/emf/2002/Ecore#//EClass")
							.put("name", "B")
							.put("_id", "//B")));

	private JsonNode eNode = mapper.createObjectNode()
			.put("eClass", "http://test/foo#//A");

	@Before
	public void setup() {
		mapper.registerModule(new EMFModule());
		resourceSet.getPackageRegistry().put(EcorePackage.eNS_URI, EcorePackage.eINSTANCE);
	}

	@Test
	public void shouldDeserializeDynamicPackage() throws JsonProcessingException {
		EPackage result = (EPackage) mapper.reader()
				.withAttribute("resourceSet", resourceSet)
				.treeToValue(ePackageNode, EObject.class);

		assertNotNull(result);

		assertEquals("foo", result.getName());
		assertEquals("foo", result.getNsPrefix());
		assertEquals("http://test/foo", result.getNsURI());
		assertEquals(2, result.getEClassifiers().size());

		EClassifier a = result.getEClassifiers().get(0);
		assertEquals("A", a.getName());
		assertSame(EcorePackage.Literals.ECLASS, a.eClass());

		EClassifier b = result.getEClassifiers().get(1);
		assertEquals("B", b.getName());
		assertSame(EcorePackage.Literals.ECLASS, b.eClass());

		assertEquals(1, ((EClass) a).getEStructuralFeatures().size());

		EStructuralFeature bs_feature = ((EClass) a).getEStructuralFeatures().get(0);

		assertEquals("bs", bs_feature.getName());
		assertEquals(-1, bs_feature.getUpperBound());
		assertSame(EcorePackage.Literals.EREFERENCE, bs_feature.eClass());
		assertSame(b, bs_feature.getEType());
	}

}
