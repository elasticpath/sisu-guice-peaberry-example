package com.cjbooms.sisu.test.apis;


import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class CustomTypeListenersModule extends AbstractModule {

	@Override
	protected void configure() {
		bindListener(Matchers.any(), new ScopeTypeListener());
		bindListener(Matchers.any(), new UserIdTypeListener());
	}
}
