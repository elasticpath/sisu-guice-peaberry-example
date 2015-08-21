package com.elasticpath.sisu.example.custom.impl;


import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

import com.elasticpath.sisu.example.apis.User;
import com.elasticpath.sisu.example.apis.UserGenerator;
import org.eclipse.sisu.contrib.peaberry.annotations.ServiceExport;

@Singleton
@Named
@ServiceExport(interfaces = UserGenerator.class)
public class DefaultUserGenerator implements UserGenerator {

	Provider<User> userProvider;

	@Inject
	public DefaultUserGenerator(final Provider<User> userProvider) {
		this.userProvider = userProvider;
	}

	@Override
	public User generateUser() {
		return userProvider.get();
	}
}
