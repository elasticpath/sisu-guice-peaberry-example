package com.cjbooms.sisu.test.impl;

import java.util.Arrays;
import java.util.Random;

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

	public String process(final int numberOfWords) {
		String[] randomWords = new String[numberOfWords];
		Random random = new Random();
		for(int i = 0; i < numberOfWords; i++) {
			char[] word = new char[random.nextInt(8)+3]; // words of length 3 through 10. (1 and 2 letter words are boring.)
			for(int j = 0; j < word.length; j++) {
				word[j] = (char)('a' + random.nextInt(26));
			}
			randomWords[i] = new String(word);
		}

		String timeStamp = timeStamperProvider.get().generateTimeStamp();
		return timeStamp + " " + Arrays.toString(randomWords);
	}
}