package org.emfjson.jackson.update;

public class ObjectUpdater {

//	public void update(EObject object, String value, Map<?, ?> options) {
//		if (object == null) throw new IllegalArgumentException("Object to update should not be null");
//		if (object.eResource() == null) throw new IllegalArgumentException("Object to update should be contained in a Resource");
//
//		final JsonFactory factory = new JsonFactory();
//		final StreamUpdater updater = new StreamUpdater(Options.from(options).build());
//		try {
//			final JsonParser parser = factory.createParser(value);
//			updater.update(parser, object);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private class StreamUpdater extends StreamReader {
//
//		public StreamUpdater(Options options) {
//			super(options);
//		}
//
//		public void update(JsonParser parser, EObject current) throws IOException {
//			while (parser.nextToken() != JsonToken.END_OBJECT) {
//				final String fieldName = parser.getCurrentName();
//				final EClass eClass = current.eClass();
//				final EStructuralFeature feature = cache.getEStructuralFeature(eClass, fieldName);
//
//				if (feature != null) {
//					if (feature instanceof EAttribute) {
//						readAttribute(parser, (EAttribute) feature, current);
//					} else {
//						readReference(parser, (EReference) feature, current);
//					}
//				}
//			}
//		}
//	}

}
