package com.cjbooms.sisu.test.apis;

import java.lang.reflect.Field;

import com.google.inject.MembersInjector;


public class UserIdInjector<T> implements MembersInjector<T> {

	private static final String[] userIds = {"cjbooms", "cgallagher", "conor.gallagher"};
	private static int counter = userIds.length;

	private final Field field;

	UserIdInjector(Field field) {
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
