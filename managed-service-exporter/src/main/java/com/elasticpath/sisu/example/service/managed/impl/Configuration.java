package com.elasticpath.sisu.example.service.managed.impl;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * Configuration for the three word generator service.
 */
@ObjectClassDefinition(
		id = "com.elasticpath.sisu.example.service.managed.impl.ThreeWordGenerator",
		pid = {"com.elasticpath.sisu.example.service.managed.impl.ThreeWordGenerator" },
		description = "Configuration for the exciting ThreeWordGenerator Service",
		name = "Three Word Generator Configuration")
public @interface Configuration {


	@AttributeDefinition(name = "First Word", defaultValue = "aon")
	String propertyOne();
	@AttributeDefinition(name = "Second Word", defaultValue = "do")
	String propertyTwo();
	@AttributeDefinition(name = "Third Word", defaultValue = "tri")
	String propertyThree();
}
