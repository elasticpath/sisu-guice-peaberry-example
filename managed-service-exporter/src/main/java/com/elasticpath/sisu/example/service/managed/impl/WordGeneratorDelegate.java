package com.elasticpath.sisu.example.service.managed.impl;

import java.util.Arrays;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
@Named
public class WordGeneratorDelegate {

	Provider<PrototypeTimeStamper> timeStamperProvider;

	@Inject
	public WordGeneratorDelegate(Provider<PrototypeTimeStamper> timeStamperProvider) {
		this.timeStamperProvider = timeStamperProvider;
	}

	public String process(final String... words) {
		String timeStamp = timeStamperProvider.get().generateTimeStamp();
		return Arrays.toString(words) + " generated at time: " + timeStamp;
	}
}