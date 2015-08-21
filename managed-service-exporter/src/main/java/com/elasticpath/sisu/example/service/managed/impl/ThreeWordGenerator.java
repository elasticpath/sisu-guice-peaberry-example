package com.elasticpath.sisu.example.service.managed.impl;

import java.util.Dictionary;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.eclipse.sisu.contrib.peaberry.annotations.ServiceExport;
import org.osgi.service.cm.ManagedService;

import com.elasticpath.sisu.example.apis.WordGenerator;


// Random word generator
@Named
@Singleton
@ServiceExport( service = WordGenerator.class )
public class ThreeWordGenerator implements WordGenerator, ManagedService {

	String wordOne = "Hey";
	String wordTwo = "Ho";
	String wordThree = "Let's Go";

	private final WordGeneratorDelegate wordGeneratorDelegate;

	@Inject
	public ThreeWordGenerator(
			final WordGeneratorDelegate wordGeneratorDelegate) {
		this.wordGeneratorDelegate = wordGeneratorDelegate;
	}


	@Override
	public void updated(final Dictionary<String, ?> config) {
		if (config == null) {
			return;
		}
		wordOne = config.get(Configuration.PROPERTY_ONE_NAME).toString();
		wordTwo = config.get(Configuration.PROPERTY_TWO_NAME).toString();
		wordThree = config.get(Configuration.PROPERTY_THREE_NAME).toString();
	}


	@Override
	public String generateWords() {
		return wordGeneratorDelegate.process(wordOne, wordTwo, wordThree);
	}
}
