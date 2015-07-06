package com.cjbooms.sisu.test.impl;

import javax.inject.Inject;
import javax.inject.Named;

import com.cjbooms.sisu.test.WordGenerator;


// Random word generator
@Named
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
