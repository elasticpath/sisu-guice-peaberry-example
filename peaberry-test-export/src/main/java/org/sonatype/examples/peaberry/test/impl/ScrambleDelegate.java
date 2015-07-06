package org.sonatype.examples.peaberry.test.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;



@Singleton
@Named
public class ScrambleDelegate {

	Provider<TimeStamper> timeStamperProvider;
	@Inject
	public ScrambleDelegate(Provider<TimeStamper> timeStamperProvider) {
		this.timeStamperProvider = timeStamperProvider;
	}

	public String process(final String message) {
		final List<Character> charList = new ArrayList<Character>();
		for (final char character : message.toCharArray()) {
			charList.add(character);
		}

		Collections.shuffle(charList);

		final char[] mixedChars = new char[message.length()];
		for (int i = 0; i < mixedChars.length; i++) {
			mixedChars[i] = charList.get(i);
		}

		String timeStamp = timeStamperProvider.get().generateTimeStamp();
		return timeStamp + "    " + mixedChars;
	}
}