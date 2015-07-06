package org.sonatype.examples.peaberry.test.impl;

import java.lang.reflect.Field;

import javax.inject.Named;
import javax.inject.Singleton;

import com.google.inject.TypeLiteral;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

/**
 * Created by conor on 06/07/15.
 */
@Singleton
@Named
public class ScopeTypeListener implements TypeListener {

	public <T> void hear(TypeLiteral<T> typeLiteral, TypeEncounter<T> typeEncounter) {
		Class<?> clazz = typeLiteral.getRawType();
		while (clazz != null) {
			for (Field field : clazz.getDeclaredFields()) {
				if (field.isAnnotationPresent(InjectScope.class)) {
					typeEncounter.register(new ScopeInjector<T>(field));
				}
			}
			clazz = clazz.getSuperclass();
		}
	}
}
