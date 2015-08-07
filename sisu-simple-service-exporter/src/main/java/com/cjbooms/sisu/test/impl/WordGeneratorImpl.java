package com.cjbooms.sisu.test.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.cjbooms.sisu.test.apis.WordGenerator;

import org.osgi.service.component.annotations.Component;


// Random word generator
@Named
@Component(
			service = WordGenerator.class,
			immediate = true)
public class WordGeneratorImpl implements WordGenerator {


	private final WordGeneratorDelegate wordGeneratorDelegate;

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
