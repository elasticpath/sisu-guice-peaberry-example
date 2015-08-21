package com.elasticpath.sisu.example.guice.custom;

import java.lang.reflect.Field;

import com.google.inject.MembersInjector;


public class UserNameInjector<T> implements MembersInjector<T> {

	private static final String[] userIds = {"Caoimhe", "Siobhan", "Tadgh"};
	private static int counter = userIds.length;

	private final Field field;

	UserNameInjector(Field field) {
		this.field = field;
		field.setAccessible(true);
	}

	public void injectMembers(T t) {
		try {
			String userId = userIds[counter % userIds.length];
			counter++;
			field.set(t, userId);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
