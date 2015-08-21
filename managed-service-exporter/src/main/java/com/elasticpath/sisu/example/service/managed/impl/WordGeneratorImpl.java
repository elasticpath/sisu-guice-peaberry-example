package com.elasticpath.sisu.example.service.managed.impl;

import java.util.Dictionary;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import com.elasticpath.sisu.example.apis.WordGenerator;
import com.google.common.base.MoreObjects;

import org.eclipse.sisu.contrib.peaberry.annotations.ServiceExport;
import org.osgi.service.cm.ManagedService;


// Random word generator
@Named
@Singleton
@ServiceExport( service = WordGenerator.class )
public class WordGeneratorImpl implements WordGenerator, ManagedService {


	static class Defaults {
		public static String PROPERTY_ONE_DEFAULT = "one";
		public static String PROPERTY_TWO_DEFAULT = "two";
		public static String PROPERTY_THREE_DEFAULT = "three";
	}
	String wordOne = "Hey";
	String wordTwo = "Ho";
	String wordThree = "Let's Go";

	@Override
	public void updated(final Dictionary<String, ?> config) {
		if (config == null) {
			return;
		}
		wordOne = MoreObjects.firstNonNull(config.get(Configuration.PROPERTY_ONE_NAME), Defaults.PROPERTY_ONE_DEFAULT).toString();
		wordTwo = MoreObjects.firstNonNull(config.get(Configuration.PROPERTY_TWO_NAME), Defaults.PROPERTY_TWO_DEFAULT).toString();
		wordThree = MoreObjects.firstNonNull(config.get(Configuration.PROPERTY_THREE_NAME), Defaults.PROPERTY_THREE_DEFAULT).toString();
	}


	private WordGeneratorDelegate wordGeneratorDelegate;


	@Inject
	public WordGeneratorImpl(
			WordGeneratorDelegate wordGeneratorDelegate) {
		this.wordGeneratorDelegate = wordGeneratorDelegate;
	}

	@Override
	public String createWords(final int numberOfWords) {
		return wordGeneratorDelegate.process(numberOfWords);
	}
}
