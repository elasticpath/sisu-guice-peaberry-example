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
		wordOne = config.get("propertyOne").toString();
		wordTwo = config.get("propertyTwo").toString();
		wordThree = config.get("propertyThree").toString();
	}


	@Override
	public String generateWords() {
		return wordGeneratorDelegate.process(wordOne, wordTwo, wordThree);
	}
}
