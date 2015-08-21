package com.cjbooms.sisu.test.impl;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

/**
 * Created by conor on 19/08/15.
 */
@ObjectClassDefinition(pid = {"com.cjbooms.sisu.test.apis.WordGenerator", "com.cjbooms.sisu.test.impl.WordGeneratorImpl" })
public interface Configuration {

	public static final String PROPERTY_ONE_NAME = "property.one";
	public static final String PROPERTY_TWO_NAME = "property.two";
	public static final String PROPERTY_THREE_NAME = "property.three";

	@AttributeDefinition(name = PROPERTY_ONE_NAME, defaultValue = "aon")
	String getPropertyOne();
	@AttributeDefinition(name = PROPERTY_TWO_NAME, defaultValue = "do")
	String getPropertyTwo();
	@AttributeDefinition(name = PROPERTY_THREE_NAME, defaultValue = "tri")
	String getPropertyThree();
}
