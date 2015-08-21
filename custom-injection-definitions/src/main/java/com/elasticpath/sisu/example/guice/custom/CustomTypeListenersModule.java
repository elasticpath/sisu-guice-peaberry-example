package com.elasticpath.sisu.example.guice.custom;


import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class CustomTypeListenersModule extends AbstractModule {

	@Override
	protected void configure() {
		bindListener(Matchers.any(), new UserCommentTypeListener());
		bindListener(Matchers.any(), new UserNameTypeListener());
	}
}
