package com.cjbooms.sisu.test.custom;

import javax.inject.Named;
import javax.inject.Singleton;

import com.cjbooms.sisu.test.apis.ScopeTypeListener;
import com.cjbooms.sisu.test.apis.UserIdTypeListener;
import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

@Singleton
@Named
public class GuiceModule extends AbstractModule {

	@Override
	protected void configure() {
		bindListener(Matchers.any(), new ScopeTypeListener());
		bindListener(Matchers.any(), new UserIdTypeListener());
	}
}
