package com.elasticpath.sisu.example.guice.custom;

import java.lang.reflect.Field;

import com.google.inject.MembersInjector;

public class UserCommentInjector<T> implements MembersInjector<T> {

	private static final String[] scopes = {"likes OSGI!", "hates OSGI!"};
	private static int counter = scopes.length;

	private final Field field;

	UserCommentInjector(Field field) {
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
