package org.emfjson.jackson.module;

import org.emfjson.jackson.streaming.EListSerializer;
import org.emfjson.jackson.streaming.EObjectSerializer;
import org.emfjson.jackson.streaming.ResourceSerializer;

import com.fasterxml.jackson.databind.module.SimpleModule;

public class EMFModule2 extends SimpleModule {

	private static final long serialVersionUID = 103927555654884226L;

	public EMFModule2() {
		addSerializer(new EObjectSerializer());
		addSerializer(new EListSerializer());
		addSerializer(new ResourceSerializer());
	}

}
