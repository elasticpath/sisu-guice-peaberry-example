package com.elasticpath.sisu.example.guice.custom;

import java.lang.reflect.Field;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

public class UserCommentTypeListener implements TypeListener {

	public <T> void hear(TypeLiteral<T> typeLiteral, TypeEncounter<T> typeEncounter) {
		Class<?> clazz = typeLiteral.getRawType();
		while (clazz != null) {
			for (Field field : clazz.getDeclaredFields()) {
				if (field.isAnnotationPresent(UserComment.class)) {
					typeEncounter.register(new UserCommentInjector<T>(field));
				}
			}
			clazz = clazz.getSuperclass();
		}
	}
}
