package org.sonatype.examples.peaberry.test.impl;

import java.lang.reflect.Field;

import com.google.inject.MembersInjector;

/**
 * Created by conor on 06/07/15.
 */
public class ScopeInjector<T> implements MembersInjector<T> {

	private static final String[] scopes = {"What's a scope?", "I'm a scope!", "You're a scope", "I'm a store..."};
	private static int counter = scopes.length;

	private final Field field;

	ScopeInjector(Field field) {
		this.field = field;
		field.setAccessible(true);
	}

	public void injectMembers(T t) {
		try {
			String scopeValue = scopes[counter % scopes.length];
			counter++;
			field.set(t, scopeValue);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
