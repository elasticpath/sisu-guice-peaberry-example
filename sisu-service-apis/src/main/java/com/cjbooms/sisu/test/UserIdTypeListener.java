package com.cjbooms.sisu.test;

import java.lang.reflect.Field;

import javax.inject.Named;
import javax.inject.Singleton;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

@Singleton
@Named
public class UserIdTypeListener implements TypeListener {

	public <T> void hear(TypeLiteral<T> typeLiteral, TypeEncounter<T> typeEncounter) {
		Class<?> clazz = typeLiteral.getRawType();
		while (clazz != null) {
			for (Field field : clazz.getDeclaredFields()) {
				if (field.isAnnotationPresent(InjectUserId.class)) {
					typeEncounter.register(new UserIdInjector<T>(field));
				}
			}
			clazz = clazz.getSuperclass();
		}
	}
}
